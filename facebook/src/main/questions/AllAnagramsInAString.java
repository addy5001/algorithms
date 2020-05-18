package questions;

import java.util.*;

public class AllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        return _findAnagramsOptimized(s, p);
    }

    /**
     * Sliding window approach
     * Create an index for p
     * Do a compare of the index array of s with a sliding window
     * @param s
     * @param p
     * @return
     */
    private List<Integer> _findAnagramsOptimized(String s, String p) {
        if(s == null || s.length() == 0 || p.length() > s.length())
            return Collections.emptyList();

        int[] sIndex = new int[26];
        int[] pIndex = _createIndex(p);

        int sLen = s.length();
        int pLen = p.length();

        List<Integer> indices = new ArrayList<>();

        for(int i=0; i<sLen; i++) {

            sIndex[s.charAt(i) - 97]++;

            if(i >= pLen) {
                sIndex[s.charAt(i - pLen) - 97]--;
            }

            if(Arrays.equals(sIndex, pIndex))
                indices.add(i-pLen+1);
        }

        return indices;
    }

    private int[] _createIndex(String s) {
        int[] index = new int[26];
        for(char a : s.toCharArray()) {
            index[a - 97]++;
        }

        return index;
    }

    /**
     * Time Limit Exceeded
     * Compute all permutations of pattern and store in set
     * Check each index of text for occurence of anagram of pattern in the set
     *
     * p = length of p, s = length of s
     * Time Complexity = p! + (s - p) * p!
     */
    private List<Integer> _findAnagrams(String s, String p) {
        if(s == null || s.length() == 0 || s.length() < p.length())
            return Collections.emptyList();

        if(s.equals(p))
            return Arrays.asList(0);

        List<Integer> indices = new ArrayList<>();
        Set<String> anagrams = new HashSet<>();
        _computeAnagrams(p.toCharArray(), anagrams, 0, p.length()-1);
        int begin=0;
        for(int i=p.length(); i<=s.length(); i++) {
            if(anagrams.contains(s.substring(begin, i)))
                indices.add(begin);
            begin++;
        }

        return Collections.unmodifiableList(indices);
    }

    private void _computeAnagrams(char[] p, Set<String> anagrams, int begin, int end) {
        if(begin > end)
            return;

        if(begin == end) {
            anagrams.add(new String(p));
            return;
        }

        for(int i=begin; i<=end; i++) {
            _swap(p, begin, i);
            _computeAnagrams(p, anagrams, begin+1, end);
            _swap(p, begin, i);
        }
    }

    private void _swap(char[] p, int i, int j) {
        char temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }
}
