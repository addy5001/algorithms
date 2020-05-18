package ramesh.aadhavan.misc;

public class CountOfSmallerNumbersAfterSelf {
    class Node {
        int val;
        int sum;
        int dup;

        Node left;
        Node right;

        public Node(int val, int sum, int dup) {
            this.val = val;
            this.sum = sum;
            this.dup = dup;
        }
    }

    public int[] countOfSmallerNumbersAfterSelf(int[] nums) {
        int[] counts = new int[nums.length];
        Node root = null;

        for(int i=nums.length-1; i>=0; i--) {
            root = insertIntoBst(root, nums[i], i, counts, 0);
        }

        return counts;
    }

    private Node insertIntoBst(Node node, int num, int idx, int[] counts, int computedCount) {
        if(node == null) {
            node = new Node(num, 0, 1);
            counts[idx] = computedCount;
        }
        else if(node.val == num) {
            node.dup++;
            counts[idx] = computedCount + node.sum;
        }
        else if(node.val > num) {
            node.sum++;
            node.left = insertIntoBst(node.left, num, idx, counts, computedCount);
        }
        else {
            node.right = insertIntoBst(node.right, num, idx, counts, node.sum + computedCount + node.dup);
        }

        return node;
    }
}
