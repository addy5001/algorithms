package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MaxElementOfSubArrayTest {
    @Test
    public void testMaxElementsOfSubArray() {
        int[] test = {4,2,12,34,23,35,44,55};
        Integer[] output = {12,34,34,35,44,55};

        List<Integer> elements = MaxElementOfSubArray.maxElementsOfSubArrayPriorityQueue(test, 3);
        Assert.assertArrayEquals(output, elements.toArray(new Integer[0]));
    }
}
