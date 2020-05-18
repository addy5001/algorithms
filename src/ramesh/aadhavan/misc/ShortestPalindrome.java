package ramesh.aadhavan.misc;

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        return _twoPointerApproach(s, 0, s.length());
    }

    private String _naive(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();

        for(int i=0; i<n; i++) {
            if(s.substring(0, n-i).equals(rev.substring(i, n))) {
                return rev.substring(0, i).concat(s);
            }
        }

        return "";
    }

    private String _twoPointerApproach(String s, int start, int end) {

        int i=start;
        for(int j=end-1; j>= start; j--) {
            if(s.charAt(i) == s.charAt(j))
                i++;
        }

        if(i==end)
            return s.substring(start, end);

        return new StringBuilder(s.substring(i, end)).reverse()
                .append(_twoPointerApproach(s, start, i)).append(s, i, end).toString();
    }
}
