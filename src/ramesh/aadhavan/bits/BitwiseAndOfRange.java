package ramesh.aadhavan.bits;

public class BitwiseAndOfRange {
    public int rangeBitwiseAnd(int m, int n) {
        while(n > m) {
            n = n & (n - 1);
        }

        return m & n;
    }
}
