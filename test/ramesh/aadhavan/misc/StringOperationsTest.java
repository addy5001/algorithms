package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StringOperationsTest {
    @Test
    public void testIsSubsequence() {
        Assert.assertTrue(StringOperations.isSubsequence("atx", "aaaatfdgfsxc"));
    }

    @Test
    public void testIsSubsequence_invalid() {
        Assert.assertFalse(StringOperations.isSubsequence("atx", "aaaatfdgfsc"));
    }

    @Test
    public void testIsSubsequence_invalid_2() {
        Assert.assertFalse(StringOperations.isSubsequence("atx", ""));
    }

    @Test
    public void testIsSubsequence_valid_2() {
        Assert.assertTrue(StringOperations.isSubsequence("", "dsds"));
    }

    @Test
    public void testCommonChars() {
        String[] A = {"bella","label","roller"};
        StringOperations stringOperations = new StringOperations();

        Assert.assertEquals(3, stringOperations.commonChars(A).size());
    }

    @Test
    public void testLengthOfLongestSubstringOptimized() {
        StringOperations stringOperations = new StringOperations();
        Assert.assertEquals(3, stringOperations.lengthOfLongestSubstringOptimized("abca"));
        Assert.assertEquals(4, stringOperations.lengthOfLongestSubstringOptimized("bbcad"));
    }

    @Test
    public void emptyMethod() {
        String s = "cbaebabacd";
        String p = "abc";
    }
}
