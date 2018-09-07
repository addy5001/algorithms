package ramesh.aadhavan.list;

import java.util.Objects;

public class LinkedList {
    private SingleLinkedListNode head;

    public LinkedList() {
        this.head = null;
    }

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

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(0);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(6);
        list.add(7);
        list.add(8);

        System.out.println(list);
    }
}
