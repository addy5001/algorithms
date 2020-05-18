package ramesh.aadhavan.numbers;

import java.util.Arrays;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numStrings = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            numStrings[i] = Integer.toString(nums[i]);
        }

        Arrays.sort(numStrings, (x, y) -> y.concat(x).compareTo(x.concat(y)));
        int startIdx = 0;
        while(numStrings[startIdx].equals("0") && ((startIdx+1) < numStrings.length && numStrings[startIdx+1].equals("0")))
            startIdx++;

        if(numStrings[startIdx].equals("0") && (startIdx < numStrings.length-1 && !numStrings[startIdx+1].equals("0")))
            startIdx++;

        return Arrays.stream(numStrings, startIdx, numStrings.length).reduce("", String::concat);
    }
}
