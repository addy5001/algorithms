package questions;

import org.junit.Assert;
import org.junit.Test;

public class ContinuosIncreasingSequenceTest {

    final ContinuosIncreasingSequence continuosIncreasingSequence = new ContinuosIncreasingSequence();

    @Test
    public void testFindLengthOfLCIS() {
        int[] test = {1,3,5,4,2,6,7,8,9};
        Assert.assertEquals(9, continuosIncreasingSequence.findLengthOfLCIS(test, 2));
    }
}
