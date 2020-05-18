package ramesh.aadhavan.misc;

import java.util.Arrays;

public class PartitionToKEqualSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        return _canPartitionKSubsets(nums, k);
    }

    private boolean _canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if(sum % k != 0)
            return false;

        int share = sum/k;
        Arrays.sort(nums);
        int end = nums.length - 1;

        if(nums[end] > share)
            return false;

        while(nums[end] == share) {
            k--;
            end--;
        }

        int[] buckets = new int[k];
        return _backtrack(nums, end, share, buckets);
    }

    private boolean _backtrack(int[] nums, int end, int share, int[] buckets) {
        if(end < 0)
            return true;

        for(int i=0; i<buckets.length; i++) {
            if(buckets[i] + nums[end] <= share) {
                buckets[i] += nums[end];
                if(_backtrack(nums, end-1, share, buckets))
                    return true;
                buckets[i] -= nums[end];
            }

            if(buckets[i] == 0)
                break;
        }

        return false;
    }
}
