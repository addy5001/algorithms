package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class MinimumToRemoveParenthesesTest {

    final MinimumToRemoveParentheses minimumToRemoveParentheses = new MinimumToRemoveParentheses();

    @Test
    public void testMinRemoveToMakeValid() {
        String test = "lee(t(c)o)de)";
        Assert.assertEquals("lee(t(c)o)de", minimumToRemoveParentheses.minRemoveToMakeValid(test));
    }

    @Test
    public void testMinRemoveToMakeValid_2() {
        String test = "))((";
        Assert.assertEquals("", minimumToRemoveParentheses.minRemoveToMakeValid(test));
    }
}
