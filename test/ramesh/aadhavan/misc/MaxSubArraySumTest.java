package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class MaxSubArraySumTest {

    @Test
    public void testMaxSubArraySumDivideAndConquer() {
        int[] nums = {1, 0, 3, -3, 4, 5};
        Assert.assertEquals(10, MaxSubArraySum.maxSubArraySumDivideAndConquer(nums));
    }

    @Test
    public void testMaxSubArraySumKadaneAlgorithm() {
        int[] nums = {1, 0, 3, -3, 4, 5};
        Assert.assertEquals(10, MaxSubArraySum.maxSubArraySumKadaneAlgorithm(nums));
    }

    @Test
    public void testMaxSubArraySumKadaneAlgorithm_allNegative() {
        int[] nums = {-1, -3, -3, -4, -5};
        Assert.assertEquals(-1, MaxSubArraySum.maxSubArraySumKadaneAlgorithm(nums));
    }
}
