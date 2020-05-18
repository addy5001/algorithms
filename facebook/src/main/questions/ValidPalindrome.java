package questions;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0)
            return true;

        int begin = 0;
        int end = s.length()-1;

        while(begin < end) {
            if(! (Character.isAlphabetic(s.charAt(begin)) || Character.isDigit(s.charAt(begin))))
                begin++;
            else if(! (Character.isAlphabetic(s.charAt(end)) || Character.isDigit(s.charAt(end))))
                end--;
            else {
                if(Character.toLowerCase(s.charAt(begin)) != Character.toLowerCase(s.charAt(end)))
                    return false;

                begin++;
                end--;
            }
        }

        return true;
    }
}
