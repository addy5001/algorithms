package ramesh.aadhavan.dynamic;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        return _expandAroundCenter(s);
    }

    private String _dynamic(String s) {
        String sReversed = new StringBuilder(s).reverse().toString();
        int m = s.length();
        int[][] matrix = new int[m][m];
        int max = -1;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++) {
                if(s.charAt(i) == sReversed.charAt(j)) {
                    if(i==0 || j==0)
                        matrix[i][j] = 1;
                    else
                        matrix[i][j] = matrix[i-1][j-1] + 1;

                    if(matrix[i][j] > max && (i-1) == (j-1)) {
                        max = matrix[i][j];
                        stringBuilder.append(s.charAt(i));
                    }
                }
                else {
                    matrix[i][j] = 0;
                }
            }
        }

        return stringBuilder.toString();
    }

    private String _expandAroundCenter(String s) {
        if(s == null || s.length() < 1)
            return "";

        int start = 0;
        int end = 0;

        for(int i=0; i<s.length(); i++) {
            int len1 = _expandAndCheck(s, i, i);
            int len2 = _expandAndCheck(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > (end - start)) {
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }

        return s.substring(start, end+1);
    }

    private int _expandAndCheck(String s, int L, int R) {

        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }

        return R - L - 1;
    }

    /*
        Mirror index: For any palindrome centered at a center c, the mirror of index j is j’ such that
        c - j = j' - c
        So, mirror index of j:
        j' = (2 * c) - j

        If the palindrome at i expands beyond the current right boundary r,
        then c is updated to i and new l, r are found and updated.
     */
    int manachersAlgorithm(String s, int N) {
        String str = getModifiedString(s, N);
        int len = (2 * N) + 1;
        int[] P = new int[len];
        int c = 0; //stores the center of the longest palindromic substring until now
        int r = 0; //stores the right boundary of the longest palindromic substring until now
        int maxLen = 0;
        for(int i = 0; i < len; i++) {
            //get mirror index of i
            int mirror = (2 * c) - i;

            //see if the mirror of i is expanding beyond the left boundary of current longest palindrome at center c
            //if it is, then take r - i as P[i]
            //else take P[mirror] as P[i]
            if(i < r) {
                P[i] = Math.min(r - i, P[mirror]);
            }

            //expand at i
            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);
            while(a < len && b >= 0 && str.charAt(a) == str.charAt(b)) {
                P[i]++;
                a++;
                b--;
            }

            //check if the expanded palindrome at i is expanding beyond the right boundary of current longest palindrome at center c
            //if it is, the new center is i
            if(i + P[i] > r) {
                c = i;
                r = i + P[i];

                if(P[i] > maxLen) { //update maxlen
                    maxLen = P[i];
                }
            }
        }
        return maxLen;
    }

    String getModifiedString(String s, int N){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }
}
