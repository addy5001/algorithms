package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4)
            return Collections.emptyList();

        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {
            int j = i+1;

            while(j < nums.length) {
                int k = j+1;
                int p = nums.length - 1;
                while(k < p) {
                    int fourSum = nums[i] + nums[j] + nums[k] + nums[p];

                    if(fourSum == target) {
                        results.add(List.of(nums[i], nums[j], nums[k], nums[p]));

                        while(k < p && nums[k+1] == nums[k])
                            k++;

                        while(k < p && nums[p-1] == nums[p])
                            p--;

                        k++;
                        p--;
                    }
                    else if(fourSum < target) {
                        k++;
                    }
                    else {
                        p--;
                    }
                }

                while(j < nums.length-1 && nums[j] == nums[j+1])
                    j++;

                j++;
            }

            while(i < nums.length-1 && nums[i] == nums[i+1])
                i++;
        }

        return results;
    }
}
