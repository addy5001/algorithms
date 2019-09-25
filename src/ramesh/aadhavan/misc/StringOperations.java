package ramesh.aadhavan.misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public static void main(String[] args) {
        Set<String> dict = new HashSet<>(Arrays.asList("am","pan","cam", "ampa", "ncam", "amp", "anc", "am"));
        splitWordIntoDictionaryWords("ampancam", dict);
    }
}
