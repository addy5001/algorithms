package ramesh.aadhavan.math;

import java.util.HashMap;
import java.util.Map;

public class Math {
    public static int reverseInt(int x) {
        int reversedX = 0;

        while(x!=0) {
            int firstDigit = x%10;
            x = x/10;
            if((reversedX > Integer.MAX_VALUE/10) || (reversedX == Integer.MAX_VALUE/10 && firstDigit > 7))
                return 0;

            if((reversedX < Integer.MIN_VALUE/10) || (reversedX == Integer.MIN_VALUE/10 && firstDigit < -8))
                return 0;

            reversedX = (reversedX*10) + firstDigit;
        }

        return reversedX;
    }

    public static boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        else if(x < 10)
            return true;
        else {
            String input = String.valueOf(x);
            return isPalindrome(input.toCharArray(), 0, input.length() - 1);
        }
    }

    private static boolean isPalindrome(char[] input, int start, int end) {
        if(start<end) {
            if(input[start]!=input[end])
                return false;

            return isPalindrome(input, ++start, --end);
        }
        return true;
    }


    private Map<Character, Integer> initMap() {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        return romanMap;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = initMap();
        if(s.length() == 1)
            return romanMap.get(s.charAt(0));

        int i=0;
        int result = 0;
        while(i < s.length()) {
            int peek = (i+1 >= s.length()) ? 0 : romanMap.get(s.charAt(i+1));
            int digit = romanMap.get(s.charAt(i));
            if(peek <= digit) {
                result = result + digit;
                i++;
            }
            else {
                int sum = peek - digit;
                result = result + sum;
                i = i+2;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Math math = new Math();
        int result = math.romanToInt("MCDLXXVII");
        System.out.print(result);
    }
}
