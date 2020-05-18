package questions;

import java.util.function.Function;

/**
 * Traverse from end to start for both strings
 * add digits and get mod and div
 * mod is digit
 * div is carry
 *
 * iterate for remaining length
 * append carry in the end
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        return _add(a,b);
    }

    private Function<Character, Integer> convert = c -> c == '0' ? 0 : 1;

    private String _add(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int aIdx = a.length()-1;
        int bIdx = b.length()-1;

        int carry = 0;
        while(aIdx >= 0 && bIdx >= 0) {
            int aDigit = convert.apply(a.charAt(aIdx));
            int bDigit = convert.apply(b.charAt(bIdx));

            int sum = carry + aDigit + bDigit;
            int digit = sum%2;
            carry = sum/2;
            sb.append(digit);

            aIdx--;
            bIdx--;
        }

        while(aIdx >= 0) {
            int aDigit = convert.apply(a.charAt(aIdx));
            int sum = carry + aDigit;
            int digit = sum%2;
            carry = sum/2;

            sb.append(digit);

            aIdx--;
        }

        while(bIdx >= 0) {
            int bDigit = convert.apply(b.charAt(bIdx));
            int sum = carry + bDigit;
            int digit = sum%2;
            carry = sum/2;

            sb.append(digit);

            bIdx--;
        }

        if(carry > 0)
            sb.append(carry);

        return sb.reverse().toString();
    }
}
