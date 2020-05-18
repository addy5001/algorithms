package ramesh.aadhavan.misc;

public class MinimumSizeSubArray {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        return _twoPointer(s, nums);
    }

    private int _twoPointer(int s, int[] nums) {
        int fwd = 0, idx = 0;
        int sum = 0;
        Integer minLen = null;

        while(fwd < nums.length) {
            if(sum < s) {
                sum += nums[fwd];
                fwd++;
            }
            else {
                minLen = minLen == null ? fwd - idx : Math.min(minLen, fwd - idx);
                if(idx == fwd-1)
                    return 1;

                sum -= nums[idx];
                idx++;
            }
        }

        while(sum >= s) {
            minLen = Math.min(minLen, fwd - idx);
            sum -= nums[idx];
            idx++;
        }

        return minLen == null ? 0 : minLen;
    }

    private int _naive(int s, int[] nums) {

        int len=1;
        while(len <= nums.length) {
            for(int i=0; i<nums.length-len+1; i++) {
                int sum = rangeSum(nums, i, i+len);
                if(sum >= s) {
                    return len;
                }
            }

            len++;
        }

        return 0;
    }

    private int rangeSum(int[] nums, int begin, int end) {
        int sum = 0;
        for(int i=begin; i<end; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
