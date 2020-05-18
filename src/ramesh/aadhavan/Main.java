package ramesh.aadhavan;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(6);
        treeSet.add(7);
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(21);
        treeSet.add(26);
        treeSet.add(30);

        System.out.println(treeSet.floor(7));
        System.out.println(treeSet.ceiling(10));
        System.out.println(treeSet.higher(10));
    }
}
