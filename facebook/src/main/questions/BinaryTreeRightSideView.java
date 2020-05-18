package questions;

import java.util.*;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if(root == null)
            return Collections.emptyList();
        return _rightSideViewBfs(root);
    }

    private void _rightSideView(TreeNode node, int currentLevel, Map<Integer, Integer> nodeMap) {
        if(node == null)
            return;

        nodeMap.putIfAbsent(currentLevel, node.val);

        _rightSideView(node.right, currentLevel+1, nodeMap);
        _rightSideView(node.left, currentLevel+1, nodeMap);
    }

    private List<Integer> _rightSideViewBfs(TreeNode root) {
        Queue<TreeNode> bfsQ = new LinkedList<>();
        List<Integer> results = new ArrayList<>();
        bfsQ.add(root);

        while(!bfsQ.isEmpty()) {
            int size = bfsQ.size();
            boolean rightMostAdded = false;

            for(int i=0; i<size; i++) {
                TreeNode node = bfsQ.poll();
                if(!rightMostAdded) {
                    results.add(node.val);
                    rightMostAdded = true;
                }

                if(node.right != null)
                    bfsQ.add(node.right);

                if(node.left != null)
                    bfsQ.add(node.left);
            }
        }

        return results;
    }
}
