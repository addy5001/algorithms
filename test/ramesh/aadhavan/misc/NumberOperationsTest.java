package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NumberOperationsTest {

    @Test
    public void testSumOf1sAnd2s() {
        List<List<Integer>> actual = NumberOperations.sumOf1sAnd2s(100);
        Assert.assertEquals(2, actual.size());
    }

    @Test
    public void testSqrt() {
        Assert.assertEquals(4.0, NumberOperations.sqrt(16));
    }
}
