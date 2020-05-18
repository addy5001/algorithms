package ramesh.aadhavan.misc;

import java.util.*;
import java.util.stream.IntStream;

public class MaxElementOfSubArray {

    public static List<Integer> maxElementsOfSubArray(int[] arr, int k) {
        List<Integer> maxElements = new ArrayList<>();

        for(int i=0; i<arr.length-k+1; i++) {
            int max = Integer.MIN_VALUE;
            for(int j=i; j<i+k; j++) {
                if(max < arr[j])
                    max = arr[j];
            }
            maxElements.add(max);
        }

        return maxElements;
    }

    static class Item implements Comparable<Item> {
        Integer val;
        int position;

        public Item(Integer val, int position) {
            this.val = val;
            this.position = position;
        }

        @Override
        public int compareTo(Item o) {
            return val - o.val;
        }
    }

    public static List<Integer> maxElementsOfSubArrayPriorityQueue(int[] arr, int k) {
        Queue<Item> queue = new PriorityQueue<>(Comparator.reverseOrder());
        List<Integer> maxElements = new ArrayList<>();

        for(int i=0; i<arr.length; i++) {
            queue.add(new Item(arr[i], i));
            if(i >= k-1) {
                while(!queue.isEmpty() && queue.peek().position < (i-k+1)) {
                    queue.poll();
                }

                if(!queue.isEmpty())
                    maxElements.add(queue.peek().val);
            }
        }

        return maxElements;
    }
}
