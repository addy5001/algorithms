package questions;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortedArraysTest {

    final MergeSortedArrays mergeSortedArrays = new MergeSortedArrays();

    @Test
    public void testMergeSortedArrays() {
        int[] A = {1,3,4,7,9,11,45,67};
        int[] B = {1,2,4,6,10,12,44,76};

        int[] expected = {1,1,2,3,4,4,6,7,9,10,11,12,44,45,67,76};
        Assert.assertArrayEquals(expected, mergeSortedArrays.mergeSortedArrays(A, B));
    }

    @Test
    public void testMergeSortedArraysInPlace() {
        int[] A = {1,3,4,7,9,11,45,67,100,101,120,0,0,0,0,0,0,0,0};
        int[] B = {1,2,4,6,10,12,44,76};

        int[] expected = {1,1,2,3,4,4,6,7,9,10,11,12,44,45,67,76,100,101,120};
        Assert.assertArrayEquals(expected, mergeSortedArrays.mergeSortedArraysInPlace(A, 11, B, 8));
    }
}
