package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class TrappingWaterTest {

    final TrappingWater trappingWater = new TrappingWater();

    @Test
    public void testTrap() {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trapped = trappingWater.trap(arr);
        Assert.assertEquals(6, trapped);
    }
}
