package ramesh.aadhavan.tree;

import org.junit.Assert;
import org.junit.Test;

public class SerializeDeserializeTreeWithDuplicatesTest {

    final SerializeDeserializeWithDuplicates treeSerializer = new SerializeDeserializeWithDuplicates();

    @Test
    public void testSerialize() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(5);

        Assert.assertEquals("1 2 null null 3 4 null null 5 null null", treeSerializer.serialize(node));
    }

    @Test
    public void testDeserialize() {
        TreeNode node = treeSerializer.deserialize("1 2 null null 3 4 null null 5 null null");
        Assert.assertEquals(1, node.val);
        Assert.assertEquals(2, node.left.val);
        Assert.assertEquals(3, node.right.val);
        Assert.assertEquals(4, node.right.left.val);
        Assert.assertEquals(5, node.right.right.val);
    }
}
