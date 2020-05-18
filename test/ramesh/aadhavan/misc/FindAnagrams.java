package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        return _findAnagrams(s, p);
    }

    private List<Integer> _findAnagrams(String s, String p) {
        if(s == null || s.length() == 0 || p.length() > s.length())
            return Collections.emptyList();

        int[] sIndex = _createIndex(s);
        int[] pIndex = _createIndex(p);

        int sLen = s.length();
        int pLen = p.length();

        List<Integer> indices = new ArrayList<>();

        for(int i=0; i<sLen; i++) {

            sIndex[s.charAt(i) - 'a']++;

            if(i >= pLen) {
                sIndex[s.charAt(i - pLen) - 'a']--;
            }

            if(Arrays.equals(sIndex, pIndex))
                indices.add(i-pLen+1);
        }

        return indices;
    }

    private int[] _createIndex(String s) {
        int[] index = new int[s.length()];
        for(char a : s.toCharArray()) {
            index[a - 'a']++;
        }

        return index;
    }
}
