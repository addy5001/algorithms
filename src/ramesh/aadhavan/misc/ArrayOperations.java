package ramesh.aadhavan.misc;

import java.util.*;

public class ArrayOperations {

    public static class CharOperations {
        public static void reverseChars(char[] arr) {
            reverseChars(arr, 0, arr.length-1);
        }

        private static void reverseChars(char[] arr, int start, int end) {
            if(start >= end)
                return;

            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            reverseChars(arr, start+1, end-1);
        }
    }

    public static int count(int[] array) {
        if(array.length == 1)
            return 1;
        return 1 + count(Arrays.copyOf(array, array.length - 1));
    }

    public static int sum(int[] array) {
        if(array.length == 1)
            return array[0];
        return array[0] + sum(Arrays.copyOfRange(array, 1, array.length));
    }

    public static int binarySearch(int[] array, int element, int start, int end) {
        if(start <= end) {
            int mid = (end + start)/2;

            if(element == array[mid])
                return mid;

            else if (array[mid] > element)
                return binarySearch(array, element, start, mid - 1);

            return binarySearch(array, element, mid + 1, end);
        }

        return -1;
    }

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

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 6, 23, 12, 43, 14};
        int[] sum = ArrayOperations.twoSumHashMap(arr, 7);
        if(Objects.nonNull(sum))
            System.out.println(arr[sum[0]]+" "+ arr[sum[1]]);
        else
            System.out.println("No two numbers adding to target");
    }
}
