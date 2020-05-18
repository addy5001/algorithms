package questions;

import org.junit.Assert;
import org.junit.Test;

public class StrStrTest {

    final StrStr strStr = new StrStr();

    @Test
    public void testStrStr_1() {
        Assert.assertEquals(2, strStr.strStr("hello", "ll"));
    }

    @Test
    public void testStrStr_2() {
        Assert.assertEquals(-1, strStr.strStr("hello", "bba"));
    }
}
