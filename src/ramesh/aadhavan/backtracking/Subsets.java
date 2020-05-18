package ramesh.aadhavan.backtracking;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 */
public class Subsets {

    public List<List<Integer>> subsetsBackTracking(int[] nums) {
        if(nums == null)
            return Collections.emptyList();

        List<Integer> path = new LinkedList<>();
        List<List<Integer>> results = new ArrayList<>();
        _subsets(nums, 0, nums.length, 0, path, results);
        return results;
    }

    private void _subsets(int[] nums, int begin, int end, int idx, List<Integer> path, List<List<Integer>> results) {
        if(begin > end) {
            return;
        }

        results.add(new ArrayList<>(path));
        for(int i=begin; i<end; i++) {
            if(i+1<end && nums[i] == nums[i+1])
                continue;

            path.add(idx, nums[i]);
            _subsets(nums, i+1, end, idx+1, path, results);
            path.remove(idx);
        }
    }

    public List<List<Integer>> subsetsBitMasking(int[] nums) {
        int n = nums.length;
        List<List<Integer>> powerSet = new ArrayList<>();

        for(int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n+1); i++) {
            String binary = Integer.toBinaryString(i).substring(1);
            List<Integer> set = new ArrayList<>();
            for(int j = 0; j < binary.length(); j++) {
                if(binary.charAt(j) == '1')
                    set.add(nums[j]);
            }
            powerSet.add(new ArrayList<>(set));
        }

        return powerSet;
    }
}

