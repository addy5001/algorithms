package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class FourSumTest {

    final FourSum fourSum = new FourSum();

    @Test
    public void testFourSum() {
        int[] nums = {1,0,-1,0,-2,2};
        Assert.assertEquals(3, fourSum.fourSum(nums, 0).size());
    }
}
