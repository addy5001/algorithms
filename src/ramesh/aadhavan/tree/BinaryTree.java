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

    public void balancedAdd(int val) {
        if(root == null)
            root = new BNode(val);
        else
            _balancedAdd(root, val);
    }

    private void _balancedAdd(BNode node, int val) {
        if(node.left == null)
            node.left = new BNode(val);
        else if(node.right == null)
            node.right = new BNode(val);
        else {
            int leftSubTreeCount = nodeCount(node.left);
            int rightSubTreeCount = nodeCount(node.right);

            if(leftSubTreeCount == rightSubTreeCount)
                _balancedAdd(node.left, val);
            else
                _balancedAdd(node.right, val);
        }
    }

    public void bfsAdd(int val) {
        if(root == null)
            root = new BNode(val);
        else
            _bfsAdd(root, val);
    }

    private void _bfsAdd(BNode node, int val) {
        if(node.left == null)
            node.left = new BNode(val);
        else if(node.right == null)
            node.right = new BNode(val);
        else {
            int leftDeepestLevel = leftDeepestLevel(node, 0);
            int leftCount = nodeCount(node.left);
            int rightCount = nodeCount(node.right);

            if(leftCount == rightCount)
                _bfsAdd(node.left, val);
            else if(leftCount < Math.pow(2, leftDeepestLevel-1) - 1)
                _bfsAdd(node.left, val);
            else
                _bfsAdd(node.right, val);
        }
    }

    private int nodeCount(BNode bNode) {
        if(bNode == null)
            return 0;

        return 1 + nodeCount(bNode.left) + nodeCount(bNode.right);
    }

    private int leftDeepestLevel(BNode node, int currentLevel) {
        if(node == null)
            return currentLevel;

        return leftDeepestLevel(node.left, currentLevel+1);
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
        binaryTree.bfsAdd(5);
        binaryTree.bfsAdd(10);
        binaryTree.bfsAdd(11);
        binaryTree.bfsAdd(12);
        binaryTree.bfsAdd(13);
        binaryTree.bfsAdd(14);
        binaryTree.bfsAdd(15);
        binaryTree.bfsAdd(16);
        binaryTree.bfsAdd(17);
        binaryTree.bfsAdd(18);
        binaryTree.bfsAdd(19);
        binaryTree.bfsAdd(20);
        binaryTree.bfsAdd(21);
        binaryTree.bfsAdd(22);
        binaryTree.bfsAdd(23);
        binaryTree.bfsAdd(24);
        binaryTree.bfsAdd(25);

        System.out.println();
    }
}
