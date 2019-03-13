package ramesh.aadhavan.list;

public class LeetCodeLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
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
