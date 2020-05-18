package ramesh.aadhavan.misc;

import ramesh.aadhavan.list.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LruCache<T> {
    private final int cacheSize;
    private DoublyLinkedListNode<Integer> head;
    private DoublyLinkedListNode<Integer> tail;
    private Map<Integer, Object> cache;
    private int currentSize;


    public LruCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.currentSize = 0;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    @SuppressWarnings("unchecked")
    public T get(Integer key) {
        if(head == null)
            return null;

        DoublyLinkedListNode<Integer> node = head;
        DoublyLinkedListNode<Integer> prev = head;
        boolean found = false;

        while(node != null) {
            if(key == node.getVal()) {
                found = true;
                break;
            }

            prev = node;
            node = node.getNext();
        }

        if(!found)
            return null;

        if(node == head) {
            return (T) cache.get(head.getVal());
        }

        DoublyLinkedListNode<Integer> nextNode = node.getNext();
        node.setNext(head);
        head.setPrev(node);
        node.setPrev(null);
        head = node;

        if(nextNode == null) {
            prev.setNext(null);
            tail = prev;
        }
        else {
            prev.setNext(nextNode);
            nextNode.setPrev(prev);
        }

        return (T) cache.get(head.getVal());
    }

    public void put(Integer key, T val) {
        if(head == null) {
            head = new DoublyLinkedListNode<>(key);
            tail = head;
            currentSize = 1;
            cache.put(key, val);
            return;
        }

        DoublyLinkedListNode<Integer> node = new DoublyLinkedListNode<>(key);
        node.setNext(head);
        head.setPrev(node);
        node.setPrev(null);
        head = node;
        currentSize++;

        if(currentSize > cacheSize) {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        cache.put(key, val);
    }

    public static void main(String[] args) {
    }
}
