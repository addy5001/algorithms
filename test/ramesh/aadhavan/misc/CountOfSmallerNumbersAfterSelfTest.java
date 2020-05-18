package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class CountOfSmallerNumbersAfterSelfTest {
    final CountOfSmallerNumbersAfterSelf countOfSmallerNumbersAfterSelf = new CountOfSmallerNumbersAfterSelf();

    @Test
    public void testCountOfSmallerNumbersAfterSelf() {
        int[] nums = {5,2,6,1};
        Assert.assertArrayEquals(new int[] {2,1,1,0},
                countOfSmallerNumbersAfterSelf.countOfSmallerNumbersAfterSelf(nums));
    }
}
