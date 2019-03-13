package ramesh.aadhavan.list;

import java.util.Objects;

public class LinkedList {
    private SingleLinkedListNode head;

    public LinkedList() {
        this.head = null;
    }

    public LinkedList(SingleLinkedListNode head) { this.head = head; }

    public void add(int value) {
        if(Objects.isNull(head))
            head = new SingleLinkedListNode(value);
        else {
            SingleLinkedListNode pointer = head;
            while(pointer.getNext()!=null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(new SingleLinkedListNode(value));
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        SingleLinkedListNode pointer = head;
        StringBuilder builder = new StringBuilder("[ ");
        while(pointer!=null) {
            builder.append(pointer.getValue()+" ");
            pointer = pointer.getNext();
        }
        builder.append("]");
        return builder.toString();
    }

    public static LinkedList addReversedLists(LinkedList list1, LinkedList list2) {
        SingleLinkedListNode pointer1 = list1.head;
        SingleLinkedListNode pointer2 = list2.head;

        LinkedList sumList = new LinkedList();

        int carryOver = 0;
        while(pointer1!=null || pointer2!=null) {
            int a = 0;
            int b = 0;

            if(pointer1!=null) {
                a = pointer1.getValue();
                pointer1 = pointer1.getNext();
            }
            if(pointer2!=null) {
                b = pointer2.getValue();
                pointer2 = pointer2.getNext();
            }

            int sum = a + b;
            if(carryOver!=0) {
                sum = sum + carryOver;
            }

            int lastDigit = 0;
            if(sum >= 10) {
                lastDigit = sum%10;
                carryOver = sum/10;
            }
            else {
                lastDigit = sum;
                carryOver = 0;
            }

            sumList.add(lastDigit);
        }

        if(carryOver!=0) {
            sumList.add(carryOver);
        }

        return sumList;
    }

    public void reverseOrder() {
        reverseOrder(head);
    }

    private void reverseOrder(SingleLinkedListNode pointer) {
        if(pointer.getNext() == null) {
            System.out.print(pointer.getValue());
            return;
        }
        reverseOrder(pointer.getNext());
        System.out.print(pointer.getValue());
    }

    public void reverseList(LinkedList list) {
        reverseList(list.head);
    }

    private void reverseList(SingleLinkedListNode node) {
        if(node == null) {
            return;
        }

        if(node.getNext() == null) {

        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(0);
        list.add(3);
        list.add(5);
        list.add(1);

        LinkedList list2 = new LinkedList();
        list2.add(6);
        list2.add(7);
        list2.add(7);
        list2.add(7);
        list2.add(7);

        LinkedList sumList = LinkedList.addReversedLists(list, list2);
        sumList.reverseOrder();
    }
}
