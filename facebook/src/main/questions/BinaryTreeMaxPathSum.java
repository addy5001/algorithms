package questions;

public class BinaryTreeMaxPathSum {
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
}
