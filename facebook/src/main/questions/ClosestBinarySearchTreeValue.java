package questions;

import java.util.Deque;
import java.util.LinkedList;

public class ClosestBinarySearchTreeValue {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    double min = Double.MAX_VALUE;
    Integer valTarget = null;

    public int closestValue(TreeNode root, double target) {
        return _iterativeInorder(root, target);
    }

    private void _inorder(TreeNode node, double target) {
        if(node == null)
            return;

        _inorder(node.left, target);

        double abs = Math.abs(((double)node.val) - target);
        if(abs < min) {
            min = abs;
            valTarget = node.val;
        }

        _inorder(node.right, target);
    }

    private int _iterativeInorder(TreeNode node, double target) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode temp = node;

        int targetLowerBound = 0;
        int targetUpperBound = 0;

        while(temp != null || !stack.isEmpty()) {
            while(temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            TreeNode current = stack.pop();
            if((double)current.val < target) {
                targetLowerBound = current.val;
            }
            else {
                targetUpperBound = current.val;
                break;
            }

            temp = current.right;
        }

        double targetMinAbs = Math.abs(target - (double) targetLowerBound);
        double targetMaxAbs = Math.abs((double) targetUpperBound - target);

        if(targetMinAbs < targetMaxAbs)
            return targetLowerBound;
        else
            return targetUpperBound;
    }

    private int _binarySearch(TreeNode root, double target) {
        int val;
        int closest = root.val;

        while(root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target) ? root.val : closest;
            root = target < (double) root.val ? root.left : root.right;
        }

        return closest;
    }

    /**
     * Follow up
     *
     * Find k closest nodes
     *
     *     public List<Integer> closestKValues(TreeNode root, double target, int k) {
     *         LinkedList<Integer> res = new LinkedList<>();
     *         collect(root, target, k, res);
     *         return res;
     *     }
     *
     *     public void collect(TreeNode root, double target, int k, LinkedList<Integer> res) {
     *         if (root == null) return;
     *         collect(root.left, target, k, res);
     *
     *         if (res.size() == k) {
     *             //if size k, add curent and remove head if it's optimal, otherwise return
     *             if (Math.abs(target - root.val) < Math.abs(target - res.peekFirst()))
     *                 res.removeFirst();
     *             else return;
     *         }
     *         res.add(root.val);
     *         collect(root.right, target, k, res);
     *     }
     */
}
