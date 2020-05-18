package ramesh.aadhavan.list;

public class DoublyLinkedList<T> {
    private DoublyLinkedListNode<T> head;

    public DoublyLinkedList() {
        head = null;
    }

    @SuppressWarnings("unchecked")
    public void add(T item) {
        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(item);
        if(head == null) {
            head = node;
            head.setNext(null);
            head.setPrev(null);
        }
        else {
            DoublyLinkedListNode ptr = head;
            while(ptr.getNext() != null)
                ptr = ptr.getNext();

            ptr.setNext(node);

            node.setPrev(ptr);
            node.setNext(null);
        }
    }

    public int length() {
        int len = 0;
        DoublyLinkedListNode node = head;
        while(node != null) {
            len++;
            node = node.getNext();
        }

        return len;
    }

    public DoublyLinkedListNode<T> getHead() {
        return head;
    }
}
