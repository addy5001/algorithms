package ramesh.aadhavan.list;

public class DoublyLinkedListNode<T> {
    private final T val;
    private DoublyLinkedListNode<T> next;
    private DoublyLinkedListNode<T> prev;

    public DoublyLinkedListNode(T val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }

    public T getVal() {
        return val;
    }

    public DoublyLinkedListNode<T> getNext() {
        return next;
    }

    public DoublyLinkedListNode<T> getPrev() {
        return prev;
    }

    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public void setPrev(DoublyLinkedListNode<T> prev) {
        this.prev = prev;
    }
}
