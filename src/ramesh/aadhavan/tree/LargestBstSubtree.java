package ramesh.aadhavan.tree;

public class LargestBstSubtree {
    class NodeInfo {
        int size;
        Integer upperBound;
        Integer lowerBound;

        NodeInfo(int size, Integer upperBound, Integer lowerBound) {
            this.size = size;
            this.upperBound = upperBound;
            this.lowerBound = lowerBound;
        }
    }

    Integer maxBstSize = 0;

    public int largestBSTSubtree(TreeNode root) {
        maxBstSize = 0;
        _largestBSTSubtree(root);
        return maxBstSize;
    }

    private NodeInfo _largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return new NodeInfo(0, null, null);
        }

        NodeInfo leftSubTreeInfo = _largestBSTSubtree(root.left);
        NodeInfo rightSubTreeInfo = _largestBSTSubtree(root.right);

        if(leftSubTreeInfo.size == -1 ||
                rightSubTreeInfo.size == -1 ||
                (leftSubTreeInfo.upperBound != null && root.val <= leftSubTreeInfo.upperBound) ||
                (rightSubTreeInfo.lowerBound != null && root.val >= rightSubTreeInfo.lowerBound))
            return new NodeInfo(-1, null, null);

        Integer currentBstSize = 1 + leftSubTreeInfo.size + rightSubTreeInfo.size;
        maxBstSize = Math.max(currentBstSize, maxBstSize);

        Integer currentUpperBound = (rightSubTreeInfo.upperBound != null ? rightSubTreeInfo.upperBound : root.val);
        Integer currentLowerBound = (leftSubTreeInfo.lowerBound != null ? leftSubTreeInfo.lowerBound : root.val);
        return new NodeInfo(currentBstSize, currentUpperBound, currentLowerBound);
    }
}
