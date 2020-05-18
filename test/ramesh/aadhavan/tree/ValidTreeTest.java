package ramesh.aadhavan.tree;

import org.junit.Assert;
import org.junit.Test;

public class ValidTreeTest {
    final ValidTree validTree = new ValidTree();

    @Test
    public void testValidTree() {
        Assert.assertTrue(validTree.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}}));
    }
}
