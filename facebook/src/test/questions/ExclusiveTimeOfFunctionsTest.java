package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ExclusiveTimeOfFunctionsTest {
    final ExclusiveTimeOfFunctions exclusiveTimeOfFunctions = new ExclusiveTimeOfFunctions();

    @Test
    public void testExclusiveTime() {
        List<String> logs = List.of("0:start:0","1:start:2","1:end:5","0:end:6");
        int n = 2;
        Assert.assertArrayEquals(new int[] {3, 4}, exclusiveTimeOfFunctions.exclusiveTime(n, logs));
    }
}
