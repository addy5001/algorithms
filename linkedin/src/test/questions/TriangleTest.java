package questions;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

    final Triangle triangle = new Triangle();

    @Test
    public void testIsTriangle() {
        int[] test = {5, 4, 3, 1, 2};
        Assert.assertTrue(triangle.isTriangle(test));
    }
}
