package ramesh.aadhavan.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BinaryTree} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class BinaryTree {
    static class BNode {
        public int val;
        public BNode left;
        public BNode right;

        public BNode(int val) {
            this.val = val;
        }
    }

    public BNode root;

    public BinaryTree() {
        root = null;
    }

    public boolean isBst() {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBst(BNode node, int min, int max) {
        if(node == null)
            return true;

        boolean isCurrentBst = node.val > min && node.val < max;
        boolean isLeftBst = isBst(node.left, min, node.val);
        boolean isRightBst = isBst(node.right, node.val, max);

        return isCurrentBst && isLeftBst && isRightBst;
    }

    public int maxSubBst() {
        return maxSubBst(root);
    }

    private int maxSubBst(BNode node) {
        if(isBst(node, Integer.MIN_VALUE, Integer.MAX_VALUE))
            return depth(node, 0);
        else {
            int leftLevel = maxSubBst(node.left);
            int rightLevel = maxSubBst(node.right);
            return Math.max(leftLevel, rightLevel);
        }
    }

    private int depth(BNode node, int level) {
        if(node == null)
            return level;

        return Math.max(depth(node.left, level+1), depth(node.right, level+1));
    }

    public List<Integer> maxDepthList() {
        return maxDepthList(root, new ArrayList<>());
    }

    private List<Integer> maxDepthList(BNode node, List<Integer> maxList) {
        if(node == null)
            return maxList;

        maxList.add(node.val);
        List<Integer> leftMaxList = maxDepthList(node.left, new ArrayList<>(maxList));
        List<Integer> rightMaxList = maxDepthList(node.right, new ArrayList<>(maxList));

        return leftMaxList.size() > rightMaxList.size() ? leftMaxList : rightMaxList;
    }

    private void printNodesWithOneChild(BNode node) {
        if(node == null)
            return;

        if((node.right == null && node.left != null) || (node.left == null && node.right != null))
            System.out.print(node.val+" ");

        printNodesWithOneChild(node.left);
        printNodesWithOneChild(node.right);
    }

    private int sumOfAllLeaves(BNode node) {
        if(node == null)
            return 0;

        if(node.left == null && node.right == null)
            return node.val;

        return sumOfAllLeaves(node.left) + sumOfAllLeaves(node.right);
    }

    private int sumOfAllLeftLeaves(BNode node) {
        if(node == null)
            return 0;

        if(node.left == null)
            return sumOfAllLeftLeaves(node.right);

        return node.left.val + sumOfAllLeftLeaves(node.left) + sumOfAllLeftLeaves(node.right);
    }

    public boolean isSymmetric() {
        if(root == null)
            return true;

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(BNode node1, BNode node2) {
        if(node1 == null && node2 == null)
            return true;

        if(node1 == null || node2 == null)
            return false;

        return (node1.val == node2.val) && isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new BNode(5);
        binaryTree.root.left = new BNode(3);
        binaryTree.root.right = new BNode(3);
        binaryTree.root.left.left = new BNode(1);
        binaryTree.root.left.right = new BNode(0);
        binaryTree.root.right.right = new BNode(1);

        System.out.println(binaryTree.isSymmetric());
    }
}
