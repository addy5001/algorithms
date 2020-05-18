package ramesh.aadhavan.list;

import org.junit.Assert;
import org.junit.Test;

public class MyLinkedListTest {

    @Test
    public void testAddAtHead() {
        MyLinkedList myLinkedList = new MyLinkedList();

        for(int i=1; i<=1000; i++) {
            myLinkedList.addAtHead(i*3);
        }

        Assert.assertEquals(3, myLinkedList.get(999));
        Assert.assertEquals(3000, myLinkedList.get(0));
    }

    @Test
    public void testAddAtTail() {
        MyLinkedList myLinkedList = new MyLinkedList();

        for(int i=1; i<=1000; i++) {
            myLinkedList.addAtTail(i*3);
        }

        Assert.assertEquals(3, myLinkedList.get(0));
        Assert.assertEquals(3000, myLinkedList.get(999));
    }

    @Test
    public void testAddAtIndex() {
        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.addAtIndex(0, 1);
        myLinkedList.addAtIndex(1, 2);
        myLinkedList.addAtIndex(0, 3);
        myLinkedList.addAtIndex(1, 4);

        Assert.assertEquals(3, myLinkedList.get(0));
        Assert.assertEquals(2, myLinkedList.get(3));
        Assert.assertEquals(4, myLinkedList.get(1));
        Assert.assertEquals(1, myLinkedList.get(2));
    }

    @Test
    public void testDeleteAtIndex() {
        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.addAtIndex(0, 1);
        myLinkedList.addAtIndex(1, 2);
        myLinkedList.addAtIndex(0, 3);
        myLinkedList.addAtIndex(1, 4);


        myLinkedList.deleteAtIndex(1);
        myLinkedList.deleteAtIndex(1);

        Assert.assertEquals(3, myLinkedList.get(0));
        Assert.assertEquals(2, myLinkedList.get(1));
    }
}
