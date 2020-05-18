package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class RelativeRanksTest {

    RelativeRanks relativeRanks = new RelativeRanks();

    @Test
    public void testFindRelativeRanks() {
        String[] actual = relativeRanks.findRelativeRanks(new int[]{5,4,3,2,1});
        String[] expected = new String[] {"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"};

        Assert.assertArrayEquals(expected, actual);
    }
}
