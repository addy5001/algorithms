package questions;

import org.junit.Assert;
import org.junit.Test;

public class MinWindowSubstringTest {

    final MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();

    @Test
    public void testMinWindow() {
        String s = "ABAACBAB";
        String t = "ABC";

        Assert.assertEquals("ACB", minimumWindowSubstring.minWindow(s, t));
    }

    @Test
    public void testMinWindow_2() {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        Assert.assertEquals("BANC", minimumWindowSubstring.minWindow(s, t));
    }
}
