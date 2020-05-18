package questions;

public class BinarySearchTreeToSortedDoublyLinkedList {

    public class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {
        if(root == null)
            return null;

        root = _convert(root);
        Node head = root;
        Node tail = root;

        while(head.left != null || tail.right != null) {
            if(head.left != null)
                head = head.left;

            if(tail.right != null)
                tail = tail.right;
        }

        head.left = tail;
        tail.right = head;

        return head;
    }

    private Node _convert(Node root) {
        if(root == null) {
            return null;
        }

        if(root.left != null) {
            Node leftNode = _convert(root.left);
            while(leftNode.right != null) {
                leftNode = leftNode.right;
            }

            leftNode.right = root;
            root.left = leftNode;
        }

        if(root.right != null) {
            Node rightNode = _convert(root.right);
            while(rightNode.left != null) {
                rightNode = rightNode.left;
            }

            rightNode.left = root;
            root.right = rightNode;
        }

        return root;
    }
}
