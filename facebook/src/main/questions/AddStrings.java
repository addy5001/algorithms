package questions;

public class AddStrings {
    public String addStrings(String num1, String num2) {
        return _addStrings(num1, num2, 10);
    }

    private String _addStrings(String num1, String num2, int radix) {
        StringBuilder sb = new StringBuilder();

        int carry=0;
        int i=num1.length()-1;
        int j=num2.length()-1;

        while(i >= 0 && j >= 0) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(j) - '0';

            int sum = carry + digit1 + digit2;
            int digit = sum%radix;
            carry = sum/radix;

            sb.append(digit);

            i--;
            j--;
        }

        while(i >= 0) {
            int digit1 = num1.charAt(i) - '0';
            int sum = carry + digit1;
            int digit = sum%radix;
            carry = sum/radix;

            sb.append(digit);

            i--;
        }

        while(j >= 0) {
            int digit2 = num2.charAt(j) - '0';
            int sum = carry + digit2;
            int digit = sum%radix;
            carry = sum/radix;

            sb.append(digit);

            j--;
        }

        if(carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}
