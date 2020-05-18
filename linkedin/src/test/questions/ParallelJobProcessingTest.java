package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelJobProcessingTest {

    @Test
    public void testExecute() {
        final ParallelJobProcessing processing = new ParallelJobProcessing();
        List<String> inputs = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> "input".concat(Integer.toString(i)))
                .collect(Collectors.toList());

        processing.execute(inputs);
    }

    @Test
    public void testExecuteMultiplyBy2Task() {
        final ParallelJobProcessing processing = new ParallelJobProcessing();
        List<Integer> inputs = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());

        Assert.assertEquals(10100, processing.multiplyBy2(inputs).intValue());
    }
}
