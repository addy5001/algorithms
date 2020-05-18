package ramesh.aadhavan.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SubsetsTest {

    Subsets subsets = new Subsets();

    @Test
    public void testSubsets() {
        int[] nums = {1,1,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4};
        List<List<Integer>> results = subsets.subsetsBackTracking(nums);
        Assert.assertEquals(16, results.size());
        results.forEach(System.out::println);
    }

    @Test
    public void testSubsetsBitMasking() {
        int[] nums = {1,2,3,4};
        List<List<Integer>> results = subsets.subsetsBitMasking(nums);
        Assert.assertEquals(16, results.size());
    }
}
