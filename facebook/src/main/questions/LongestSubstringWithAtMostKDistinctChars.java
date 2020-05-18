package questions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctChars {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        return _slidingWindow(s, k);
    }

    private int _slidingWindow(String s, int k) {
        if(s == null || s.isBlank() || k <= 0)
            return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLength = 1;

        while(right < s.length()) {
            map.put(s.charAt(right), right++);

            if(map.size() == k+1) {
                int minIdx = Collections.min(map.values());
                map.remove(s.charAt(minIdx));
                left = minIdx + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    String _slidingWindowFindString(String s, int k) {
        if(s == null || s.isBlank() || k <= 0)
            return "";

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLength = 1;
        String result = s.substring(0, 1);

        while(right < s.length()) {
            map.put(s.charAt(right), right++);

            if(map.size() == k+1) {
                int minIdx = Collections.min(map.values());
                map.remove(s.charAt(minIdx));
                left = minIdx + 1;
            }

            if(right - left > maxLength) {
                maxLength = right - left;
                result = s.substring(left, right);
            }
        }

        return result;
    }
}
