package ramesh.aadhavan.sort;

import java.util.Arrays;

public class RadixSort {

    public static void sort(int[] nums) {
        int max = findMax(nums);
        int radix = 10;
        int exp = 1;
        int[] aux = new int[nums.length];
        int[] counts = new int[radix];

        while((max/exp) > 0) {
            // Counting Sort
            for(int i=0; i<nums.length; i++) {
                counts[((nums[i]/exp) % radix)]++;
            }

            // Partial Sum
            for(int i=1; i<counts.length; i++) {
                counts[i] += counts[i-1];
            }

            // Ordering in aux
            for(int i=nums.length-1; i>=0; i--) {
                aux[--counts[((nums[i]/exp) % radix)]] = nums[i];
            }

            // Copy aux to nums
            System.arraycopy(aux, 0, nums, 0, nums.length);

            Arrays.fill(counts, 0);
            exp*=radix;
        }
    }

    private static int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int num : nums) {
            if(max < num)
                max = num;
        }

        return max;
    }
}
