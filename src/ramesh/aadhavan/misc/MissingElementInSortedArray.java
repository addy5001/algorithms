package ramesh.aadhavan.misc;

import java.util.LinkedList;
import java.util.Queue;

public class MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        return _linear(nums, k);
    }

    private int _naive(int[] nums, int k) {
        int count = 0;
        int idx = 1;
        Queue<Integer> queue = new LinkedList<>();

        int queuePushCount = 0;
        while(idx < nums.length && queuePushCount < k) {
            if(nums[idx-1] != nums[idx]+1) {
                int temp = nums[idx-1]+1;
                while(temp < nums[idx] && queuePushCount < k) {
                    queue.add(temp);
                    temp++;
                    queuePushCount++;
                }
            }

            idx++;
        }

        Integer element = 0;
        while(!queue.isEmpty() && count < k) {
            element = queue.poll();
            count++;
        }

        if(count < k) {
            element = nums[nums.length-1];
            while(count < k) {
                element++;
                count++;
            }
        }

        return element;
    }

    public int _linear(int[] nums, int k) {
        int idx = 1;
        int remaining = k;
        int element = nums[0];

        while(idx < nums.length && remaining > 0) {
            if(nums[idx-1] != nums[idx] + 1) {
                int numsBetween = nums[idx] - nums[idx-1] - 1;
                element = nums[idx-1];
                if(remaining <= numsBetween) {
                    element += remaining;
                    remaining = 0;
                }
                else {
                    element += numsBetween;
                    remaining = remaining - numsBetween;
                }
            }

            idx++;
        }

        if(remaining > 0) {
            element = nums[nums.length-1];
            element += remaining;
        }

        return element;
    }

    /**
     * Find the missing numbers at an index
     * missing(idx) = nums[idx] - nums[0] - idx
     *
     * Find k such that missing(idx-1) < k <= missing(idx)
     *
     * Return k = nums[idx-1] + k - missing(idx-1)
     */

    private int missing(int[] nums, int idx) {
        return nums[idx] - nums[0] - idx;
    }

    private int _linear_2(int[] nums, int k) {
        int n = nums.length;

        if(k > missing(nums, n-1)) {
            return nums[n-1] + k - missing(nums, n-1);
        }

        int idx = 1;
        while(missing(nums, idx) < k)
            idx++;

        return nums[idx-1] + k - missing(nums, idx-1);
    }
}
