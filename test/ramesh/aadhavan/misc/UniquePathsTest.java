package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class UniquePathsTest {

    UniquePaths uniquePaths = new UniquePaths();

    @Test
    public void testUniquePaths() {
        Assert.assertEquals(28, uniquePaths.uniquePaths(30, 30));
    }

    @Test
    public void testUniquePathsMemoized() {
        Assert.assertEquals(28, uniquePaths.uniquePathsMemoized(30, 30));
    }
}
