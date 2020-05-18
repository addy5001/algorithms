package questions;

import org.junit.Assert;
import org.junit.Test;

public class SimplifyPathTest {

    final SimplifyPath simplifyPath = new SimplifyPath();

    @Test
    public void testSimplifyPath() {
        String test = "/a/../../b/../c//.//";
        Assert.assertEquals("/c", simplifyPath.simplifyPath(test));
    }

    @Test
    public void testSimplifyPath_2() {
        String test = "/a//b////c/d//././/..";
        Assert.assertEquals("/a/b/c", simplifyPath.simplifyPath(test));
    }
}
