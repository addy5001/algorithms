package questions;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        return _merge(lists);
    }

    private ListNode _merge(ListNode[] lists) {
        ListNode result = null;
        int maxLen = 0;

        for(int i=0; i<lists.length; i++) {
            maxLen += _getLength(lists[i]);
        }

        ListNode temp = null;
        for(int i=0; i<maxLen; i++) {
            int j=0;
            int chosen = 0;
            int min = Integer.MAX_VALUE;
            for(; j<lists.length; j++) {
                if(lists[j] == null)
                    continue;

                if(min > lists[j].val) {
                    min = lists[j].val;
                    chosen = j;
                }
            }

            if(temp == null) {
                result = new ListNode(min);
                temp = result;
            }
            else {
                temp.next = new ListNode(min);
                temp = temp.next;
            }

            lists[chosen] = lists[chosen].next;
        }

        return result;
    }

    private int _getLength(ListNode list) {
        if(list == null)
            return 0;

        ListNode temp = list;
        int count = 0;

        while(temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }

    public static ListNode mergeKSortedListsDivideAndConquer(ListNode[] lists) {
        return _mergeKSortedListsDivideAndConquer(lists, 0, lists.length-1);
    }

    private static ListNode _mergeKSortedListsDivideAndConquer(ListNode[] lists, int start, int end) {
        if(start == end)
            return lists[start];

        if(start < end) {
            int mid = (end + start)/2;
            ListNode sorted1 = _mergeKSortedListsDivideAndConquer(lists, start, mid);
            ListNode sorted2 = _mergeKSortedListsDivideAndConquer(lists, mid+1, end);
            return merge2SortedLists(sorted1, sorted2);
        }

        return null;
    }

    public static ListNode merge2SortedLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null)
            return null;

        if(list1 == null)
            return list2;

        if(list2 == null)
            return list1;

        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode prevNode1 = list1;

        while(node1 != null && node2 != null) {
            if(node1.val >= node2.val) {
                ListNode tmp = node2;
                if(node1 == list1) {
                    list1 = tmp;
                    prevNode1 = list1;
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
}
