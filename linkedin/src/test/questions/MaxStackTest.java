package questions;

import org.junit.Assert;
import org.junit.Test;

public class MaxStackTest {

    @Test
    public void testPeekMax() {
        final MaxStack maxStack = new MaxStack();

        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(-5);

        Assert.assertEquals(5, maxStack.popMax());
        Assert.assertEquals(1, maxStack.popMax());
        Assert.assertEquals(-5, maxStack.top());
    }
}
