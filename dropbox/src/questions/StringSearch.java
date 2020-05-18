package questions;

public class StringSearch {

    /**
     * Brute-force approach
     *
     * Ideally, text is treated as a stream of data
     * Brute-force require the length of the pattern in text as "backup"
     * @param pattern
     * @param txt
     * @return
     */
    public static int search(String pattern, String txt) {
        int m = pattern.length();
        int n = txt.length();

        for(int i=0; i<n-m+1; i++) {
            if(txt.substring(i, i+m).equals(pattern))
                return i;
        }

        return -1;
    }

    static int multiplier = 256;
    static int modulus = 997;

    /**
     * Rabin-Karp Method (rolling hash)
     */
    public static long hash(String key, int M) {

        long hash = 0;
        for(int i=0; i<M; i++) {
            hash = ((hash * multiplier) + key.charAt(i)) % modulus;
        }

        return hash;
    }

    public static int searchRollingHash(String pattern, String txt) {
        int n = txt.length();
        int m = pattern.length();
        long patternHash = hash(pattern, m);
        long txtHash = hash(txt, m);

        if(patternHash == txtHash)
            return 0;

        int multiplier1 = 1;
        for(int i=1; i<m; i++) {
            multiplier1 = (multiplier * multiplier1) % modulus;
        }

        for(int i=m; i<n; i++) {
            txtHash = (txtHash + modulus - (multiplier1*txt.charAt(i-m) % modulus)) % modulus;
            txtHash = (multiplier * txtHash + txt.charAt(i)) % modulus;

            if(txtHash == patternHash)
                return i-m+1;
        }

        return -1;
    }
}
