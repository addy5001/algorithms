package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if(lower == Integer.MAX_VALUE)
            return Collections.emptyList();

        int start = lower;
        List<String> results = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {

            if(i < nums.length-1 && nums[i] == nums[i+1])
                continue;

            if(nums[i] == start) {
                start++;
            }
            else {
                addRanges(results, start, nums[i]-1);
                if(nums[i] == Integer.MAX_VALUE)
                    return results;

                start = nums[i]+1;
            }
        }

        if(start <= upper)
            addRanges(results, start, upper);

        return results;
    }

    private void addRanges(List<String> result, int start, int end) {
        if(start == end)
            result.add(Integer.toString(start));
        else
            result.add(start+"->"+end);
    }
}
