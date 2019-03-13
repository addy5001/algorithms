package ramesh.aadhavan;

import ramesh.aadhavan.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.addNode(4);
        bst.addNode(5);
        bst.addNode(2);


        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.addNode(4);
        bst2.addNode(5);
        bst2.addNode(2);

        System.out.println(bst.isSameTree(bst2));

        bst.inorder();
        bst.preorder();
    }
}
