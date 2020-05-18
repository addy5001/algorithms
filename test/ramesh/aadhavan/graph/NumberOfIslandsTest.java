package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfIslandsTest {

    @Test
    public void testFindIslands() {
        int[][] graph = {
                {1,1,0,1,0},
                {1,1,1,0,0},
                {1,1,0,0,0},
                {0,0,0,0,0}
        };

        Assert.assertEquals(2, NumberOfIslands.findIslands(graph, 4, 5));
    }

    @Test
    public void testFindIslands_3() {
        int[][] graph = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };

        Assert.assertEquals(3, NumberOfIslands.findIslands(graph, 4, 5));
    }

    @Test
    public void testFindIslands_1() {
        int[][] graph = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,1,1,0,1}
        };

        Assert.assertEquals(1, NumberOfIslands.findIslands(graph, 3, 5));
    }

    @Test
    public void testMaxAreaOfIsland() {
        int[][] graph = {
                {1,1,0,1,0},
                {1,1,1,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1}
        };

        Assert.assertEquals(7, NumberOfIslands.maxAreaOfIsland(graph));
    }

    @Test
    public void testMaxAreaOfIsland_3() {
        int[][] graph = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };

        Assert.assertEquals(4, NumberOfIslands.maxAreaOfIsland(graph));
    }

    @Test
    public void testMaxAreaOfIsland_1() {
        int[][] graph = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,1,1,0,1}
        };

        Assert.assertEquals(11, NumberOfIslands.maxAreaOfIsland(graph));
    }
}
