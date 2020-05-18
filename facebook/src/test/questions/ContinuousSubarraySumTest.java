package questions;

import org.junit.Assert;
import org.junit.Test;

public class ContinuousSubarraySumTest {

    final ContinuousSubarraySum continuousSubarraySum = new ContinuousSubarraySum();

    @Test
    public void testCheckSubarraySum() {
        int[] arr = {23, 2, 4, 6, 7};
        int k = 6;

        Assert.assertTrue(continuousSubarraySum.checkSubarraySum(arr, k));
    }

    @Test
    public void testCheckSubarraySum_2() {
        int[] arr = {23, 2, 4, 6, 7};
        int k = 19;

        Assert.assertTrue(continuousSubarraySum.checkSubarraySum(arr, k));
    }
}
