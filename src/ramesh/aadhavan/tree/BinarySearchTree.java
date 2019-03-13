package ramesh.aadhavan.tree;

import java.util.Objects;

public class BinarySearchTree {

    public static class Node implements Comparable<Node> {
        private int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(value, o.value);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node) {
                Node comparable = (Node) obj;
                if(this.value == ((Node) obj).value)
                    return true;
            }
            return false;
        }
    }

    private Node rootNode;

    public BinarySearchTree() {
        rootNode = null;
    }

    public void addNode(int value) {
        if(Objects.isNull(this.rootNode)) {
            this.rootNode = new Node(value, null, null);
            return;
        }

        if(searchNode(value)) {
            System.out.println("Value already present");
            return;
        }

        addNode(this.rootNode, new Node(value, null, null));
    }

    private void addNode(Node parentNode, Node node) {
        if(parentNode.compareTo(node) > 0) {
            if(Objects.isNull(parentNode.left))
                parentNode.left = node;
            else
                addNode(parentNode.left, node);
        }
        else {
            if(Objects.isNull(parentNode.right))
                parentNode.right = node;
            else
                addNode(parentNode.right, node);
        }
    }

    public boolean searchNode(int value) {
        Node pointer = this.rootNode;
        while(Objects.nonNull(pointer)) {
            if(value == pointer.value)
                return true;
            else if(value < pointer.value)
                pointer = pointer.left;
            else
                pointer = pointer.right;
        }
        return false;
    }

    public void inorder() {
        System.out.println("------------INORDER TRAVERSAL------------");
        inorder(this.rootNode);
    }

    private void inorder(Node node) {
        if(Objects.nonNull(node)) {
            inorder(node.left);
            System.out.print(node.value+" ");
            inorder(node.right);
        }
    }

    public void preorder() {
        System.out.println("------------PREORDER TRAVERSAL------------");
        preorder(this.rootNode);
    }

    private void preorder(Node node) {
        if(Objects.nonNull(node)) {
            System.out.print(node.value+" ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    public void postorder() {
        System.out.println("------------POSTORDER TRAVERSAL------------");
        postorder(this.rootNode);
    }

    private void postorder(Node node) {
        if(Objects.nonNull(node)) {
            preorder(node.left);
            preorder(node.right);
            System.out.print(node.value+" ");
        }
    }

    public void removeNode(int value) {
       Node nodeToDelete = findNode(value, rootNode);
       if(Objects.isNull(nodeToDelete))
           System.out.println("No node found for value: "+value);
       else
           removeNode(nodeToDelete);
    }

    private void removeNode(Node node) {
        Node parent = findParent(node);
        if(Objects.nonNull(parent)) {
            if(isLeafNode(node)) {
                reassignChildToParent(parent, node, null);
            }
            else if(Objects.isNull(node.left)) {
                reassignChildToParent(parent, node, node.right);
            }
            else if(Objects.isNull(node.right)) {
                reassignChildToParent(parent, node, node.left);
            }
            else {
                Node highestInLeft = findHighestInSubtree(node.left);
                int highestInLeftVal = highestInLeft.value;
                removeNode(highestInLeft);
                node.value = highestInLeftVal;
            }
        }
        else {
            if(isLeafNode(rootNode))
                rootNode = null;
            else if(Objects.isNull(rootNode.left))
                rootNode = rootNode.right;
            else if(Objects.isNull(rootNode.right))
                rootNode = rootNode.left;
            else {
                Node highestInLeft = findHighestInSubtree(node.left);
                int highestInLeftVal = highestInLeft.value;
                removeNode(highestInLeft);
                node.value = highestInLeftVal;
            }
        }
    }

    private void reassignChildToParent(Node parent, Node childToRemove, Node newChild) {
        if(parent.left == childToRemove)
            parent.left = newChild;
        else
            parent.right = newChild;
    }

    private Node findNode(int value, Node node) {
        if(Objects.nonNull(node)) {
            if(value == node.value)
                return node;
            else if(value < node.value)
                return findNode(value, node.left);
            else
                return findNode(value, node.right);
        }

        return null;
    }

    private Node findParent(Node node) {
        if(node == this.rootNode) return null;

        Node pointer = this.rootNode;
        Node parentPointer = pointer;

        while(Objects.nonNull(pointer)) {
            if(node == pointer)
                break;
            else if(node.compareTo(pointer) < 0) {
                parentPointer = pointer;
                pointer = pointer.left;
            }
            else {
                parentPointer = pointer;
                pointer = pointer.right;
            }
        }
        return parentPointer;
    }

    private Node findHighestInSubtree(Node node) {
        if (node.right == null)
            return node;
        else
            return findHighestInSubtree(node.right);
    }

    private boolean isLeafNode(Node node) {
        return Objects.isNull(node.left) && Objects.isNull(node.right);
    }

    public boolean isSameTree(BinarySearchTree bst) {
        return isSameTree(this.rootNode, bst.rootNode);
    }

    private boolean isSameTree(Node tree1, Node tree2) {
        if(tree1 == null && tree2 == null)
            return true;

        if(tree1 == null || tree2 == null)
            return false;

        return tree1.equals(tree2)
                && isSameTree(tree1.left, tree2.left)
                && isSameTree(tree1.right, tree2.right);
    }
}
