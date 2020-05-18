package questions;

import org.junit.Assert;
import org.junit.Test;

public class ReorganizeStringTest {

    final ReorganizeString reorganizeString = new ReorganizeString();

    @Test
    public void testReorganizeString() {
        Assert.assertEquals("aba", reorganizeString.reorganizeString("aab"));
    }

    @Test
    public void testReorganizeString_invalid() {
        Assert.assertEquals("", reorganizeString.reorganizeString("aaab"));
    }

    @Test
    public void testReorganizeString_2() {
        Assert.assertEquals("abdcacdfbe", reorganizeString.reorganizeString("aabbccddef"));
    }
}
