package questions;

import java.util.ArrayList;
import java.util.List;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * Base use case is check if inorder representation of the BST is sorted.
     */
    private boolean _isValidInorder(TreeNode root) {
        if(root == null)
            return true;

        List<Integer> integers = new ArrayList<>();
        _inorder(root, integers);

        for(int i=1; i<integers.size(); i++) {
            if(integers.get(i-1) >= integers.get(i))
                return false;
        }

        return true;
    }

    private void _inorder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;

        _inorder(root.left, list);
        list.add(root.val);
        _inorder(root.right, list);
    }

    private boolean isValidBST(TreeNode root, Integer max, Integer min) {
        if(root == null)
            return true;

        if(max != null && root.val >= max)
            return false;

        if(min != null && root.val <= min)
            return false;

        return isValidBST(root.left, root.val, min)
                && isValidBST(root.right, max, root.val);
    }
}
