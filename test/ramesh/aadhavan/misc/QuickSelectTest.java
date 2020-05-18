package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class QuickSelectTest {

    @Test
    public void testSelect() {
        int[] arr = {34, 23, 2, 3, 5, 12, 76};

        Assert.assertEquals(2, QuickSelect.select(arr, 0));
    }
}
