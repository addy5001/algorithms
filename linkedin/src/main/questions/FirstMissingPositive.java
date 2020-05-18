package questions;

import java.util.Iterator;
import java.util.TreeSet;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        return _optimizedApproach(nums);
    }

    /**
     * Time Complexity O(nlogn)
     * Space Complexity O(n)
     * @param nums
     * @return
     */
    private int _treeSetApproach(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0)
                treeSet.add(nums[i]);
        }

        Iterator<Integer> treeSetIterator = treeSet.iterator();

        int number=1;
        while(treeSetIterator.hasNext()) {
            if(number != treeSetIterator.next())
                return number;

            number++;
        }

        return number;
    }

    /**
     * Time Complexity O(n)
     * Space Complexity O(1)
     */
    public int _optimizedApproach(int[] nums) {
        int n = nums.length;

        // Base Case
        int contains = 0;
        for(int i=0; i<n; i++) {
            if(nums[i] == 1) {
                contains++;
                break;
            }
        }

        if(contains == 0)
            return 1;

        // If nums.length == 1
        if(n == 1)
            return 2;

        /**
         * Data clean up - replace all negatives, 0, numbers greater than n by 1
         */
        for(int i=0; i<n; i++) {
            if(nums[i] <= 0 || nums[i] > n)
                nums[i] = 1;
        }

        // Use index as a hash key and number sign as a presence detector.
        // For example, if nums[1] is negative that means that number `1`
        // is present in the array.
        // If nums[2] is positive - number 2 is missing.
        for(int i=0; i<n; i++) {
            int num = Math.abs(nums[i]);

            if(num == n) {
                nums[0] = - Math.abs(nums[0]);
            }
            else {
                nums[num] = - Math.abs(nums[num]);
            }
        }

        // Now the index of the first positive number
        // is equal to first missing positive.
        for(int i=1; i<n; i++) {
            if(nums[i] > 0)
                return i;
        }

        if(nums[0] > 0)
            return n;

        return n+1;
    }
}
