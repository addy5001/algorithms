package ramesh.aadhavan.list;

import java.util.*;
import java.util.LinkedList;

/**
 * The {@code LeetCodeTreeNode} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class LeetCodeTreeNode {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if(root == null)
            return Collections.emptyList();

        Set<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        stack.push(root);

        while(stack.peek() != null) {
            TreeNode node = stack.peek();

            if(node.left != null && !visited.contains(node.left))
                stack.push(node.left);
            else {
                TreeNode popped = stack.pop();
                result.add(popped.val);
                visited.add(popped);
                if(node.right != null && !visited.contains(node.right))
                    stack.push(node.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(3);
        node.right = new TreeNode(2);

        List<Integer> list = inorderTraversal(node);

    }
}

class BSTIterator {

    private final Iterator<Integer> iterator;

    public BSTIterator(LeetCodeTreeNode.TreeNode root) {
        iterator = _initIterator(root);
    }

    /** @return the next smallest number */
    public int next() {
        return iterator.next();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return iterator.hasNext();
    }

    private Iterator<Integer> _initIterator(LeetCodeTreeNode.TreeNode node) {
        List<Integer> list = new ArrayList<>();
        _inorderList(node, list);
        return list.iterator();
    }

    private void _inorderList(LeetCodeTreeNode.TreeNode node, List<Integer> list) {
        if(node == null)
            return;

        _inorderList(node.left, list);
        list.add(node.val);
        _inorderList(node.right, list);
    }
}
