package ramesh.aadhavan.dynamic;

import org.junit.Assert;
import org.junit.Test;

public class LongestPalindromicSubstringTest {

    LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();

    @Test
    public void testLongestPalindrome() {
        String s = "bayerreyba";
        Assert.assertEquals("yerrey", longestPalindromicSubstring.longestPalindrome(s));
    }
}
