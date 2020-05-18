package questions;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

/**
 * Base case is get the inorder representation of the Tree in the constructor
 * Then get the iterator to that list
 */
class BinarySearchTreeIteratorInorder implements Iterator<Integer> {

    final Iterator<Integer> inorderListIterator;

    public BinarySearchTreeIteratorInorder(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        _inorder(root, inorderList);
        this.inorderListIterator = inorderList.iterator();
    }

    private void _inorder(TreeNode node, List<Integer> inorderList) {
        if(node == null)
            return;

        _inorder(node.left, inorderList);
        inorderList.add(node.val);
        _inorder(node.right, inorderList);
    }

    @Override
    public boolean hasNext() {
        return this.inorderListIterator.hasNext();
    }

    @Override
    public Integer next() {
        return this.inorderListIterator.next();
    }
}

public class BinarySearchTreeIterator implements Iterator<Integer> {

    Deque<TreeNode> stack = new LinkedList<>();

    public BinarySearchTreeIterator(TreeNode root) {
        _recurseAddLeft(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        TreeNode leftMost = stack.pop();

        if(leftMost.right != null) {
            _recurseAddLeft(leftMost.right);
        }

        return leftMost.val;
    }

    private void _recurseAddLeft(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
