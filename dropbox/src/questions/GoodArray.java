package questions;

/**
 * Given an array nums of positive integers. Your task is to select some subset of nums, multiply each
 * element by an integer and add all these numbers. The array is said to be good if you can obtain a
 * sum of 1 from the array by any possible subset and multiplicand.
 *
 * Return True if the array is good otherwise return False.
 *
 */
public class GoodArray {
    public boolean isGoodArray(int[] nums) {

        int x = nums[0], y;
        for(int i=1; i<nums.length; i++) {
            int num = nums[i];

            while(num > 0) {
                y = x % num;
                x = num;
                num = y;
            }
        }

        return x == 1;
    }
}
