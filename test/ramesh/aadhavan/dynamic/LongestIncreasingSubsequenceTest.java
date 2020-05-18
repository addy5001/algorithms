package ramesh.aadhavan.dynamic;

import org.junit.Assert;
import org.junit.Test;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void testFindSubsequence() {
        int[] nums = {10,9,2,5,3,7,101,18};

        Assert.assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(nums));

    }
}
