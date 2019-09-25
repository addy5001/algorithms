package ramesh.aadhavan.list;

public class SingleLinkedListNode implements Node {
    public int value;
    public SingleLinkedListNode next;

    public SingleLinkedListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public SingleLinkedListNode(int value, SingleLinkedListNode next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public int getValue() {
        return value;
    }

    public SingleLinkedListNode getNext() {
        return next;
    }

    public void setNext(SingleLinkedListNode next) {
        this.next = next;
    }
}
