package ramesh.aadhavan.misc;

import org.junit.Test;
import ramesh.aadhavan.backtracking.CombinationSum;

import java.util.List;

public class CombinationSumTest {

    private CombinationSum combinationSum = new CombinationSum();

    @Test
    public void testCombinationSum() {
        int[] candidates = new int[]{2,3,6,7};
        List<List<Integer>> results = combinationSum.combinationSum(candidates, 7);
        results.forEach(result -> System.out.println(result.toString()));
    }
}
