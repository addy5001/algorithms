package questions;

import java.util.*;

public class IntersectionOfArrays {

    /**
     * Time Complexity = O(n)
     * Space Complexity = O(n)
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0)
            return new int[0];

        if(nums2.length < nums1.length) {
            return intersection(nums2, nums1);
        }

        Set<Integer> intersect = new HashSet<>();

        for(int i : nums1)
            intersect.add(i);

        Set<Integer> results = new HashSet<>();

        for (int value : nums2) {
            if (intersect.contains(value))
                results.add(value);
        }

        int[] resultsArr = new int[results.size()];
        Iterator<Integer> setIterator = results.iterator();
        for(int i=0; i<resultsArr.length; i++) {
            resultsArr[i] = setIterator.next();
        }

        return resultsArr;
    }

    /**
     * Time Complexity O(nlogn), if arrays are already sorted O(n)
     * Space Complexity O(1)
     */
    private int[] _intersectionSpaceEfficient(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> intersectionList = new ArrayList<>();

        int nums1Idx = 0;
        int nums2Idx = 0;

        while (nums1Idx < nums1.length && nums2Idx < nums2.length) {
            if(nums1[nums1Idx] < nums2[nums2Idx]) {
                nums1Idx++;
            }
            else if(nums1[nums1Idx] > nums2[nums2Idx]) {
                nums2Idx++;
            }
            else {
                intersectionList.add(nums1[nums1Idx]);

                int num1 = nums1[nums1Idx];
                while(nums1Idx < nums1.length-1 && num1 == nums1[nums1Idx+1])
                    nums1Idx++;

                int num2 = nums2[nums2Idx];
                while (nums2Idx < nums2.length-1 && num2 == nums2[nums2Idx+1])
                    nums2Idx++;

                nums1Idx++;
                nums2Idx++;
            }
        }

        int[] result = new int[intersectionList.size()];
        int k = 0;
        for(int item : intersectionList) {
            result[k++] = item;
        }

        return result;
    }

    /**
     * Follow up
     * Sort the smaller array
     * For every element in bigger array, binary search in smaller sorted array
     * For every match, put it into the intersection list
     * Convert intersection list to array
     */
}
