package questions;

import org.junit.Assert;
import org.junit.Test;

public class MaximumSumOfNonOverlappingSubarraysTest {

    final MaximumSumOfNonOverlappingSubarrays maximumSumOfNonOverlappingSubarrays =
            new MaximumSumOfNonOverlappingSubarrays();

    @Test
    public void testMaxSumOfThreeSubarrays() {
        int[] test = {1,2,1,2,6,7,5,1};

        Assert.assertEquals(3, maximumSumOfNonOverlappingSubarrays.maxSumOfThreeSubarrays(test, 2).length);
    }
}
