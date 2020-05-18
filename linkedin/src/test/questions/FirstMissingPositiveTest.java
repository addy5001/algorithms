package questions;

import org.junit.Assert;
import org.junit.Test;

public class FirstMissingPositiveTest {

    final FirstMissingPositive firstMissingPositive = new FirstMissingPositive();

    @Test
    public void testFirstMissingPositive() {
        Assert.assertEquals(3, firstMissingPositive.firstMissingPositive(new int[]{1,2,0}));
    }

    @Test
    public void testFirstMissingPositive_2() {
        Assert.assertEquals(2, firstMissingPositive.firstMissingPositive(new int[]{3,4,-1,1}));
    }

    @Test
    public void testFirstMissingPositive_3() {
        Assert.assertEquals(1, firstMissingPositive.firstMissingPositive(new int[]{7,8,9,11,12}));
    }
}
