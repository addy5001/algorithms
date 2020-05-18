package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class StringCompressionTest {
    @Test
    public void testCompress() {
        char[] input = {'a', 'a', 'a', 'b', 'c', 'c'};

        final StringCompression stringCompression = new StringCompression();
        Assert.assertEquals(5, stringCompression.compress(input));
    }
}
