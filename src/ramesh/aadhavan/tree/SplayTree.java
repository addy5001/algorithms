package ramesh.aadhavan.tree;

import ramesh.aadhavan.helpers.Tuple;

public class SplayTree {

    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    Node root;

    public SplayTree() {
        root = null;
    }

    public Tuple<Node, Node> findParentGrandFatherNodes(int val) {
        return _findParentGrandFatherNodes(val, root, null, null);
    }

    private Tuple<Node, Node> _findParentGrandFatherNodes(int val, Node node, Node parent, Node grandFather) {
        if(val == node.value)
            return new Tuple<>(parent, grandFather);

        if(val < node.value)
            return _findParentGrandFatherNodes(val, node.left, node, parent);

        return _findParentGrandFatherNodes(val, node.right, node, parent);
    }
}
