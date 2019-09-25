package ramesh.aadhavan.list;

import java.util.HashSet;
import java.util.Set;

public class CircularList {
    private SingleLinkedListNode head;
    private SingleLinkedListNode tail;
    private SingleLinkedListNode token;

    public CircularList() {
        head = tail = token = null;
    }

    public void add(int val) {
        if(head == null) {
            token = tail = head = new SingleLinkedListNode(val);
            tail.next = head;
        }
        else {
            tail.next = new SingleLinkedListNode(val);
            tail = tail.next;
            tail.next = head;
        }
    }

    public int getNextToken() {
        if(token == null)
            return -1;

        int val = token.value;
        token = token.next;
        return val;
    }

    public static boolean detectLoop(SingleLinkedListNode node) {
        return detectLoop(node, new HashSet<>());
    }

    private static boolean detectLoop(SingleLinkedListNode node, Set<SingleLinkedListNode> nodeSet) {
        if(node == null)
            return false;

        if(nodeSet.contains(node))
            return true;

        nodeSet.add(node);
        return detectLoop(node.next, nodeSet);
    }

    public static void main(String[] args) {
        CircularList list = new CircularList();
        list.add(10);
        list.add(15);
        list.add(20);

        System.out.println(list.getNextToken());
        System.out.println(list.getNextToken());
        System.out.println(list.getNextToken());
        System.out.println(list.getNextToken());
        System.out.println(list.getNextToken());
        System.out.println(list.getNextToken());

        System.out.println(list.toString());
    }
}
