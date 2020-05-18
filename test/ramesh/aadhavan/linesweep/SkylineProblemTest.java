package ramesh.aadhavan.linesweep;

import org.junit.Test;

public class SkylineProblemTest {

    SkylineProblem skylineProblem = new SkylineProblem();

    @Test
    public void testFindSkyline() {
        int[][] buildings = {{2,4,7}, {2,4,5}, {2,4,6}};
        skylineProblem.getSkyline(buildings);
    }
}
