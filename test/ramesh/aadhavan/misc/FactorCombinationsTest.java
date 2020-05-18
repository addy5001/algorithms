package ramesh.aadhavan.misc;

import org.junit.Test;

import java.util.List;

public class FactorCombinationsTest {

    final FactorCombinations factorCombinations = new FactorCombinations();

    @Test
    public void testGetFactors() {
        List<List<Integer>> results = factorCombinations.getFactors(100);
        results.forEach(list -> System.out.println(list.toString()));
    }
}
