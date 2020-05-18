package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class KFrequentElementsTest {

    @Test
    public void testGetKFreqElements() {
        int[] elements = new int[]{1,1,1,2,2,3};
        List<Integer> results = KFrequentElements.getKFreqElements(elements, 2);

        Assert.assertEquals(2, results.size());
        Assert.assertArrayEquals(new Integer[] {1,2}, results.toArray(new Integer[0]));
    }

    @Test
    public void testGetKFreqElements_singleElement() {
        int[] elements = new int[]{1};
        List<Integer> results = KFrequentElements.getKFreqElements(elements, 1);

        Assert.assertEquals(1, results.size());
        Assert.assertArrayEquals(new Integer[] {1}, results.toArray(new Integer[0]));
    }
}
