package ramesh.aadhavan.list;

import com.google.common.annotations.VisibleForTesting;

import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class LinkedList {
    SingleLinkedListNode head;

    public LinkedList() {
        this.head = null;
    }

    public LinkedList(SingleLinkedListNode head) { this.head = head; }

    private void setHead(SingleLinkedListNode head) {
        this.head = head;
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

    private SingleLinkedListNode _addRecursive(SingleLinkedListNode head, int val) {
        if(head == null)
            return new SingleLinkedListNode(val);

        head.next = _addRecursive(head.next, val);
        return head;
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

    @VisibleForTesting
    SingleLinkedListNode reverseList(SingleLinkedListNode node) {
        if(node == null || node.next == null)
            return node;

        SingleLinkedListNode rest = reverseList(node.next);
        node.next.next = node;
        node.next = null;

        return rest;
    }

    @VisibleForTesting
    SingleLinkedListNode reverseListIterative(SingleLinkedListNode node) {
        if(node == null || node.next == null)
            return node;

        SingleLinkedListNode prev = node;
        SingleLinkedListNode fwd = node.next;

        prev.next = null;

        while(fwd != null) {
            SingleLinkedListNode temp = fwd.next;
            fwd.next = prev;
            prev = fwd;
            fwd = temp;
        }

        return prev;
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
        head = removeRecursive(head, val);
    }

    private SingleLinkedListNode removeRecursive(SingleLinkedListNode node, int val) {
        if(node == null)
            return null;

        if(val == node.value) {
            return node.next;
        }
        else {
            node.next = removeRecursive(node.next, val);
            return node;
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

        int len = findLength(head, 0);

        if(len <= k)
            head = reverseList(head);
        else {
            SingleLinkedListNode pointer = head;
            SingleLinkedListNode kBehind = head;
            SingleLinkedListNode prevKBehind = head;
            int reversed = 0;
            boolean swapped = false;

            while(reversed < len) {
                int counter = 0;
                for(;counter < k; counter++) {
                    if (pointer == null)
                        break;
                    pointer = pointer.next;
                }

                if(!swapped) {
                    if (reversed == 0) {
                        head = reverseKNodes(head, pointer, 0, k - 1);
                    }
                    else {
                        prevKBehind.next = reverseKNodes(kBehind, pointer, 0, k-1);
                    }

                    kBehind = pointer;
                    swapped = true;
                    reversed += counter;
                }
                else {
                    for(int i=0; i<k; i++) {
                        if(kBehind == null)
                            break;
                        prevKBehind = kBehind;
                        kBehind = kBehind.next;
                    }
                    swapped = false;
                    reversed+=counter;
                }
            }
        }
    }

    private SingleLinkedListNode reverseKNodes(SingleLinkedListNode node, SingleLinkedListNode nextInList, int pointer, int length) {
        if(node == null || node.next == null || pointer == length) {
            return node;
        }

        SingleLinkedListNode rest = reverseKNodes(node.next, nextInList, pointer+1, length);
        node.next.next = node;
        node.next = nextInList;

        return rest;
    }

    private static int findLength(SingleLinkedListNode node, int len) {
        if(node == null)
            return len;

        return findLength(node.next, len+1);
    }

    public static void sumOfListsAsNumbers(SingleLinkedListNode list1, SingleLinkedListNode list2) {
        Deque<Integer> stack1 = new java.util.LinkedList<>();
        Deque<Integer> stack2 = new java.util.LinkedList<>();

        while(list1 != null) {
            stack1.push(list1.value);
            list1 = list1.next;
        }

        while(list2 != null) {
            stack2.push(list2.value);
            list2 = list2.next;
        }

        Deque<Integer> result = new java.util.LinkedList<>();
        int carry = 0;
        while(stack1.peek() != null && stack2.peek() != null) {
            int sum = carry + stack1.pop() + stack2.pop();
            int digit = sum % 10;
            carry = sum/10;
            result.push(digit);
        }

        while(stack1.peek() != null) {
            int sum = carry + stack1.pop();
            int digit = sum % 10;
            carry = sum/10;
            result.push(digit);
        }

        while(stack2.peek() != null) {
            int sum = carry + stack2.pop();
            int digit = sum % 10;
            carry = sum/10;
            result.push(digit);
        }

        if(carry > 0)
            result.push(carry);

        while(result.peek() != null)
            System.out.print(result.pop());
    }

    public static LinkedList sumOfListsAsNumbersRecursive(SingleLinkedListNode node1, SingleLinkedListNode node2) {
        int len1 = findLength(node1, 0);
        int len2 = findLength(node2, 0);

        SingleLinkedListNode bigger, smaller;

        if(len1 > len2) {
            bigger = node1;
            smaller = node2;
        }
        else {
            bigger = node2;
            smaller = node1;
        }

        int diff = Math.abs(len1 - len2);
        Deque<Integer> result = new java.util.LinkedList<>();
        _sumOfListsAsNumbersRecursive(bigger, smaller, result, diff, diff);
        LinkedList resultList = new LinkedList();

        while(result.peek() != null) {
            resultList.add(result.pop());
        }

        return resultList;
    }

    private static int _sumOfListsAsNumbersRecursive(SingleLinkedListNode bigger,
                                              SingleLinkedListNode smaller,
                                              Deque<Integer> result,
                                              int diff, int counter) {
        if(counter > 0 && bigger != null) {
            int sum = bigger.value + _sumOfListsAsNumbersRecursive(bigger.next, smaller, result, diff, counter-1);
            int carry = sum/10;
            int digit = sum%10;
            result.push(digit);
            if(counter == diff)
                result.push(carry);

            return carry;
        }
        else {
            if(bigger != null && smaller != null) {
                int sum = bigger.value + smaller.value + _sumOfListsAsNumbersRecursive(bigger.next, smaller.next, result, diff, counter-1);
                int carry = sum/10;
                int digit = sum%10;
                result.push(digit);
                if(counter == diff)
                    result.push(carry);

                return carry;

            }
            else {
                return 0;
            }
        }
    }

    public void reverseListIterative() {
        if(head == null || head.next == null)
            return;

        SingleLinkedListNode curr = head;
        curr = curr.next;
        head.next = null;

        while(curr != null) {
            SingleLinkedListNode nextCurr = curr;
            nextCurr = nextCurr.next;

            curr.next = head;
            head = curr;

            curr = nextCurr;
        }
    }

    public static LinkedList mergeKSortedLists(LinkedList[] lists, int k) {
        LinkedList sortedList = new LinkedList();
        Integer nextMin = findNextMin(lists, k);
        while (nextMin != null) {
            sortedList.add(nextMin);
            nextMin = findNextMin(lists, k);
        }

        return sortedList;
    }

    private static Integer findNextMin(LinkedList[] lists, int k) {
        int min = Integer.MAX_VALUE;
        int minNodeIndex = 0;
        int i = 0;
        boolean found = false;

        while(i < k) {
            if(lists[i] != null && lists[i].head != null) {
                if(min > lists[i].head.value) {
                    min = lists[i].head.value;
                    minNodeIndex = i;
                    found = true;
                }
            }
            i++;
        }

        if(found) {
            lists[minNodeIndex].head = lists[minNodeIndex].head.next;
            return min;
        }
        return null;
    }

    public static LinkedList mergeKSortedListsDivideAndConquer(LinkedList[] lists) {
        return _mergeKSortedListsDivideAndConquer(lists, 0, lists.length-1);
    }

    private static LinkedList _mergeKSortedListsDivideAndConquer(LinkedList[] lists, int start, int end) {
        if(start == end)
            return lists[start];

        if(start < end) {
            int mid = (end + start)/2;
            LinkedList sorted1 = _mergeKSortedListsDivideAndConquer(lists, start, mid);
            LinkedList sorted2 = _mergeKSortedListsDivideAndConquer(lists, mid+1, end);
            return merge2SortedLists(sorted1, sorted2);
        }

        return null;
    }

    public static LinkedList merge2SortedLists(LinkedList list1, LinkedList list2) {
        if(list1 == null && list2 == null)
            return null;

        if(list1 == null)
            return list2;

        if(list2 == null)
            return list1;

        SingleLinkedListNode node1 = list1.head;
        SingleLinkedListNode node2 = list2.head;
        SingleLinkedListNode prevNode1 = list1.head;

        while(node1 != null && node2 != null) {
            if(node1.value >= node2.value) {
                SingleLinkedListNode tmp = node2;
                if(node1 == list1.head) {
                    list1.head = tmp;
                    prevNode1 = list1.head;
                }
                else {
                    prevNode1.next = tmp;
                    prevNode1 = prevNode1.next;
                }
                node2 = node2.next;
                tmp.next = node1;
            }
            else {
                prevNode1 = node1;
                node1 = node1.next;
            }
        }

        if(node2 != null) {
            prevNode1.next = node2;
        }

        return list1;
    }

    public static SingleLinkedListNode rotateList(SingleLinkedListNode head, int k) {
        int n = findLength(head, 0);
        int rotateLen = k % n;
        if(rotateLen == 0)
            return head;

        SingleLinkedListNode toRotateNode = _getKthFromEnd(head, rotateLen);

        SingleLinkedListNode headPtr = head;
        while(headPtr.next != toRotateNode) {
            headPtr = headPtr.next;
        }

        SingleLinkedListNode ptr = toRotateNode;
        while(ptr.next != null) {
            ptr = ptr.next;
        }

        ptr.next = head;
        headPtr.next = null;
        head = toRotateNode;

        return head;
    }

    private static SingleLinkedListNode _getKthFromEnd(SingleLinkedListNode node, int k) {
        SingleLinkedListNode ptr = node;
        while(k>0 && ptr!=null) {
            ptr = ptr.next;
            k--;
        }

        while(ptr!=null) {
            node = node.next;
            ptr = ptr.next;
        }

        return node;
    }

    public LinkedList[] segmentLists(int k) {
        LinkedList[] segmentedLists = new LinkedList[k];
        for(int i=0; i<k; i++)
            segmentedLists[i] = new LinkedList();

        if(head == null) {
            return segmentedLists;
        }

        int n = findLength(head, 0);
        int segment = n/k;
        int remainders = n%k;
        int partitionCounter = 0;

        SingleLinkedListNode ptr = head;
        while(ptr != null) {
            SingleLinkedListNode newList = ptr;
            SingleLinkedListNode prev = ptr;
            for(int i=0; i<segment && ptr!=null; i++) {
                prev = ptr;
                ptr = ptr.next;
            }

            if(remainders > 0 && ptr!=null) {
                prev = ptr;
                ptr = ptr.next;
                remainders--;
            }

            prev.next = null;
            segmentedLists[partitionCounter].setHead(newList);
            partitionCounter++;
        }

        return segmentedLists;
    }

    public int length() {
        return findLength(head, 0);
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(4);
        list.add(11);

        list.removeRecursive(4);
        list.removeRecursive(3);
        list.removeRecursive(11);

        LinkedList list2 = new LinkedList();
        list2.add(2);
        list2.add(3);
        list2.add(10);

        LinkedList list3 = new LinkedList();
        list3.add(5);
        list3.add(6);
        list3.add(9);

        LinkedList list4 = new LinkedList();
        list4.add(15);
        list4.add(16);
        list4.add(19);

        LinkedList list5 = new LinkedList();
        list5.add(0);
        list5.add(100);
        list5.add(200);

//        LinkedList[] lists = new LinkedList[5];
//        lists[0] = list;
//        lists[1] = list2;
//        lists[2] = list3;
//        lists[3] = list4;
//        lists[4] = list5;
//
//        list = mergeKSortedListsDivideAndConquer(lists);


        list5.head = rotateList(list5.head, 4);
    }
}
