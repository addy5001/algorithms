package ramesh.aadhavan.graph;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class DirectedTopologicalSortTest {

    @Test
    public void testPathExists_validPath() {
        int[][] g = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0}
        };

        Assert.assertTrue(DirectedGraph.pathExists(g, 1, 2, 4, new HashSet<>()));
    }

    @Test
    public void testPathExists_noPath() {
        int[][] g = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0}
        };

        Assert.assertFalse(DirectedGraph.pathExists(g, 0, 2, 4, new HashSet<>()));
    }

    @Test
    public void testPathExists_cyclic() {
        int[][] g = {
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {1, 0, 0, 0}
        };

        Assert.assertTrue(DirectedGraph.pathExists(g, 2, 1, 4, new HashSet<>()));
    }
}
