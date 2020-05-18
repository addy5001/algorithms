package ramesh.aadhavan.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CodecTest {

    final Codec codec = new Codec();
    final BinarySearchTree searchTree = new BinarySearchTree();

    @Test
    public void testSerialize() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.addNode(3);
        bst.addNode(4);
        bst.addNode(5);
        bst.addNode(1);
        bst.addNode(2);

        String preOrder = codec.serialize(bst.rootNode);
        Assert.assertEquals("3/1/2/4/5", preOrder);
    }

    @Test
    public void testDeserialize() {
        String preorder = "3/1/2/4/5";
        BinarySearchTree.Node root = codec.deserialize(preorder);
        searchTree.setRootNode(root);
        List<Integer> inorder = searchTree.inorderToList();
        int[] inorderArr = {1, 2, 3, 4, 5};

        for(int i=0; i<inorderArr.length; i++) {
            Assert.assertEquals(inorderArr[i], (int) inorder.get(i));
        }
    }

    @Test
    public void testNull() {
        String preOrder = codec.serialize(null);
        Assert.assertEquals("", preOrder);
        BinarySearchTree.Node root = codec.deserialize(preOrder);
        Assert.assertNull(root);
    }

    @Test
    public void testSerialize_1() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.addNode(2);
        bst.addNode(1);

        String preOrder = codec.serialize(bst.rootNode);
        Assert.assertEquals("2/1", preOrder);
    }

    @Test
    public void testDeserialize_1() {
        String preorder = "2/1";
        BinarySearchTree.Node root = codec.deserialize(preorder);
        searchTree.setRootNode(root);
        List<Integer> inorder = searchTree.inorderToList();
        int[] inorderArr = {1, 2};

        for(int i=0; i<inorderArr.length; i++) {
            Assert.assertEquals(inorderArr[i], (int) inorder.get(i));
        }
    }
}
