package ramesh.aadhavan.heap;

import java.util.*;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        return _maxSlidingWindowDeque(nums, k);
    }

    private int[] _maxSlidingWindowPriorityQueue(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return new int[0];

        int[] maxWindows = new int[nums.length - k + 1];
        int idx = 0;
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> nums[(int) i]).reversed());
        for(int i=0; i<k-1; i++) {
            queue.add(i);
        }

        for(int i=k-1; i<nums.length; i++) {
            queue.offer(i);
            while(queue.peek() < i-k+1)
                queue.poll();

            maxWindows[idx++] = nums[queue.peek()];
        }

        return maxWindows;
    }

    private int[] _maxSlidingWindowDeque(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] maxWindows = new int[nums.length - k + 1];
        int idx = 0;

        for(int i=0; i<k-1; i++) {
            if(deque.isEmpty())
                deque.addLast(i);
            else {
                while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                    deque.removeLast();

                deque.addLast(i);
            }
        }

        for(int i=k-1; i<nums.length; i++) {
            while(!deque.isEmpty() && (nums[deque.peekLast()] < nums[i] || deque.peekLast() < i-k+1))
                deque.removeLast();

            while(!deque.isEmpty() && deque.peekFirst() < i-k+1)
                deque.removeFirst();

            deque.addLast(i);

            maxWindows[idx++] = nums[deque.peekFirst()];
        }

        return maxWindows;
    }
}
