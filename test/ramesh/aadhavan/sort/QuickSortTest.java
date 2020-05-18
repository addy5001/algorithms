package ramesh.aadhavan.sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {

    @Test
    public void testSort() {
        int[] arr = {23, 1, 15, 4, 7, 54, 21, 76, 3};
        int[] expected = {1, 3, 4, 7, 15, 21, 23, 54, 76};
        QuickSort.sort(arr);

        Assert.assertArrayEquals(expected, arr);
    }

}
