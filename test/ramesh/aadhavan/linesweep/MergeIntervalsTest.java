package ramesh.aadhavan.linesweep;

import org.junit.Assert;
import org.junit.Test;

public class MergeIntervalsTest {

    final MergeIntervals mergeIntervals = new MergeIntervals();

    @Test
    public void testMergeIntervals() {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] results = mergeIntervals.merge(intervals);

        Assert.assertArrayEquals(new int[] {1,6}, results[0]);
        Assert.assertArrayEquals(new int[] {8,10}, results[1]);
        Assert.assertArrayEquals(new int[] {15,18}, results[2]);
    }

    @Test
    public void testMergeIntervals_1() {
        int[][] intervals = {{1,4},{4,5},{5,6},{15,18}};
        int[][] results = mergeIntervals.merge(intervals);

        Assert.assertArrayEquals(new int[]{1,6}, results[0]);
        Assert.assertArrayEquals(new int[]{15,18}, results[1]);
    }
}
