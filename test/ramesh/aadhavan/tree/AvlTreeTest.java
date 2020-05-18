package ramesh.aadhavan.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class AvlTreeTest {

    @Test
    public void testAdd() {
        BalancedBinaryTree balancedBinaryTree = new AvlTree();
        IntStream.range(1, 65).forEach(balancedBinaryTree::add);
        Assert.assertEquals(7, balancedBinaryTree.height());
        Assert.assertEquals(32, balancedBinaryTree.getRoot().get().getValue().intValue());
    }

    @Test
    public void testRemove() {
        BalancedBinaryTree balancedBinaryTree = new AvlTree();
        IntStream.range(1, 6).forEach(balancedBinaryTree::add);
        balancedBinaryTree.delete(5);
        balancedBinaryTree.delete(1);
        Assert.assertEquals(3, balancedBinaryTree.getRoot().get().getValue().intValue());
        Assert.assertEquals(2, balancedBinaryTree.height());
    }

    @Test
    public void testRemove_2() {
        BalancedBinaryTree balancedBinaryTree = new AvlTree();
        IntStream.range(1, 6).forEach(balancedBinaryTree::add);
        balancedBinaryTree.delete(1);
        balancedBinaryTree.delete(3);
        Assert.assertEquals(4, balancedBinaryTree.getRoot().get().getValue().intValue());
        Assert.assertEquals(2, balancedBinaryTree.height());
    }
}
