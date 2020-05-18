package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class CourseScheduleTest {

    final CourseSchedule courseSchedule = new CourseSchedule();

    @Test
    public void testCanFinish() {
        int[][] courses = {{0,1},{1,2},{2,0}};
        Assert.assertFalse(courseSchedule.canFinish(3, courses));
    }

    @Test
    public void testCanFinish_valid() {
        int[][] courses = {{0,1},{1,2},{0,2}};
        Assert.assertTrue(courseSchedule.canFinish(3, courses));
    }
}
