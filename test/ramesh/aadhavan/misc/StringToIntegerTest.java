package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class StringToIntegerTest {

    final StringToInteger stringToInteger = new StringToInteger();

    @Test
    public void testMyAtoi() {
        Assert.assertEquals(Integer.MAX_VALUE - 3, stringToInteger.myAtoi(Integer.toString(Integer.MAX_VALUE - 3)));
    }
}
