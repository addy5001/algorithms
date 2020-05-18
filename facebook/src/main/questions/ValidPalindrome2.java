package questions;

public class ValidPalindrome2 {
    public boolean validPalindrome(String s) {
        return _isValidPalindrome(s);
    }

    private boolean _isValidPalindrome(String s) {
        int begin = 0;
        int end = s.length() - 1;
        int mid = (begin + end)/2;

        for(int i=begin, j=end; i<mid; i++, j--) {
            if(s.charAt(i) != s.charAt(j)) {
                return _isPalindrome(s, i+1, j) || _isPalindrome(s, i, j-1);
            }
        }

        return true;
    }

    private boolean _isPalindrome(String s, int begin, int end) {
        int mid = (begin + end)/2;

        for(int i=begin, j=end; i<=mid; i++, j--) {
            if(s.charAt(i) != s.charAt(j))
                return false;
        }

        return true;
    }
}
