package ramesh.aadhavan.list;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

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

    public void addRecursive(int val) {
        if(head == null) {
            head = new SingleLinkedListNode(val);
        }
        else {
            addRecursive(head, val);
        }
    }

    private void addRecursive(SingleLinkedListNode node, int val) {
        if(node.getNext() == null) {
            node.setNext(new SingleLinkedListNode(val));
            return;
        }

        addRecursive(node.getNext(), val);
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

    public void reverseList() {
        head = reverseList(head);
    }

    public SingleLinkedListNode reverseList(SingleLinkedListNode node) {
        if(node == null || node.next == null)
            return node;

        SingleLinkedListNode rest = reverseList(node.next);
        node.next.next = node;
        node.next = null;

        return rest;
    }

    public int findNthFromEnd(int n) {
        SingleLinkedListNode node1, node2;
        node1 = node2 = head;
        for(int i=0; i<n; i++) {
            if(node1 == null) {
                return -1;
            }
            node1 = node1.getNext();
        }

        while(node1 != null) {
            node1 = node1.getNext();
            node2 = node2.getNext();
        }

        return node2 == null ? -1 : node2.getValue();
    }

    public int sumRecursive() {
        return sumRecursive(head, 0);
    }

    private int sumRecursive(SingleLinkedListNode node, int sum) {
        if(node == null) {
            return sum;
        }
        return sumRecursive(node.getNext(), sum + node.getValue());
    }

    public void removeRecursive(int val) {
        if(head.getValue() == val) {
            head = head.getNext();
        }
        else {
            if(!removeRecursive(head, val))
                System.out.println("Element not found!");
        }
    }

    private boolean removeRecursive(SingleLinkedListNode node, int val) {
        if(node.getNext() != null) {
            if(node.getNext().getValue() == val) {
                SingleLinkedListNode replace = node.getNext().getNext();
                node.setNext(replace);
                return true;
            }
            return removeRecursive(node.getNext(), val);
        }
        else {
            return false;
        }
    }

    private Map<Integer, Integer> getSums(SingleLinkedListNode node, int index, int sum, Map<Integer, Integer> sumMap) {

        if(node == null)
            return sumMap;

        sumMap.put(index, sum + node.getValue());
        return getSums(node.getNext(), index + 1, sum + node.getValue(), sumMap);
    }

    private void getSumsReverse(SingleLinkedListNode node, int index, Map<Integer, Integer> sumMap) {

        if(node == null)
            return;

        if(node.getNext() == null) {
            sumMap.put(index, node.getValue());
            return;
        }

        getSumsReverse(node.getNext(), index + 1, sumMap);
        sumMap.put(index, sumMap.get(index + 1) + node.getValue());
    }

    public void reverseKAlternateNodes(int k) {
        if(head == null || head.next == null)
            return;

        boolean swapped = false;
        int[] arr = new int[k];
        SingleLinkedListNode node = head;

        while(node != null) {
            int counter = 1;
            for(int i=0; i<k && node!=null; i++, counter++) {
                arr[i] = node.value;
                node = node.next;
            }

            if(!swapped) {
                reverseArray(arr, counter-1);
                node = copyFromArray(node, arr, counter-1);
                swapped = true;
            }
            else {
                swapped = false;
            }
        }
    }

    private int findLength(SingleLinkedListNode node, int len) {
        if(node == null)
            return len;

        return findLength(node.next, len+1);
    }

    private void reverseArray(int[] arr, int length) {
        int mid = length/2;
        int end = length - 1;

        for(int i=0; i<mid; i++) {
            int tmp = arr[i];
            arr[i] = arr[end];
            arr[end] = tmp;
            end--;
        }
    }

    private SingleLinkedListNode copyFromArray(SingleLinkedListNode node, int[] arr, int k) {
        if(node == null)
            return node;

        SingleLinkedListNode tracker = node;
        for(int i=0; i<k; i++, tracker = tracker.next) {
            if(tracker == null)
                return node;
            tracker.value = arr[i];
        }
        return node;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        IntStream.range(1, 20).forEach(list::add);
        System.out.println(list.toString());
        list.reverseKAlternateNodes(2);
        System.out.println(list.toString());
    }


}
