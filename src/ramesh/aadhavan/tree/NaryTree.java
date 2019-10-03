package ramesh.aadhavan.tree;

public class NaryTree {
    private static class Node {
        private final int N_ARY;
        private int value;
        private Node[] nodes;

        public Node(int n_ARY, int value) {
            N_ARY = n_ARY;
            this.value = value;
            nodes = new Node[N_ARY];
        }
    }

    private Node rootNode;
    private final int breadth;

    public NaryTree(int breadth) {
        rootNode = null;
        this.breadth = breadth;
    }

    public void mirrorTree() {
        rotate(rootNode);
    }

    public void rotate(Node node) {
        if(node == null)
            return;

        int halfBreadth = breadth/2;
        for(int i=0, j=breadth-1; i<halfBreadth; i++, j--) {
            Node tmp = node.nodes[i];
            node.nodes[i] = node.nodes[j];
            node.nodes[j] = tmp;
        }

        for(int i=0; i<breadth; i++) {
            rotate(node.nodes[i]);
        }
    }

    public static void main(String[] args) {
        NaryTree naryTree = new NaryTree(3);
        naryTree.rootNode = new Node(3, 1);
        naryTree.rootNode.nodes[0] = new Node(3, 11);
        naryTree.rootNode.nodes[1] = new Node(3, 12);
        naryTree.rootNode.nodes[2] = new Node(3, 13);
        naryTree.rootNode.nodes[0].nodes[0] = new Node(3, 141);
        naryTree.rootNode.nodes[0].nodes[1] = new Node(3, 151);
        naryTree.rootNode.nodes[0].nodes[2] = new Node(3, 161);
        naryTree.rootNode.nodes[1].nodes[0] = new Node(3, 171);
        naryTree.rootNode.nodes[1].nodes[1] = new Node(3, 181);
        naryTree.rootNode.nodes[1].nodes[2] = new Node(3, 191);
        naryTree.rootNode.nodes[2].nodes[0] = new Node(3, 201);
        naryTree.rootNode.nodes[2].nodes[1] = new Node(3, 211);
        naryTree.rootNode.nodes[2].nodes[2] = new Node(3, 221);

        naryTree.mirrorTree();

    }
}
