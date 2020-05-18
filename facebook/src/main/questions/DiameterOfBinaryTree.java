package questions;

public class DiameterOfBinaryTree {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode node) {
        _diameter(node);
        return max;
    }

    private int _diameter(TreeNode node) {
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
}
