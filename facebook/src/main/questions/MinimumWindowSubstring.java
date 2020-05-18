package questions;

import java.util.*;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        return _minWindow(s, t);
    }

    private String _minWindow(String s, String t) {
        if(s == null || s.length() == 0 || t == null || t.length() == 0)
            return "";

        Map<Character, Integer> countDict = new HashMap<>();
        for(char c : t.toCharArray()) {
            countDict.put(c, countDict.getOrDefault(c, 0) + 1);
        }

        int required = countDict.size();

        int left=0;
        int right=0;
        int formed = 0;

        Map<Character, Integer> windowCount = new HashMap<>();
        int resultLength = -1;
        int leftIdx = 0;
        int rightIdx = 0;

        while(right < s.length()) {
            char c = s.charAt(right);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);

            if(countDict.containsKey(c)
                    && windowCount.get(c).intValue() == countDict.get(c).intValue()) {
                formed++;
            }

            while(left <= right && formed == required) {
                char lC = s.charAt(left);

                if(resultLength == -1 || right - left + 1 < resultLength) {
                    resultLength = right - left + 1;
                    leftIdx = left;
                    rightIdx = right;
                }

                windowCount.put(lC, windowCount.get(lC) - 1);
                if(countDict.containsKey(lC) && windowCount.get(lC).intValue() < countDict.get(lC).intValue()) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return resultLength == -1 ? "" : s.substring(leftIdx, rightIdx+1);
    }

    /**
     * Follow-up.
     *
     * If S is too long and contains characters which are not present in T
     * A small improvement to the above approach can reduce the time complexity of the algorithm to
     * O(2*|filtered\_S| + |S| + |T|)O(2∗∣filtered_S∣+∣S∣+∣T∣), where filtered\_Sfiltered_S is the string formed from S
     * by removing all the elements not present in TT.
     *
     * This complexity reduction is evident when |filtered\_S| <<< |S|∣filtered_S∣<<<∣S∣.
     *
     * This kind of scenario might happen when length of string TT is way too small than the length of string SS and
     * string SS consists of numerous characters which are not present in TT.
     *
     * Algorithm
     *
     * We create a list called filtered\_Sfiltered_S which has all the characters from string SS along with their
     * indices in SS, but these characters should be present in TT.
     *
     *   S = "ABCDDDDDDEEAFFBC" T = "ABC"
     *   filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
     *   Here (0, 'A') means in string S character A is at index 0.
     * We can now follow our sliding window approach on the smaller string filtered\_Sfiltered_S.
     *
     */
}
