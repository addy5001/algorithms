package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class BipartiteGraphTest {

    final BipartiteGraph bipartiteGraph = new BipartiteGraph();

    @Test
    public void testIsBipartite() {
        int[][] graph = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};

        Assert.assertTrue(bipartiteGraph.isBipartite(graph));
    }

    @Test
    public void testIsBipartite_2() {
        int[][] graph = {{1, 2, 3}, {0, 3}, {0, 3}, {0, 1, 2}};

        Assert.assertFalse(bipartiteGraph.isBipartite(graph));
    }
}
