package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

/**
 * The {@code SelfCrossingTest} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class SelfCrossingTest {

    @Test
    public void testIsSelfCrossing() {
        SelfCrossing selfCrossing = new SelfCrossing(0, 0);
        int[] distances = new int[] {2,1,1,2};
        Assert.assertTrue(selfCrossing.isSelfCrossing(distances));
    }

    @Test
    public void testIsSelfCrossing_invalid() {
        SelfCrossing selfCrossing = new SelfCrossing(0,0);
        int[] distances = new int[] {1,2,3,4};
        Assert.assertFalse(selfCrossing.isSelfCrossing(distances));
    }
}
