package questions;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] arr, int target) {
        int[] sumIndices = new int[2];
        for(int i=0; i<arr.length; i++) {
            for(int j=i+1; j< arr.length; j++) {
                if(arr[i] + arr[j] == target) {
                    sumIndices[0] = i;
                    sumIndices[1] = j;
                    return sumIndices;
                }

            }
        }
        return null;
    }

    public static int[] twoSumHashMap(int[] arr, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            int diff = target - arr[i];
            if(sumMap.containsKey(diff)) {
                int[] sumIndices = new int[2];
                sumIndices[0] = i;
                sumIndices[1] = sumMap.get(diff);
                return sumIndices;
            }
            sumMap.put(arr[i], i);
        }
        return null;
    }

    public static List<int[]> twoSumSorted(int[] arr, int target) {
        Arrays.sort(arr);
        int begin = 0;
        int end = arr.length-1;
        List<int[]> list = new ArrayList<>();

        while(begin < end) {
            int twoSum = arr[begin] + arr[end];

            if(twoSum == target) {
                 list.add(new int[] {arr[begin], arr[end]});

                 while(begin < end && arr[begin+1] == arr[begin])
                     begin++;

                 while(begin < end && arr[end-1] == arr[end])
                     end--;

                 begin++;
                 end--;
            }
            else if(twoSum < target) {
                begin++;
            }
            else {
                end--;
            }
        }

        return list;
    }
}
