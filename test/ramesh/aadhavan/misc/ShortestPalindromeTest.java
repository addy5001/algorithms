package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class ShortestPalindromeTest {

    final ShortestPalindrome shortestPalindrome = new ShortestPalindrome();

    @Test
    public void testShortestPalindrome() {
        String s = "aababababababa";
        Assert.assertEquals("aaacecaaa", shortestPalindrome.shortestPalindrome(s));
    }
}
