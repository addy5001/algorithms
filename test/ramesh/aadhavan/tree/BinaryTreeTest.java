package ramesh.aadhavan.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BinaryTreeTest {

    private BinaryTree testTree = new BinaryTree();
    private BinaryTree partialTree = new BinaryTree();

    @Before
    public void init() {
        testTree.root = new BinaryTree.BNode(10);
        testTree.root.left = new BinaryTree.BNode(5);
        testTree.root.right = new BinaryTree.BNode(15);
        testTree.root.left.right = new BinaryTree.BNode(2);
        testTree.root.left.left = new BinaryTree.BNode(1);

        partialTree.root = new BinaryTree.BNode(500);
        partialTree.root.left = new BinaryTree.BNode(1500);
        partialTree.root.right = new BinaryTree.BNode(2500);
        partialTree.root.left.right = new BinaryTree.BNode(3500);
        partialTree.root.right.left = new BinaryTree.BNode(4500);
    }

    @Test
    public void testIsFull_fullTree() {
        Assert.assertTrue(testTree.isFull());
    }

    @Test
    public void testIsFull_invalid() {
        Assert.assertFalse(partialTree.isFull());
    }

    @Test
    public void testIsFullIterative_fullTree() {
        Assert.assertTrue(testTree.isFull());
    }

    @Test
    public void testIsFullIterative_invalid() {
        Assert.assertFalse(partialTree.isFull());
    }

    @Test
    public void testIsComplete_completeTree() {
        Assert.assertTrue(testTree.isComplete());
    }

    @Test
    public void testIsComplete_invalid() {
        Assert.assertFalse(partialTree.isComplete());
    }

    @Test
    public void testIsComplete_invalid_1() {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.BNode(10);
        tree.root.left = new BinaryTree.BNode(5);
        tree.root.right = new BinaryTree.BNode(15);
        tree.root.left.right = new BinaryTree.BNode(2);
        tree.root.left.left = new BinaryTree.BNode(1);
        tree.root.left.left.left = new BinaryTree.BNode(2);
        tree.root.left.left.right = new BinaryTree.BNode(3);

        Assert.assertFalse(tree.isComplete());
    }

    @Test
    public void testIsComplete_invalid_2() {
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.BNode(1);
        tree.root.left = new BinaryTree.BNode(2);
        tree.root.right = new BinaryTree.BNode(3);
        tree.root.left.left = new BinaryTree.BNode(4);
        tree.root.left.right = new BinaryTree.BNode(5);
        tree.root.right.left = new BinaryTree.BNode(6);
        tree.root.right.right = new BinaryTree.BNode(7);
        tree.root.left.left.left = new BinaryTree.BNode(8);
        tree.root.left.left.right = new BinaryTree.BNode(9);
        tree.root.left.right.left = new BinaryTree.BNode(10);
        tree.root.left.right.right = new BinaryTree.BNode(11);
        tree.root.right.left.left = new BinaryTree.BNode(12);
        tree.root.right.left.right = new BinaryTree.BNode(13);
        tree.root.left.left.left.left = new BinaryTree.BNode(15);

        Assert.assertFalse(tree.isComplete());
    }

    @Test
    public void testCompleteBinaryTreeInsert() {
        BinaryTree tree = new BinaryTree();
        IntStream.range(0, 10000).forEach(tree::completeBinaryTreeInsert);
        String message = "1043200";
        message.toCharArray();
        Assert.assertTrue(tree.isComplete());
    }

    @Test
    public void testIsSubtree() {
        BinaryTree tree = new BinaryTree();
        Arrays.stream(new int[]{3,4,5,1,2}).forEach(tree::completeBinaryTreeInsert);
        BinaryTree t = new BinaryTree();
        Arrays.stream(new int[]{4,1,2}).forEach(t::completeBinaryTreeInsert);

        Assert.assertTrue(tree.isSubtree(tree.root, t.root));
    }

    @Test
    public void testSumRootToLeafBinary() {
        BinaryTree btree = new BinaryTree();
        btree.root = new BinaryTree.BNode(1);
        btree.root.left = new BinaryTree.BNode(0);
        btree.root.left.left = new BinaryTree.BNode(0);
        btree.root.left.right = new BinaryTree.BNode(1);
        btree.root.right = new BinaryTree.BNode(1);
        btree.root.right.left = new BinaryTree.BNode(0);
        btree.root.right.right = new BinaryTree.BNode(1);

        Assert.assertEquals(22, btree.sumRootToLeafBinary(btree.root));
    }

    @Test
    public void testSumRootToLeafBinary_2() {
        BinaryTree btree = new BinaryTree();
        btree.root = new BinaryTree.BNode(1);
        btree.root.left = new BinaryTree.BNode(1);

        Assert.assertEquals(3, btree.sumRootToLeafBinary(btree.root));

        System.out.println(Integer.toString(97));
    }

    @Test
    public void testWidthOfBinaryTree_1() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new BinaryTree.BNode(1);
        binaryTree.root.left = new BinaryTree.BNode(2);
        binaryTree.root.right = new BinaryTree.BNode(3);

        Assert.assertEquals(2, binaryTree.widthOfBinaryTree(binaryTree.root));
    }

    @Test
    public void testBuildTree_preorder() {
        BinaryTree binaryTree = new BinaryTree();
        int[] preorder = {4, 3, 1, 2, 10, 9, 8, 7, 6, 5, 12, 11, 13};
        int[] inorder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        BinaryTree.BNode root = binaryTree.buildTree(BinaryTree.BuildMetadata.PREORDER_INORDER, preorder, inorder);
        List<Integer> preorderList = new ArrayList<>();
        binaryTree.preorder(root, preorderList);
        for(int i=0; i<preorder.length; i++) {
            Assert.assertEquals(preorder[i], preorderList.get(i).intValue());
        }
    }

    @Test
    public void testBuildTree_postorder() {
        BinaryTree binaryTree = new BinaryTree();
        int[] postorder = {1, 3, 2};
        int[] inorder = {1, 2, 3};

        BinaryTree.BNode root = binaryTree.buildTree(BinaryTree.BuildMetadata.POSTORDER_INORDER, postorder, inorder);
    }

    @Test
    public void testConvertToBst() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new BinaryTree.BNode(0);
        binaryTree.root.left = new BinaryTree.BNode(1);
        binaryTree.root.right = new BinaryTree.BNode(2);
        binaryTree.root.left.left = new BinaryTree.BNode(3);
        binaryTree.root.left.right = new BinaryTree.BNode(4);
        binaryTree.root.right.left = new BinaryTree.BNode(5);
        binaryTree.root.left.left.right = new BinaryTree.BNode(6);
        binaryTree.root.right.left.right = new BinaryTree.BNode(7);
        binaryTree.root.left.left.right.right = new BinaryTree.BNode(8);

        binaryTree.convertToBst();

    }

    @Test
    public void testVerticalOrder() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new BinaryTree.BNode(0);
        binaryTree.root.left = new BinaryTree.BNode(1);
        binaryTree.root.right = new BinaryTree.BNode(2);
        binaryTree.root.left.left = new BinaryTree.BNode(3);
        binaryTree.root.left.right = new BinaryTree.BNode(4);
        binaryTree.root.right.left = new BinaryTree.BNode(5);
        binaryTree.root.left.left.right = new BinaryTree.BNode(6);
        binaryTree.root.right.left.right = new BinaryTree.BNode(7);
        binaryTree.root.left.left.right.right = new BinaryTree.BNode(8);

    }
}
