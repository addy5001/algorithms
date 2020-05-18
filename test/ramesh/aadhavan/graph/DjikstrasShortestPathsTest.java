package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class DjikstrasShortestPathsTest {

    @Test
    public void testShortestPaths() {
        int[][] g = new int[][] {
                {0, 2, 1, 0, 0, 0, 0},
                {0, 0, 3, 0, 0, 0, 0},
                {0, 0, 0, 5, 0, 0, 12},
                {0, 0, 0, 0, 0, 6, 6},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };

        int[] expected = {0, 2, 1, 6, Integer.MAX_VALUE, 12, 12};
        Assert.assertArrayEquals(expected, DjikstrasShortestPaths.shortestPaths(g, 0));
    }
}
