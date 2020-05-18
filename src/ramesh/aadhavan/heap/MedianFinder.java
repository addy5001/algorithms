package ramesh.aadhavan.heap;

public class MedianFinder {

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    Node head;
    int size = 0;

    /** initialize your data structure here. */
    public MedianFinder() {
        head = null;
    }

    public void addNum(int num) {
        if(head == null) {
            head = new Node(num);
        }
        else {
            Node temp = head;
            Node prev = null;
            while(temp != null) {
                if(temp.val > num) {
                    break;
                }

                prev = temp;
                temp = temp.next;
            }

            Node node = new Node(num);
            if(prev == null) {
                node.next = head;
                head = node;
            }
            else {
                node.next = temp;
                prev.next = node;
            }
        }

        size++;
    }

    public double findMedian() {
        boolean isOdd = size % 2 != 0;
        int mid = size/2;

        int idx = 0;
        Node temp = head;
        while(idx < mid-1 && temp != null) {
            idx++;
            temp = temp.next;
        }

        if(isOdd) {
            return (double) temp.next.val;
        }
        else {
            return (double) (temp.val + temp.next.val)/2.0;
        }
    }
}
