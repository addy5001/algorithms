package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class MissingNumberInIncreasingSequenceTest {

    final MissingNumberInIncreasingSequence missing = new MissingNumberInIncreasingSequence();

    @Test
    public void testFindMissingNumber() {
        int[] arr = {1,2,4,5};
        Assert.assertEquals(3, missing.findMissingNumber(arr));
    }

    @Test
    public void testFindMissingNumber_2() {
        int[] arr = {1,2,3,4,5,6,8,9};
        Assert.assertEquals(7, missing.findMissingNumber(arr));
    }

    @Test
    public void testFindMissingNumber_3() {
        int[] arr = {2,3,4,5,6,7,8};
        Assert.assertEquals(1, missing.findMissingNumber(arr));
    }

    @Test
    public void testFindMissingNumber_4() {
        int[] arr = {1,2,3,4,5,6,7,8};
        Assert.assertEquals(9, missing.findMissingNumber(arr));
    }
}
