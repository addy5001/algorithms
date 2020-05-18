package ramesh.aadhavan.misc;

import java.util.*;

public class StringOperations {
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder prefixBuilder = new StringBuilder();
        if(Objects.isNull(strs) || strs.length == 0)
            return "";

        for(int i=0; i<strs.length; i++) {
            if(Objects.isNull(strs[i]) || strs[i].length() == 0)
                return "";
        }

        int min = strs[0].length();
        for(int i=1; i<strs.length; i++) {
            if(min > strs[i].length())
                min = strs[i].length();
        }

        int k = 0;
        while(k < min) {
            int count = 0;
            for(int i=0; i<strs.length-1; i++) {
                if(strs[i].charAt(k) == strs[i+1].charAt(k))
                    count++;
            }

            if(count==strs.length-1 && (k==0 || prefixBuilder.length()>0))
                prefixBuilder.append(strs[0].charAt(k));

            k++;
        }

        return prefixBuilder.toString();
    }

    public static int strStr(String haystack, String needle) {
        if(needle.length() == 0)
            return 0;
        int length = needle.length();
        int i=0;
        while(i < haystack.length()) {
            if(i+length <= haystack.length() && haystack.substring(i, i+length).equals(needle)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void printPalindromicSequences(String input) {
        printPalindromicSequences(input, "", 0, input.length());
    }

    private static void printPalindromicSequences(String input, String output, int begin, int end) {
        if(begin == end) {
            System.out.println(output);
            return;
        }

        String delimiter = "-";
        for(int i=begin; i<end; i++) {
            if(isPalindrome(input, begin, i)) {
                if(i+1==input.length()) {
                    delimiter = "";
                }
                printPalindromicSequences(input, output + input.substring(begin, i+1) + delimiter, i+1, end);
            }
        }
    }

    public static boolean isPalindrome(String input, int begin, int end) {
        while(begin < end) {
            if(input.charAt(begin) != input.charAt(end))
                return false;
            begin++;
            end--;
        }
        return true;
    }

    public static boolean arePermutations(String s1, String s2) {
        if(s1.length()!=s2.length())
            return false;

        int result = s1.charAt(0) ^ s2.charAt(0);
        for(int i=1; i<s1.length(); i++) {
            result = result ^ s1.charAt(i) ^ s2.charAt(i);
        }

        return result == 0;
    }

    public static boolean hasDuplicates(String s1) {
        if(s1 == null || s1.length()==0)
            return false;

        if(s1.length() > 128)
            return true;

        boolean[] duplicates = new boolean[256];
        for(int i=0; i<s1.length(); i++) {
            if(duplicates[s1.charAt(i)])
                return true;
            else
                duplicates[s1.charAt(i)] = true;
        }

        return false;
    }

    private static boolean isPalindromeRecurse(String str, int start, int end) {
        if(start > end)
            return false;

        if(str.charAt(start) == str.charAt(end)) {
            return (++start >= --end) || isPalindromeRecurse(str, start, end);
        }

        return false;
    }

    public static boolean isPalindromeRecurse(String str) {
        return isPalindromeRecurse(str, 0, str.length()-1);
    }

    public static void palindromicPartitions(String str) {
        palindromicPartitions(str, 0, str.length() - 1);
    }

    private static void palindromicPartitions(String str, int start, int end) {
        if(start > end)
            return;

        if(start == end)
            System.out.println(str.charAt(start));

        for(int i=start; i<end; i++) {
            if(isPalindromeRecurse(str, i, end))
                System.out.println(str.substring(i, end+1));
        }

        palindromicPartitions(str, start, end-1);
    }

    public static void splitWordIntoDictionaryWords(String word, Set<String> dictionary) {
        _splitWordIntoDictionaryWords(word, "", false, 0, word.length(), dictionary);
    }

    private static void _splitWordIntoDictionaryWords(String word, String output, boolean exactSplit,
                                                      int begin, int end, Set<String> dict) {
        if(begin == end) {
            if(exactSplit)
                System.out.println(output);
            return;
        }
        for(int i=1; i<end; i++) {
            if (begin + i <= end) {
                String sub = word.substring(begin, begin + i);
                boolean _exactSplit = false;
                if (dict.contains(sub)) {
                    if (begin + i == end)
                        _exactSplit = true;
                    _splitWordIntoDictionaryWords(word, output.concat(sub+" "), _exactSplit, begin + i, end, dict);
                }
            }
        }
    }

    public static String longestPalindromeSubstring(String word) {
        return _longestPalindromeSubstring(word,0, word.length());
    }

    private static String _longestPalindromeSubstring(String word, int begin, int end) {
        String output = "";
        String nextPalindrome = "";
        for(int i=1; begin+i <= end; i++) {
            String sub = word.substring(begin, begin+i);
            if(isPalindromeRecurse(sub) && sub.length() > output.length())
                output = sub;
            nextPalindrome = _longestPalindromeSubstring(word, begin+i, end);
            output = output.length() > nextPalindrome.length() ? output : nextPalindrome;
        }

        return output;
    }

    public static int longestPalindrome(String s) {
        int[] capitals = new int[26];
        int[] smalls = new int[26];

        for(char a : s.toCharArray()) {
            if(a >=65 && a <= 90) {
                capitals[a-65]++;
            }
            else if(a >= 97 && a <= 122) {
                smalls[a-97]++;
            }
        }

        int maxOdd = 0;
        int evenLength = 0;
        for(int i : capitals) {
            if(i != 0) {
                if((i&1) == 0) {
                    evenLength+=i;
                }
                else {
                    if(maxOdd < i) {
                        evenLength+=(i-1);
                        maxOdd = 1;
                    }
                }
            }
        }

        for(int i : smalls) {
            if(i != 0) {
                if((i&1) == 0) {
                    evenLength+=i;
                }
                else {
                    if(maxOdd < i) {
                        evenLength+=(i-1);
                        maxOdd=1;
                    }
                }
            }
        }

        return (evenLength != 0) ? evenLength + maxOdd : maxOdd;
    }

    public int lengthOfLastWord(String s) {
        return _lengthCharByChar(s, s.length()-1, 0, false);
    }

    private int _lengthCharByChar(String s, int end, int len, boolean lastCharSeen) {
        if(end < 0)
            return len;

        if(s.charAt(end) == ' ') {
            if(lastCharSeen)
                return len;
            else
                return _lengthCharByChar(s, end-1, len, false);
        }

        return _lengthCharByChar(s, end-1, len+1, true);
    }

    private int _lengthOfLastWordJavaSolution(String s) {
        if(s == null || s.length() == 0)
            return 0;

        String[] splits = s.split(" ");

        if(splits.length == 0)
            return 0;

        int lastWord = splits.length-1;

        if(splits[lastWord] == " ")
            return 0;

        return splits[lastWord].length();
    }

    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0 || s.length() == 1)
            return true;

        int begin = 0;
        int end = s.length()-1;

        while(begin < end) {
            if(! (Character.isAlphabetic(s.charAt(begin)) || Character.isDigit(s.charAt(begin))))
                begin++;
            else if(! (Character.isAlphabetic(s.charAt(end)) || Character.isDigit(s.charAt(end))))
                end--;
            else {
                if((s.charAt(begin) | 32) != (s.charAt(end) | 32))
                    return false;

                begin++;
                end--;
            }
        }

        return true;
    }


    public boolean wordPattern(String pattern, String str) {
        return _wordPatternHashMap(pattern, str);
    }

    private boolean _wordPatternHashMap(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();

        char[] keys = pattern.toCharArray();
        String[] words = str.split(" ");

        if(keys.length != words.length)
            return false;

        int i = 0;
        while(i < keys.length) {
            if(map.containsKey(keys[i])) {
                if(! map.get(keys[i]).equals(words[i])) {
                    return false;
                }
            }
            else {
                map.put(keys[i], words[i]);
            }

            i++;
        }

        return true;
    }

    public static boolean detectCapitalUse(String word) {
        return _detect(word, 0, word.length(), true, false, false);
    }

    private static boolean _detect(String word,
                            int begin, int end, boolean result,
                            boolean firstCaps, boolean secondCaps) {
        if(begin == end || !result)
            return result;

        if(begin == 0) {
            return _detect(word, begin+1, end, true,
                    Character.isUpperCase(word.charAt(0)), secondCaps);
        }
        else if(begin == 1) {
            boolean second = Character.isUpperCase(word.charAt(1));
            if(!firstCaps && second)
                result = false;

            return _detect(word, begin+1, end, result, firstCaps, second);
        }
        else {
            boolean current = Character.isUpperCase(word.charAt(begin));
            if(firstCaps) {
                if(secondCaps) {
                    result = current ? true : false;
                }
                else {
                    result = current ? false : true;
                }
            }
            else {
                result = current ? false : true;
            }

            return _detect(word, begin+1, end, result, firstCaps, secondCaps);
        }
    }

    public static boolean isSubsequence(String s, String t) {
        if(s == null || t == null)
            return false;

        int tIdx = 0;
        int sIdx = 0;

        while(sIdx < s.length() && tIdx < t.length()) {
            if(s.charAt(sIdx) == t.charAt(tIdx)) {
                sIdx++;
                tIdx++;
            }
            else {
                tIdx++;
            }
        }

        return sIdx == s.length();
    }

    public static String shortestCompletingWord(String licensePlate, String... words) {
        int[] charMap = new int[26];

        for(int i=0; i<licensePlate.length(); i++) {
            if(Character.isAlphabetic(licensePlate.charAt(i))) {
                char x = (char) (licensePlate.charAt(i) | 32);
                charMap[(x-97) % 26]++;
            }
        }

        String shortestWord = null;
        int shortestLen = Integer.MAX_VALUE;

        for(String word : words) {
            int[] charMapCopy = new int[26];
            System.arraycopy(charMap, 0, charMapCopy, 0, charMap.length);
            boolean contains = true;

            for(char c : word.toCharArray()) {
                if(Character.isAlphabetic(c)) {
                    char comp = (char) (c | 32);
                    charMapCopy[(comp - 97) % 26]--;
                }
            }

            for(int i : charMapCopy) {
                if(i >= 1) {
                    contains = false;
                    break;
                }
            }

            if(contains && word.length() < shortestLen) {
                shortestWord = word;
                shortestLen = word.length();
            }
        }

        return shortestWord;
    }

    public List<String> commonChars(String[] A) {
        int[][] matrix = calculateOccurenceMatrix(A);
        return calculateCommonChars(matrix, A.length);
    }

    private int[][] calculateOccurenceMatrix(String[] A) {
        int m = A.length;
        int n = 26;
        int[][] matrix = new int[m][n];

        for(int i=0; i<m; i++) {
            String s = A[i];
            for(int j=0; j<s.length(); j++) {
                int idx = (s.charAt(j) - 97);
                matrix[i][idx]++;
            }
        }

        return matrix;
    }

    private List<String> calculateCommonChars(int[][] matrix, int m) {
        List<String> results = new ArrayList<>();

        for(int j=0; j<26; j++) {
            int min = Integer.MAX_VALUE;
            int counter = 0;
            for(int i=0; i<m; i++) {
                if(matrix[i][j] != 0) {
                    if(matrix[i][j] < min)
                        min = matrix[i][j];
                    counter++;
                }
            }

            if(counter == m) {
                while(min > 0) {
                    results.add(String.valueOf(j + 97));
                    min--;
                }
            }
        }

        return results;
    }

    // without repeating characters
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        int maxLen = 0;

        for(int i=0; i<s.length(); i++) {
            hashSet.clear();
            int len = 0;
            for(int j=i; j<s.length(); j++) {
                if(!hashSet.contains(s.charAt(j))) {
                    hashSet.add(s.charAt(j));
                    len++;
                }
                else
                    break;
            }

            if(len > maxLen)
                maxLen = len;
        }

        return maxLen;
    }

    public int lengthOfLongestSubstringOptimized(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int start = 0, end = 0;
        int maxLen = 0;
        int len = 0;
        while(end < s.length()) {
            if(!map.containsKey(s.charAt(end))) {
                len++;
            }
            else {
                int positionOfChar = map.get(s.charAt(end));
                if(positionOfChar >= start)
                    start = map.get(s.charAt(end)) + 1;
                len = end - start + 1;
            }

            map.put(s.charAt(end), end);
            if(len > maxLen)
                maxLen = len;

            end++;
        }

        return maxLen;
    }
}
