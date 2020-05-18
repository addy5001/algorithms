package questions;

import org.junit.Assert;
import org.junit.Test;

public class DivideTwoIntegersTest {

    final DivideTwoIntegers divideTwoIntegers = new DivideTwoIntegers();

    @Test
    public void testDivideBySubtraction_1() {
        Assert.assertEquals(111, divideTwoIntegers._divisionBySubtraction(999, 9));
    }

    @Test
    public void testDivideBySubtraction_2() {
        Assert.assertEquals(-13, divideTwoIntegers._divisionBySubtraction(-340, 25));
    }

    @Test
    public void testDivideBySubtraction_3() {
        Assert.assertEquals(Integer.MIN_VALUE+1, divideTwoIntegers._divisionBySubtraction(Integer.MIN_VALUE+1, 1));
    }

    @Test
    public void testDivideBySubtractionWithOverflow_1() {
        Assert.assertEquals(Integer.MIN_VALUE,
                divideTwoIntegers._divisionBySubtractionWithOverflow(Integer.MIN_VALUE, 1));
    }
}
