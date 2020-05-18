package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SummaryRangesTest {

    final SummaryRanges summaryRanges = new SummaryRanges();

    @Test
    public void testSummaryRanges() {
        int[] arr = {0, 1, 2, 4, 5, 7};
        List<String> ranges = summaryRanges.summaryRanges(arr);
        Assert.assertEquals(3, ranges.size());
        Assert.assertEquals("0->2", ranges.get(0));
        Assert.assertEquals("4->5", ranges.get(1));
        Assert.assertEquals("7", ranges.get(2));
    }
}
