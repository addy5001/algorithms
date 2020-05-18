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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;

        ListNode fwdPtr = head;
        int i=0;

        for(; i<n && fwdPtr!=null; i++)
            fwdPtr = fwdPtr.next;

        if(i==n && fwdPtr==null)
            return head.next;
        else if(i<n)
            return head;
        else {
            ListNode tmp = head;
            ListNode prevTmp = head;
            while(fwdPtr != null) {
                prevTmp = tmp;
                tmp = tmp.next;
                fwdPtr = fwdPtr.next;
            }

            prevTmp.next = tmp.next;
            return head;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;

        int len = length(head, 0);
        int mid = ((len & 1) == 0) ? (len/2) : (len/2 + 1);

        ListNode ptr = head;

        for(int i=0; i<mid; i++) {
            ptr = ptr.next;
        }

        ListNode curr = head;
        ptr = reverseList(ptr);

        while(ptr != null) {
            if(curr.val != ptr.val)
                return false;

            curr = curr.next;
            ptr = ptr.next;
        }

        return true;
    }

    private static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode curr = head;
        curr = curr.next;
        head.next = null;

        while(curr != null) {
            ListNode nextCurr = curr;
            nextCurr = nextCurr.next;

            curr.next = head;
            head = curr;

            curr = nextCurr;
        }

        return head;
    }

    private static int length(ListNode head, int n) {
        if(head == null)
            return n;

        return length(head.next, n+1);
    }

    public static ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return null;

        ListNode curr = head;
        ListNode prev = head;

        while(curr != null) {
            if(curr.val == val) {
                if(curr == head) {
                    head = head.next;
                    curr = head;
                    prev = head;
                }
                else if(curr.next == null) {
                    prev.next = null;
                    curr = null;
                }
                else {
                    curr.val = curr.next.val;
                    curr.next = curr.next.next;
                }

                continue;
            }

            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode newHead = null;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode vSlow = null;

        while(fast != null) {
            ListNode fastNext = fast.next;
            fast.next = slow;
            slow.next = fastNext;
            if(vSlow != null)
                vSlow.next = fast;

            if(newHead == null)
                newHead = fast;

            if(fastNext == null)
                return newHead;

            vSlow = slow;
            slow = fastNext;
            fast = fastNext.next;
        }

        return newHead;
    }

    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;

        ListNode lesser = null;
        ListNode lTemp = null;
        ListNode greater = null;
        ListNode gTemp = null;

        ListNode temp = head;

        while(temp != null) {
            if(temp.val < x) {
                if(lesser == null) {
                    lesser = new ListNode(temp.val);
                    lTemp = lesser;
                }
                else {
                    lTemp.next = new ListNode(temp.val);
                    lTemp = lTemp.next;
                }
            }
            else {
                if(greater == null) {
                    greater = new ListNode(temp.val);
                    gTemp = greater;
                }
                else {
                    gTemp.next = new ListNode(temp.val);
                    gTemp = gTemp.next;
                }
            }

            temp = temp.next;
        }

        if(lTemp != null)
            lTemp.next = greater;
        else
            return greater;

        return lesser;
    }


    public ListNode partitionOptimized(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;

        ListNode lesser = head;
        ListNode pLesser = null;
        ListNode ptr = null;
        boolean found = false;

        while(lesser != null) {
            if(lesser.val < x) {
                if(!found) {
                    ptr = lesser;
                    pLesser = lesser;
                    lesser = lesser.next;
                }
                else {
                    pLesser.next = lesser.next;
                    if (ptr == null) {
                        lesser.next = head;
                        head = lesser;
                        ptr = lesser;
                    } else {
                        lesser.next = ptr.next;
                        ptr.next = lesser;
                        ptr = ptr.next;
                    }
                    lesser = pLesser.next;
                }
            }
            else {
                found = true;
                pLesser = lesser;
                lesser = lesser.next;
            }
        }

        return head;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n)
            return head;

        ListNode ptr = head;
        ListNode prev = null;

        int i=1;
        while(i<m && ptr != null) {
            prev = ptr;
            ptr = ptr.next;
            i++;
        }

        ListNode tail = ptr;
        int j=m;
        while(j<n && tail != null) {
            tail = tail.next;
            j++;
        }

        ptr = _reverse(ptr, tail.next);
        if(prev == null)
            return ptr;

        prev.next = ptr;
        return head;
    }

    private ListNode _reverse(ListNode head, ListNode nextNode) {
        if(head == null || head == nextNode || head.next == nextNode)
            return head;

        ListNode rest = _reverse(head.next, nextNode);
        head.next.next = head;
        head.next = nextNode;

        return rest;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(3);

        l1 = removeElements(l1, 1);
        System.out.println(l1.val+" ");
    }
}
