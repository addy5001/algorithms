package ramesh.aadhavan.list;

public class LeetCodeLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l2 == null)
            return l1;

        if(l1 == null)
            return l2;

        ListNode curr = null;
        ListNode mergedList = null;

        while(l1!=null && l2!=null) {
            int x;
            if(l1.val <= l2.val) {
                x = l1.val;
                l1 = l1.next;
            }
            else {
                x = l2.val;
                l2 = l2.next;
            }

            if(mergedList == null) {
                curr = new ListNode(x);
                mergedList = curr;
            }
            else {
                curr.next = new ListNode(x);
                curr = curr.next;
            }
        }

        while(l1!=null) {
            curr.next = new ListNode(l1.val);
            curr = curr.next;
            l1 = l1.next;

        }

        while(l2!=null) {
            curr.next = new ListNode(l2.val);
            curr = curr.next;
            l2 = l2.next;
        }

        return mergedList;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sumList = null;
        ListNode head = null;

        int carryOver = 0;
        while(l1!=null || l2!=null) {
            int a = 0;
            int b = 0;

            if(l1!=null) {
                a = l1.val;
                l1 = l1.next;
            }
            if(l2!=null) {
                b = l2.val;
                l2 = l2.next;
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

            if(head==null) {
                sumList = new ListNode(lastDigit);
                head = sumList;
            }
            else {
                sumList.next = new ListNode(lastDigit);
                sumList = sumList.next;
            }
        }

        if(carryOver!=0) {
            sumList.next = new ListNode(carryOver);
        }

        return head;
    }

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return false;

        ListNode doublePointer = head.next.next;
        ListNode singlePointer = head;

        while(doublePointer.next!=null) {
            if(doublePointer.next.next == null)
                return false;

            if(doublePointer == singlePointer)
                return true;

            doublePointer = doublePointer.next.next;
            singlePointer = singlePointer.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = LeetCodeLinkedList.addTwoNumbers(l1, l2);
        System.out.print(l3.val+" "+l3.next.val+" "+l3.next.next.val);
    }
}
