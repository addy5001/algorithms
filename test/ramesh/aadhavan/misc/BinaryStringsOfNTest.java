package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class BinaryStringsOfNTest {

    final BinaryStringsOfN binaryStringsOfN = new BinaryStringsOfN();

    @Test
    public void testBinaryStrings() {
        Collection<String> results = binaryStringsOfN.binaryStrings(4);
        Assert.assertEquals(8, results.size());
    }
}
