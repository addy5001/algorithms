package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class PostfixExpressionTest {

    @Test
    public void testEvaluate() {
        String[] operands = {"5", "1", "2", "+", "4", "*", "+", "3", "-"};
        PostfixExpression postfixExpression = new PostfixExpression();
        Assert.assertEquals(14, postfixExpression.evaluate(operands));
    }
}
