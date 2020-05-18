package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class MaximumGapTest {
    @Test
    public void testMaximumGap() {
        int[] arr = {3,6,9,1};

        Assert.assertEquals(3, new MaximumGap().maximumGap(arr));
    }
}
