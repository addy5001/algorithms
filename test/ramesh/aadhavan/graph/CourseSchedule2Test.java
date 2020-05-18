package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class CourseSchedule2Test {

    final CourseSchedule2 courseSchedule = new CourseSchedule2();

    @Test
    public void testCanFinish() {
        int[][] courses = {{1, 0}};
        Assert.assertArrayEquals(new int[] {0, 1}, courseSchedule.findOrder(2, courses));
    }

    @Test
    public void testCanFinish_valid() {
        int[][] courses = {{0,1},{1,2},{0,2}};
        Assert.assertArrayEquals(new int[] {2, 1, 0}, courseSchedule.findOrder(3, courses));
    }
}
