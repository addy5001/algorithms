package questions;

import org.junit.Assert;
import org.junit.Test;

public class LongestSubstringWithAtMostKDistinctCharsTest {

    LongestSubstringWithAtMostKDistinctChars longestSubstringWithAtMostKDistinctChars =
            new LongestSubstringWithAtMostKDistinctChars();

    @Test
    public void testSlidingWindowFindString() {
        String test = "eceba";
        Assert.assertEquals("ece",
                longestSubstringWithAtMostKDistinctChars._slidingWindowFindString(test, 2));
    }
}
