package ramesh.aadhavan.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class LinkedListTest {

    final LinkedList list = new LinkedList();

    @Before
    public void init() {
        IntStream.rangeClosed(1, 25).forEach(list::add);
    }

    @Test
    public void testSegmentLists_equalSegments() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(4);
        list.add(11);

        LinkedList[] segmented = list.segmentLists(3);
        Assert.assertEquals(segmented[0].length(), 1);
        Assert.assertEquals(segmented[1].length(), 1);
        Assert.assertEquals(segmented[2].length(), 1);
    }

    @Test
    public void testSegmentLists_unequalSegments() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(4);
        list.add(11);
        list.add(12);
        list.add(13);

        LinkedList[] segmented = list.segmentLists(3);
        Assert.assertEquals(segmented[0].length(), 2);
        Assert.assertEquals(segmented[1].length(), 2);
        Assert.assertEquals(segmented[2].length(), 1);
    }

    @Test
    public void testSegmentLists_lesserSegments() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(4);
        list.add(11);
        list.add(12);
        list.add(13);

        LinkedList[] segmented = list.segmentLists(7);
        Assert.assertEquals(segmented[0].length(), 1);
        Assert.assertEquals(segmented[1].length(), 1);
        Assert.assertEquals(segmented[2].length(), 1);
        Assert.assertEquals(segmented[3].length(), 1);
        Assert.assertEquals(segmented[4].length(), 1);
        Assert.assertEquals(segmented[5].length(), 0);
        Assert.assertEquals(segmented[6].length(), 0);
    }

    @Test
    public void testListReverse() {
        SingleLinkedListNode head = list.reverseListIterative(list.head);
        LinkedList listLocal = new LinkedList(head);
        System.out.println(listLocal.toString());
    }
}
