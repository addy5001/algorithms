package ramesh.aadhavan.sets;

import org.junit.Assert;
import org.junit.Test;

public class MyHashSetTest {

    @Test
    public void testAdd() {
        MyHashSet hashSet = new MyHashSet();

        for(int i=0; i<10000; i++) {
            hashSet.add(i);
        }

        System.out.println(hashSet.getOccupancy());

        Assert.assertTrue(hashSet.contains(5000));
        Assert.assertTrue(hashSet.contains(9999));
        Assert.assertTrue(hashSet.contains(45));
        Assert.assertFalse(hashSet.contains(7676767));
    }

    @Test
    public void testRemove() {
        MyHashSet hashSet = new MyHashSet();

        for(int i=0; i<10000; i++) {
            hashSet.add(i);
        }

        hashSet.remove(5000);
        hashSet.remove(45);

        Assert.assertFalse(hashSet.contains(45));
        Assert.assertFalse(hashSet.contains(5000));
    }
}
