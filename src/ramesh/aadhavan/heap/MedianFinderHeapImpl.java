package ramesh.aadhavan.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinderHeapImpl {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinderHeapImpl() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());

        if(minHeap.size() < maxHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(minHeap.size() > maxHeap.size()) {
            return (double) minHeap.peek();
        }
        else {
            return (double) (minHeap.peek() + maxHeap.peek())/2.0;
        }
    }
}
