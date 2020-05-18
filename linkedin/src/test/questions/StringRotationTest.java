package questions;

import org.junit.Assert;
import org.junit.Test;

public class StringRotationTest {

    final StringRotation stringRotation = new StringRotation();

    @Test
    public void testIsRotation() {
        String initial = "madhu";
        String toCompare = "adhum";

        Assert.assertTrue(stringRotation.isRotation(initial, toCompare));
    }

    @Test
    public void testIsRotation_invalid() {
        String initial = "madhu";
        String toCompare = "amdhu";

        Assert.assertFalse(stringRotation.isRotation(initial, toCompare));
    }

    @Test
    public void testIsRotationOptimized() {
        String initial = "madhu";
        String toCompare = "dhuma";

        Assert.assertTrue(stringRotation.isRotationOptimized(initial, toCompare));
    }

    @Test
    public void testIsRotationOptimized_invalid() {
        String initial = "madhu";
        String toCompare = "amdhu";

        Assert.assertFalse(stringRotation.isRotationOptimized(initial, toCompare));
    }
}
