package ramesh.aadhavan.bits;

public class MaxBinaryGap {
    public int binaryGap(int N) {
        String binaryString = Integer.toBinaryString(N);
        int left;
        int right = -1;
        int idx = 0;
        int max = 0;

        while(idx < binaryString.length()) {
            if(binaryString.charAt(idx) == '1') {
                if(right == -1)
                    right = idx;
                else {
                    left = right;
                    right = idx;
                    max = (right - left) > max ? (right - left) : max;
                }
            }

            idx++;
        }

        return max;
    }

    private int _binaryGap(int N) {
        int last = -1, ans = 0;
        for (int i = 0; i < 32; ++i)
            if (((N >> i) & 1) > 0) {
                if (last >= 0)
                    ans = Math.max(ans, i - last);
                last = i;
            }

        return ans;
    }
}
