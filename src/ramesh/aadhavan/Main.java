package ramesh.aadhavan;

import ramesh.aadhavan.tree.BinarySearchTree;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.addNode(100);
        bst.addNode(50);
        bst.addNode(150);
        bst.addNode(120);
        bst.addNode(110);
        bst.addNode(25);
        bst.addNode(75);

        Map<Integer, Integer> diagonalSum = bst.diagonalSum();
        diagonalSum.forEach((k,v) -> {
            System.out.println("Level: "+k+" = "+"Sum: "+v);
        });
    }
}
