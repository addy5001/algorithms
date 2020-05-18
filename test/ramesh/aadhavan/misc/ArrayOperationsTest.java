package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class ArrayOperationsTest {

    @Test
    public void testPlusOne() {
        int[] digits = new int[]{1, 5, 1, 0, 9, 9, 9, 9, 9, 9};
        int[] expected = new int[]{1, 5, 1, 1, 0, 0, 0, 0, 0, 0};
        Assert.assertArrayEquals(expected, ArrayOperations.plusOne(digits));
    }

    @Test
    public void testPlusOne_singleDigit() {
        int[] digits = new int[]{0};
        int[] expected = new int[]{1};
        Assert.assertArrayEquals(expected, ArrayOperations.plusOne(digits));
    }

    @Test
    public void testPlusOne_withCarry() {
        int[] digits = new int[]{9};
        int[] expected = new int[]{1,0};
        Assert.assertArrayEquals(expected, ArrayOperations.plusOne(digits));
    }

    @Test
    public void testPlusOne_withCarryBigDigit() {
        int[] digits = new int[]{9,9,9,9,9,9};
        int[] expected = new int[]{1,0,0,0,0,0,0};
        Assert.assertArrayEquals(expected, ArrayOperations.plusOne(digits));
    }

    @Test
    public void testMoveZeroes() {
        int[] arr = new int[]{0,1,0,3,12};
        ArrayOperations.moveZeroes(arr);
        System.out.println(arr.toString());
    }

    @Test
    public void testSumDivideAndConquer() {
        int[] arr = new int[]{0,1,0,3,12};
        Assert.assertEquals(16, ArrayOperations.sumDivideAndConquer(arr));
    }

    @Test
    public void testFindErrorNums() {
        int[] arr = {1, 2, 2, 4};
        Assert.assertArrayEquals(new int[]{2, 3}, ArrayOperations.findErrorNums(arr));
    }

    @Test
    public void testSearchRange() {
        int[] arr = {5,7,7,8,8,10};
        ArrayOperations arrayOperations = new ArrayOperations();
        Assert.assertArrayEquals(new int[]{3, 4}, arrayOperations.searchRange(arr, 8));
    }

    @Test
    public void testRemoveDuplicates() {
        int[] arr = {3,3,3,3,3,3,3};
        ArrayOperations arrayOperations = new ArrayOperations();
        Assert.assertEquals(1, arrayOperations.removeDuplicates(arr));

        int[] arr1 = {1,2,3,4,5};
        Assert.assertEquals(5, arrayOperations.removeDuplicates(arr1));

        int[] arr2 = {2, 2, 3, 3, 4, 4, 5, 6, 7, 7, 7, 7, 8};
        Assert.assertEquals(7, arrayOperations.removeDuplicates(arr2));

        int[] arr3 = {1, 1, 3, 4, 5, 6, 6, 6};
        Assert.assertEquals(5, arrayOperations.removeDuplicates(arr3));
    }

    @Test
    public void testRotate() {
        int[] arr = {1,2};
        int[] expected = {2,1};
        new ArrayOperations().rotate(arr, 3);
        Assert.assertArrayEquals(expected, arr);
    }
}
