package ramesh.aadhavan.misc;

import java.util.Arrays;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        return _boyerMooreVotingAlgorithm(nums);
    }

    private int _sortApproach(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int majority = n/2;

        int i=0, j=1;
        int elem = nums[0];
        int count = 1;
        while(j < nums.length) {
            if(nums[j] == nums[i]) {
                count++;
                if(count > majority)
                    return nums[j];
            }
            else {
                count = 1;
                i = j;
            }

            j++;
        }

        return -1;
    }

    private int _boyerMooreVotingAlgorithm(int[] nums) {
        int count = 0;
        Integer elem = null;

        for(int i=0; i<nums.length; i++) {
            if(count == 0) {
                elem = nums[i];
            }

            if(elem == nums[i])
                count++;
            else
                count--;
        }

        return elem;
    }
}
