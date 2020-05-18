package questions;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int swappedIdx = _next(nums);
        if(swappedIdx == -1)
            Arrays.sort(nums);
        else {
            Arrays.sort(nums, swappedIdx+1, nums.length);
        }
    }

    private int _next(int[] nums) {
        int begin = nums.length-2;

        while(begin >= 0 && nums[begin+1] <= nums[begin]) {
            begin--;
        }

        int j = begin;
        if(j == -1) {
            return j;
        }

        int swappingIdx = -1;
        for(int k=nums.length-1; k >= 0; k--) {
            if(nums[k] > nums[j]) {
                swappingIdx = k;
                break;
            }
        }

        if(swappingIdx != -1) {
            int temp = nums[j];
            nums[j] = nums[swappingIdx];
            nums[swappingIdx] = temp;
            return j;
        }

        return -1;
    }
}
