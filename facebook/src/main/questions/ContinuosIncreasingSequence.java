package questions;

import java.util.LinkedList;
import java.util.Queue;

public class ContinuosIncreasingSequence {
    public int findLengthOfLCIS(int[] nums) {
        return _slidingWindow(nums);
    }

    public int findLengthOfLCIS(int[] nums, int k) {
        return _slidingWindow(nums, k);
    }

    private int _naive(int[] nums) {
        if(nums == null)
            return 0;

        if(nums.length <= 1)
            return nums.length;

        int max = 1;
        for(int i=0; i<nums.length-1; i++) {
            int count = 0;
            for(int j=i; j<nums.length-1; j++) {
                if(nums[j] < nums[j+1])
                    count++;
                else
                    break;
            }

            max = Math.max(max, count);
        }

        return max;
    }

    private int _slidingWindow(int[] nums) {
        if(nums == null)
            return 0;

        if(nums.length <= 1)
            return nums.length;

        int left = 0;
        int right = 1;
        int max = 1;
        int length = 1;

        while(right < nums.length) {
            if(nums[left] < nums[right]) {
                length++;
                max = Math.max(max, length);
                left++;
            }
            else {
                length = 1;
                left = right;
            }

            right++;
        }

        return max;
    }

    // With 'k' breaks allowed
    private int _slidingWindow(int[] nums, int k) {
        if(nums == null)
            return 0;

        if(nums.length <= 1)
            return nums.length;

        Queue<Integer> kBreaks = new LinkedList<>();
        int left = 0;
        int right = 1;
        int max = 1;
        int length = 1;

        while(right < nums.length) {
            if(nums[left] < nums[right]) {
                length++;
                max = Math.max(max, length);
                left++;
            }
            else {
                if(k == 0) {
                    length = 1;
                    left = right;
                }
                else if(kBreaks.size() < k) {
                    kBreaks.offer(right);
                    length++;
                    max = Math.max(max, length);
                    left++;
                }
                else {
                    left = kBreaks.poll();
                    length = right - left + 1;
                }
            }

            right++;
        }

        return max;
    }
}
