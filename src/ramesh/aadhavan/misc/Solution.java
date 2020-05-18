package ramesh.aadhavan.misc;

import java.util.Arrays;

class Solution {
    public static int search(int[] nums, int target) {
        int pivotPos = _findPivot(nums, 0, nums.length - 1);

        if(pivotPos == -1) {
            return _search(nums, target, 0, nums.length-1);
        }
        else {
            if(target > nums[nums.length-1])
                return _search(nums, target, 0, pivotPos-1);
            else
                return _search(nums, target, pivotPos, nums.length-1);
        }
    }

    private static int _findPivot(int[] nums, int begin, int end) {
        if(begin > end)
            return -1;

        int mid = (begin+end)/2;

        if((mid-1) >= 0 && nums[mid-1] > nums[mid])
            return mid;
        else if(nums[mid] > nums[end])
            return _findPivot(nums, mid+1, end);
        else
            return _findPivot(nums, begin, mid-1);
    }

    private static int _search(int[] nums, int target, int begin, int end) {
        if(begin > end)
            return -1;

        int mid = (begin+end)/2;

        if(target == nums[mid])
            return mid;
        else if(target < nums[mid])
            return _search(nums, target, begin, mid-1);
        else
            return _search(nums, target, mid+1, end);
    }

    public static void main(String[] args) {
        System.out.println('A' == 65);
    }
}
