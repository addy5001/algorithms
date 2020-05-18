package ramesh.aadhavan.misc;

import org.junit.Test;
import ramesh.aadhavan.heap.MedianFinder;

public class MedianFinderTest {

    @Test
    public void testFindMedian() {
        final MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(4);
        medianFinder.addNum(5);
        medianFinder.addNum(1);
        medianFinder.addNum(7);
        medianFinder.addNum(6);
        medianFinder.addNum(10);
        medianFinder.addNum(11);

        System.out.println(medianFinder.findMedian());
    }
}
