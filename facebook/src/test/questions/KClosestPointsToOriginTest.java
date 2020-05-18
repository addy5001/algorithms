package questions;

import org.junit.Assert;
import org.junit.Test;

public class KClosestPointsToOriginTest {

    final KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();

    @Test
    public void testKClosest() {
        int[][] test = {{1,3},{-2,2}};
        int[][] results = kClosestPointsToOrigin.kClosest(test, 1);
        Assert.assertEquals(1, results.length);
        Assert.assertArrayEquals(new int[]{-2, 2}, results[0]);
    }

    @Test
    public void testKClosest_2() {
        int[][] test = {{3,3},{5,-1},{-2,4}};
        int[][] results = kClosestPointsToOrigin.kClosest(test, 2);
        Assert.assertEquals(2, results.length);
        Assert.assertArrayEquals(new int[]{3, 3}, results[0]);
        Assert.assertArrayEquals(new int[]{-2, 4}, results[1]);
    }
}
