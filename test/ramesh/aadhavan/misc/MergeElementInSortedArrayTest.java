package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class MergeElementInSortedArrayTest {

    final MissingElementInSortedArray missingElementInSortedArray = new MissingElementInSortedArray();

    @Test
    public void testMissingElement() {
        int[] test = {4,7,9,10};
        Assert.assertEquals(8, missingElementInSortedArray.missingElement(test, 3));
    }


    @Test
    public void testMissingElement_2() {
        int[] test = {1,2,4};
        Assert.assertEquals(6, missingElementInSortedArray.missingElement(test, 3));
    }

    @Test
    public void testMissingElement_3() {
        int[] test = {746421,1033196,1647541,4775111,7769817,8030384};
        Assert.assertEquals(746431, missingElementInSortedArray.missingElement(test, 10));
    }
}
