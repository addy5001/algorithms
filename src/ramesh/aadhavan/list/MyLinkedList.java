package ramesh.aadhavan.list;

public class MyLinkedList {
    static class Node {
        private int val;
        private Node next;

        Node(int x) {
            val = x;
        }
    }

    Node head = null;
    Node tail = null;
    int size = 0;

    /** Initialize your data structure here. */
    public MyLinkedList() {

    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index >= size || head == null)
            return -1;

        Node temp = head;
        while(temp != null) {
            if(index == 0)
                return temp.val;

            index--;
            temp = temp.next;
        }

        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node temp = new Node(val);

        if(head == null) {
            head = tail = temp;
        }
        else {
            temp.next = head;
            head = temp;
        }

        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node temp = new Node(val);

        if(head == null) {
            head = tail = temp;
        }
        else {
            tail.next = temp;
            tail = temp;
        }

        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size)
            return;

        Node temp = head;
        Node prev = temp;
        int counter = 0;

        if(index == 0) {
            addAtHead(val);
        }
        else if(index == size) {
            addAtTail(val);
        }
        else {
            while(counter < index && temp != null) {
                prev = temp;
                temp = temp.next;
                counter++;
            }

            if(temp == null)
                return;

            Node newNode = new Node(val);
            newNode.next = temp;
            prev.next = newNode;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index >= size || head == null)
            return;

        if(index == 0) {
            head = head.next;
        }
        else if(index == (size - 1)) {
            Node temp = head;
            while(temp.next != tail) {
                temp = temp.next;
            }

            temp.next = null;
            tail = temp;
        }
        else {
            int counter = 0;
            Node temp = head;
            Node prev = temp;
            while(counter < index && temp != null) {
                prev = temp;
                temp = temp.next;
                counter++;
            }

            if(temp == null)
                return;

            prev.next = temp.next;
        }

        size--;
    }
}
