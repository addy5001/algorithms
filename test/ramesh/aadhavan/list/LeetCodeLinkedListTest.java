package ramesh.aadhavan.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static ramesh.aadhavan.list.LeetCodeLinkedList.ListNode;
public class LeetCodeLinkedListTest {

    @Test
    public void testIsPalindrome_valid() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        Assert.assertTrue(LeetCodeLinkedList.isPalindrome(head));
        System.out.println();
    }

    @Test
    public void testSwapPairs() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);

        LeetCodeLinkedList leetCodeLinkedList = new LeetCodeLinkedList();
        ListNode newHead = leetCodeLinkedList.swapPairs(head);

        Assert.assertEquals(2, newHead.val);
        Assert.assertEquals(1, newHead.next.val);
        Assert.assertEquals(2, newHead.next.next.val);
    }

    @Test
    public void testPartition() {
        ListNode head = new ListNode(4);
        head.next = new ListNode(1);

        ListNode result = new LeetCodeLinkedList().partitionOptimized(head, 4);
        Assert.assertEquals(1, result.val);
        Assert.assertEquals(4, result.next.val);
    }

    @Test
    public void testReverseBetween() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = new LeetCodeLinkedList().reverseBetween(head, 2, 4);
        Assert.assertEquals(4, newHead.next.val);
        Assert.assertEquals(3, newHead.next.next.val);
        Assert.assertEquals(2, newHead.next.next.next.val);
    }
}
