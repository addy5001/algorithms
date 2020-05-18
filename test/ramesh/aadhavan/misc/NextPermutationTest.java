package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class NextPermutationTest {

    final NextPermutation nextPermutation = new NextPermutation();

    @Test
    public void testNextPermutation() {
        int[] nums = {7, 6, 5, 4, 3, 2};
        Integer[] expected = {2, 3, 4, 5, 6, 7};

        Iterator<Integer[]> iterator = nextPermutation.getIterator(nums);
        Assert.assertArrayEquals(expected, iterator.next());

        Integer[] expected_2 = {2, 3, 4, 5, 7, 6};
        Assert.assertArrayEquals(expected_2, iterator.next());

        Integer[] expected_3 = {2, 3, 4, 6, 5, 7};
        Assert.assertArrayEquals(expected_3, iterator.next());
    }

    @Test
    public void testNextPermutation_2() {
        int[] nums = {1, 1};
        Integer[] expected = {1, 1};

        Iterator<Integer[]> iterator = nextPermutation.getIterator(nums);
        Assert.assertArrayEquals(expected, iterator.next());
    }

    @Test
    public void testNextPermutation_3() {
        int[] nums = {2, 3, 1};
        Integer[] expected = {3, 1, 2};

        Iterator<Integer[]> iterator = nextPermutation.getIterator(nums);
        Assert.assertArrayEquals(expected, iterator.next());
    }
}
