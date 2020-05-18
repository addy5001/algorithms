package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MinimumHeightTreesTest {

    final MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();

    @Test
    public void testFindMinHeightTrees() {
        List<Integer> actual = minimumHeightTrees.findMinHeightTrees(6, new int[][] {{0,3}, {1,3}, {2,3}, {4,3}, {5,4}});
        Assert.assertEquals(List.of(3,4), actual);
    }

    @Test
    public void testFindMinHeightTrees_2() {
        List<Integer> actual = minimumHeightTrees.findMinHeightTrees(4, new int[][] {{1,0}, {1,2}, {1,3}});
        Assert.assertEquals(List.of(1), actual);
    }
}
