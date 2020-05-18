package questions;

import java.util.*;
import java.util.stream.IntStream;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        return select(nums, nums.length - k);
    }

    private int _findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<nums.length; i++) {
            queue.add(nums[i]);
        }

        while(--k > 0) {
            queue.poll();
        }

        return queue.peek();
    }

    private int _findKthLargest_sort(int[] nums, int k) {
        Integer[] arr = IntStream.of(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        return arr[k-1];
    }

    private int partition(int[] arr, int low, int high) {
        int i=low;
        int j= high+1;

        while(true) {
            while(i < high && arr[++i] < arr[low]);
            while (j > low && arr[low] < arr[--j]);

            if(i>=j) break;

            int temp = arr[i];
            arr[i]   = arr[j];
            arr[j]   = temp;
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j]   = temp;

        return j;
    }

    public int select(int[] arr, int k) {
        int low = 0;
        int high = arr.length-1;
        int n = arr.length;

        while(low <= high) {
            int pivotPos = partition(arr, low, high);

            if(pivotPos == k)
                return arr[pivotPos];
            else if(pivotPos < k)
                low = pivotPos + 1;
            else
                high = pivotPos - 1;
        }

        return -1;
    }
}
