package ramesh.aadhavan.revision;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    final Strings strings = new Strings();

    @Test
    public void testFindReplaceString() {
        String t = "abcdefgh";
        int[] indexes = {2, 5, 7};
        String[] sources = {"cd", "hg", "h"};
        String[] target = {"ffff", "xxxxx", "gggg"};

        Assert.assertEquals("abffffefggggg", strings.findReplaceString(t, indexes, sources, target));
    }
}
