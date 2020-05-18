package ramesh.aadhavan.heap;

import org.junit.Assert;
import org.junit.Test;

public class SlidingWindowMaximumTest {
    final SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();

    @Test
    public void testMaxSlidingWindow() {
        int[] test = {1,3,-1,-3,5,3,6,7};
        int[] expected = {3,3,5,5,6,7};

        Assert.assertArrayEquals(expected, slidingWindowMaximum.maxSlidingWindow(test, 3));
    }

    @Test
    public void testMaxSlidingWindow_2() {
        int[] test = {10,9,8,7,6,5,4,3,2,1};
        int[] expected = {10,9,8,7,6,5,4,3};

        Assert.assertArrayEquals(expected, slidingWindowMaximum.maxSlidingWindow(test, 3));
    }
}
