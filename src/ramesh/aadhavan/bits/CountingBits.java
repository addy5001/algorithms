package ramesh.aadhavan.bits;

public class CountingBits {

    /**
     * For number 2(10), 4(100), 8(1000), 16(10000), ..., the number of 1's is 1.
     * Any other number can be converted to be 2^m + x. For example, 9=8+1, 10=8+2.
     * The number of 1's for any other number is 1 + # of 1's in x.
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] counts = new int[num+1];
        int idx = 1;
        int pow = 1;
        int count = 1;

        while(idx <= num) {
            if(idx == pow) {
                counts[idx] = 1;
                pow = pow << 1;
                count = 1;
            }
            else {
                counts[idx] = counts[count] + 1;
                count++;
            }

            idx++;
        }

        return counts;
    }
}
