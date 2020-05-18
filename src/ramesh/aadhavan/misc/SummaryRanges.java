package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        int endRange = 1;
        Integer prevVal = nums[0];
        Integer firstVal = nums[0];

        while(endRange < nums.length) {
            if(nums[endRange] == prevVal + 1) {
                if(endRange == nums.length-1) {
                    ranges.add(firstVal+"->"+nums[endRange]);
                }
            }
            else {
                if(firstVal == prevVal) {
                    ranges.add(firstVal+"");
                }
                else {
                    ranges.add(firstVal+"->"+prevVal);
                }

                if(endRange == nums.length - 1) {
                    ranges.add(nums[endRange]+"");
                }

                firstVal = nums[endRange];
            }

            prevVal = nums[endRange];
            endRange++;
        }

        return ranges;
    }
}
