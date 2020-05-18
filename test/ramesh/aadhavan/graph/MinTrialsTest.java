package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

public class MinTrialsTest {

    final MinTrials minTrials = new MinTrials();

    @Test
    public void testFindMinTrials() {
        String[] dictionary = {"AICC", "ICC", "ACC", "BCCI", "CCI", "MCC", "MCA"};
        minTrials.createGraph(dictionary);
        Assert.assertEquals(3, minTrials.findMinTrials("AICC", "MCA"));
        Assert.assertEquals("00", Integer.toBinaryString((int) Math.pow(2,2)).substring(1));
    }
}
