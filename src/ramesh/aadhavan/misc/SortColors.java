package ramesh.aadhavan.misc;

import java.util.Arrays;

public class SortColors {
    public void sortColors(int[] nums) {
        _countingSortApproach(nums);
    }

    private void _countingSortApproach(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }

        int[] countingArray = new int[3];

        for(int num : nums) {
            countingArray[num]++;
        }

        int j = 0;
        int k = 0;

        while(j < 3) {
            if(countingArray[j] != 0) {
                nums[k++] = j;
                countingArray[j]--;
            }
            else {
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
