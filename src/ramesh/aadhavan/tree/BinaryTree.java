package ramesh.aadhavan.tree;

import java.util.*;
import java.util.function.Supplier;

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

    private int rightDeepestLevel(BNode node, int currentLevel) {
        if(node == null)
            return currentLevel;

        return rightDeepestLevel(node.right, currentLevel+1);
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

    public boolean isFull() {
        return _isFull(root);
    }

    private boolean _isFull(BNode node) {
        if(node == null)
            return true;

        if((node.left == null && node.right != null) || (node.left != null && node.right == null))
            return false;

        return _isFull(node.left) && _isFull(node.right);
    }

    public boolean isFullIterative() {
        if(root == null)
            return true;

        Queue<BNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            BNode node = queue.poll();

            if(node.left == null && node.right == null)
                continue;

            if((node.left == null || node.right == null))
                return false;

            queue.add(node.right);
            queue.add(node.left);
        }

        return true;
    }

    public boolean isComplete() {
        if(root == null)
            return true;

        Queue<BNode> queue = new LinkedList<>();
        queue.add(root);
        boolean seenPartialValidNode = false;
        boolean seenEmptyNode = false;

        while(!queue.isEmpty()) {
            BNode node = queue.poll();

            if(node.left == null && node.right == null) {
                seenEmptyNode = true;
                continue;
            }

            if(node.left == null)
                return false;

            if(node.right == null) {
                if(seenPartialValidNode || seenEmptyNode)
                    return false;
                else {
                    queue.add(node.left);
                    seenPartialValidNode = true;
                    continue;
                }
            }

            if(seenPartialValidNode || seenEmptyNode)
                return false;

            queue.add(node.left);
            queue.add(node.right);
        }

        return true;
    }

    public int completeBinaryTreeInsert(int v) {
        if(root == null) {
            root = new BNode(v);
            return v;
        }

        Queue<BNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);

        while(!levelQueue.isEmpty()) {
            BNode node = levelQueue.poll();

            if(node.left == null) {
                node.left = new BNode(v);
                return node.val;
            }
            else if(node.right == null) {
                node.right = new BNode(v);
                return node.val;
            }
            else {
                levelQueue.add(node.left);
                levelQueue.add(node.right);
            }
        }

        return -1;
    }

    public boolean isSubtree(BNode s, BNode t) {
        List<BNode> nodes = new ArrayList<>();
        findAllPossibleNodes(s, t, nodes);

        for(BNode node : nodes) {
            if(_recursiveCheck(t, node))
                return true;
        }

        return false;
    }

    private void findAllPossibleNodes(BNode s, BNode t,
                                      List<BNode> nodes) {
        if(s == null)
            return;

        if(s.val == t.val)
            nodes.add(s);

        findAllPossibleNodes(s.left, t, nodes);
        findAllPossibleNodes(s.right, t, nodes);
    }

    private boolean _recursiveCheck(BNode s, BNode t) {
        if(s == null && t == null)
            return true;

        if(s == null || t == null)
            return false;

        if(s.val != t.val)
            return false;

        return _recursiveCheck(s.left, t.left) && _recursiveCheck(s.right, t.right);
    }

    public int sumRootToLeafBinary(BNode root) {
        List<Integer> path = new LinkedList<>();
        List<Integer> numbers = new ArrayList<>();
        rootToLeaves(root, 0, path, numbers);
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    private void rootToLeaves(BNode root, int index, List<Integer> path, List<Integer> numbers) {
        if(root == null) {
            return;
        }

        path.add(index, root.val);

        if(root.left == null && root.right == null) {
            int number = 0;
            for(int i : path) {
                number = (number << 1) + i;
            }

            numbers.add(number);
            return;
        }

        rootToLeaves(root.left, index+1, path, numbers);
        if(root.left != null)
            path.remove(index+1);

        rootToLeaves(root.right, index+1, path, numbers);
        if(root.right != null)
            path.remove(index+1);
    }

    static class AnnotatedNode {
        BNode node;
        int depth;
        int position;

        public AnnotatedNode(BNode node, int depth, int position) {
            this.node = node;
            this.depth = depth;
            this.position = position;
        }
    }

    /**
     * Encapsulate BNodes inside AnnotatedNode.
     * Add depth and position for each node.
     * position of Left node (L) = 2 * (position of Parent)
     * position of Right node (R) = 2 * (position of Parent) + 1
     * width of each level = R - L + 1
     * max width = max(width1, width2....widthN)
     * @param root
     * @return
     */
    public int widthOfBinaryTree(BNode root) {
        if(root == null)
            return 0;

        Queue<AnnotatedNode> queue = new LinkedList<>();
        queue.add(new AnnotatedNode(root, 0, 0));
        int currentDepth = -1;
        int L = 0, R = 0;
        int maxWidth = 0;
        while(!queue.isEmpty()) {
            AnnotatedNode annotatedNode = queue.poll();
            if(annotatedNode.node.left != null) {
                queue.add(new AnnotatedNode(annotatedNode.node.left, annotatedNode.depth+1,
                        2 * annotatedNode.position));
            }
            if(annotatedNode.node.right != null) {
                queue.add(new AnnotatedNode(annotatedNode.node.right, annotatedNode.depth+1,
                        2 * annotatedNode.position + 1));
            }

            if(annotatedNode.depth != currentDepth) {
                int width = R - L + 1;
                maxWidth = Math.max(maxWidth, width);
                L = annotatedNode.position;
                R = annotatedNode.position;
                currentDepth = annotatedNode.depth;
            }
            else {
                R = annotatedNode.position;
                if(queue.isEmpty()) {
                    maxWidth = Math.max(maxWidth, R - L + 1);
                }
            }
        }

        return maxWidth;
    }

    enum BuildMetadata {
        PREORDER_INORDER,
        POSTORDER_INORDER,
        PREORDER_POSTORDER;
    }

    public BNode buildTree(BuildMetadata buildMetadata, int[] array, int[] inorder) {
        switch (buildMetadata) {
            case PREORDER_INORDER:
                return _buildTreeFromPreorderInorder(array, 0, array.length-1, inorder, 0, array.length-1);
            case POSTORDER_INORDER:
                return _buildTreeFromPostorderInorder(array, 0, array.length-1, inorder, 0, array.length-1);
            case PREORDER_POSTORDER:
                return null;
            default:
                return null;
        }
    }

    private BNode _buildTreeFromPreorderInorder(int[] preorder, int low1, int high1, int[] inorder, int low2, int high2) {
        if(low1 > high1)
            return null;

        int rootVal = preorder[low1];
        int mid = low2;
        while(mid <= high2) {
            if(rootVal == inorder[mid])
                break;

            mid++;
        }

        BNode node = new BNode(rootVal);
        int leftElements = mid - low2;
        node.left = _buildTreeFromPreorderInorder(
                preorder, low1+1, low1+leftElements,
                inorder, low2, mid-1);

        node.right = _buildTreeFromPreorderInorder(
                preorder, low1+leftElements+1, high1,
                inorder, mid+1, high2);

        return node;
    }

    /*
    1 2 3
    1 3 2
     */

    private BNode _buildTreeFromPostorderInorder(
            int[] postorder, int low1, int high1,
            int[] inorder, int low2, int high2) {
        if(low1 > high1)
            return null;

        int rootVal = postorder[high1];
        int mid = low2;
        while(mid <= high2) {
            if(rootVal == inorder[mid])
                break;

            mid++;
        }

        int leftElements = mid - low2;
        BNode node = new BNode(rootVal);
        node.left = _buildTreeFromPostorderInorder(
                postorder, low1, low1+leftElements-1,
                inorder, low2, mid-1);

        node.right = _buildTreeFromPostorderInorder(
                postorder, low1+leftElements, high1-1,
                inorder, mid+1, high2);

        return node;
    }

    public void preorder(BNode node, List<Integer> list) {
        if(Objects.nonNull(node)) {
            list.add(node.val);
            preorder(node.left, list);
            preorder(node.right, list);
        }
    }

    public void postorder(BNode node, List<Integer> list) {
        if(Objects.nonNull(node)) {
            preorder(node.left, list);
            preorder(node.right, list);
            list.add(node.val);
        }
    }

    public void inorder(BNode node, List<Integer> list) {
        if(Objects.nonNull(node)) {
            preorder(node.left, list);
            list.add(node.val);
            preorder(node.right, list);
        }
    }

    /**
     * Convert to BST without losing shape
     */

    private class IndexCounter {
        public Integer index = 0;
    }

    public void convertToBst() {
        List<Integer> sortedList = new ArrayList<>();
        inorder(root, sortedList);
        Collections.sort(sortedList);

        final IndexCounter indexCounter = new IndexCounter();
        _convertToBst(root, () -> sortedList.get(indexCounter.index++));
    }

    private void _convertToBst(BNode node, Supplier<Integer> valSupplier) {
        if(node == null)
            return;

        _convertToBst(node.left, valSupplier);
        node.val = valSupplier.get();
        _convertToBst(node.right, valSupplier);
    }

    int max = 0;

    public int diameterOfBinaryTree(BNode node) {
        _diameter(node);
        return max;
    }

    private int _diameter(BNode node) {
        if(node == null || (node.left == null && node.right == null))
            return 0;

        int left = _diameter(node.left);
        int right = _diameter(node.right);
        int maxHeight = 1 + Math.max(left, right);

        if(node.left == null || node.right == null) {
            if(max < maxHeight)
                max = maxHeight;
        }
        else {
            int diameter = 2 + left + right;
            if(max < diameter)
                max = diameter;
        }

        return maxHeight;
    }

    /**
     * Works on a binary tree with non-duplicate nodes
     * Assume p and q are always present.
     *
     * @param root
     * @param p
     * @param q
     * @return lowest common ancestor of p and q
     */
    BNode lowestCommonAncestor(BNode root, BNode p, BNode q) {
        if(root == null || root == p || root == q)
            return root;

        BNode left = lowestCommonAncestor(root.left, p, q);
        BNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        _maxPathSum(root);
        return maxPathSum;
    }

    private int _maxPathSum(TreeNode root) {
        if(root == null)
            return 0;

        int leftGain = Math.max(_maxPathSum(root.left), 0);
        int rightGain = Math.max(_maxPathSum(root.right), 0);

        maxPathSum = Math.max(maxPathSum, leftGain + rightGain + root.val);

        return root.val + Math.max(leftGain, rightGain);
    }

    public BNode mergeTrees(BNode node1, BNode node2) {
        return _mergeTrees(node1, node2);
    }

    private BNode _mergeTrees(BNode node1, BNode node2) {
        if(node1 == null && node2 == null)
            return null;
        else if(node1 == null)
            return node2;
        else if(node2 == null)
            return node1;
        else {
            BNode node = new BNode(node1.val + node2.val);
            node.left = _mergeTrees(node1.left, node2.right);
            node.right = _mergeTrees(node1.right, node2.right);
            return node;
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        return _findLeavesSpaceEfficient(root);
    }

    private List<List<Integer>> _findLeavesSpaceEfficient(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        _findOrderSpaceEfficient(root, results);
        return results;
    }

    private int _findOrderSpaceEfficient(TreeNode root, List<List<Integer>> leaves) {
        if(root == null)
            return 0;

        int leftOrder = _findOrderSpaceEfficient(root.left, leaves);
        int rightOrder = _findOrderSpaceEfficient(root.right, leaves);

        int orderOfNode = Math.max(leftOrder, rightOrder) + 1;

        if(leaves.size() < orderOfNode) {
            leaves.add(orderOfNode-1, new ArrayList<>());
        }

        leaves.get(orderOfNode).add(root.val);
        return orderOfNode;
    }

    private List<List<Integer>> _findLeaves(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        _findOrder(root, map);

        Map<Integer, List<Integer>> treeMap = new TreeMap<>();
        map.forEach((key, val) -> {
            treeMap.computeIfAbsent(val, v -> new ArrayList<>()).add(key.val);
        });

        return new ArrayList<>(treeMap.values());
    }

    private int _findOrder(TreeNode root, Map<TreeNode, Integer> map) {
        if(root == null)
            return 0;

        int leftOrder = _findOrder(root.left, map);
        int rightOrder = _findOrder(root.right, map);

        int orderOfNode = Math.max(leftOrder, rightOrder) + 1;
        map.put(root, orderOfNode);
        return orderOfNode;
    }
}
