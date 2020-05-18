package ramesh.aadhavan.dynamic;

import org.junit.Assert;
import org.junit.Test;

public class LongestBitonicSubsequenceTest {

    final LongestBitonicSubsequence bitonic = new LongestBitonicSubsequence();

    @Test
    public void testLengthOfBitonicSubsequence() {

        int[] ints = {2, 4, 7, 3, 2, 1};
        Assert.assertEquals(6, bitonic.lengthOfBitonicSubsequence(ints));
    }
}
