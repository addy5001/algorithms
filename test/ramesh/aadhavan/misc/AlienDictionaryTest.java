package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class AlienDictionaryTest {

    final AlienDictionary alienDictionary = new AlienDictionary();

    @Test
    public void testIsAlienSorted() {
        Assert.assertTrue(alienDictionary.isAlienSorted(new String[] {"hello","leetcode"},
                "hlabcdefgijkmnopqrstuvwxyz"));
    }
}
