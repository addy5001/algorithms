package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class MinimumSizeSubArrayTest {

    final MinimumSizeSubArray minimumSizeSubArray = new MinimumSizeSubArray();

    @Test
    public void testMinSubArrayLen() {
        int[] arr = {2,3,1,2,4,3};
        Assert.assertEquals(2, minimumSizeSubArray.minSubArrayLen(7, arr));
    }
}
