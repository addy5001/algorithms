package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExpressionAddOperatorsTest {

    final ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();

    @Test
    public void testAddOperators() {
        Assert.assertEquals(List.of("1+2+3", "1*2*3"), expressionAddOperators.addOperators("123", 6));
    }
}
