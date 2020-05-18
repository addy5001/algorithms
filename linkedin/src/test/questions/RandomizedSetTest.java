package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class RandomizedSetTest {

    @Test
    public void testInsert() {
        RandomizedSet randomizedSet = new RandomizedSet();
        Assert.assertTrue(randomizedSet.insert(1));
        Assert.assertFalse(randomizedSet.remove(2));
        Assert.assertTrue(randomizedSet.insert(2));
        Assert.assertTrue(randomizedSet.remove(1));
        Assert.assertFalse(randomizedSet.insert(2));

        System.out.println(Objects.hash(-10000));
    }
}
