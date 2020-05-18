package ramesh.aadhavan.strings;

import org.junit.Assert;
import org.junit.Test;

public class KmpAlgorithmTest {

    @Test
    public void testBuildLookupTable() {
        int[] lookup = new KmpAlgorithm()._buildLookupTable("aabaabaaa");
        Assert.assertArrayEquals(new int[]{0,1,0,1,2,3,4,5,2}, lookup);
    }
}
