package ramesh.aadhavan.list;

import org.junit.Assert;
import org.junit.Test;

/**
 * The {@code StackUsingQueuesTest} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class StackUsingQueuesTest {

    @Test
    public void testPush() {
        StackUsingQueues stackUsingQueues = new StackUsingQueues();
        stackUsingQueues.push(2);
        stackUsingQueues.push(3);
        stackUsingQueues.push(4);
        stackUsingQueues.push(5);

        Assert.assertEquals(5, stackUsingQueues.top());
    }

    @Test
    public void testPop() {
        StackUsingQueues stackUsingQueues = new StackUsingQueues();
        stackUsingQueues.push(2);
        stackUsingQueues.push(3);
        stackUsingQueues.push(4);

        Assert.assertEquals(4, stackUsingQueues.pop());
        Assert.assertEquals(3, stackUsingQueues.top());

        Assert.assertEquals(3, stackUsingQueues.pop());
        Assert.assertEquals(2, stackUsingQueues.top());

        Assert.assertEquals(2, stackUsingQueues.pop());
    }

    @Test
    public void testEmpty() {
        StackUsingQueues stackUsingQueues = new StackUsingQueues();
        Assert.assertTrue(stackUsingQueues.empty());

        stackUsingQueues.push(1);
        Assert.assertFalse(stackUsingQueues.empty());

        stackUsingQueues.pop();
        Assert.assertTrue(stackUsingQueues.empty());
    }
}
