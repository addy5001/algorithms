package ramesh.aadhavan.bits;

import org.junit.Assert;
import org.junit.Test;

public class SingleNumberTest {
    final SingleNumber singleNumber = new SingleNumber();

    @Test
    public void testSingleNumber3() {
        Assert.assertEquals(1, singleNumber.singleNumber3(new int[]{2,2,1,2}));
    }
}
