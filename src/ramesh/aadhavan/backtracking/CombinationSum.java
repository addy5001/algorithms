package ramesh.aadhavan.backtracking;

import java.util.*;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> path = new LinkedList<>();
        _combination(candidates, target, 0, 0, candidates.length, 0, path, results);
        return results;
    }

    private void _combination(int[] candidates,
                                 int target, int sum,
                                 int begin, int end, int idx,
                                 List<Integer> path,
                                 List<List<Integer>> results) {
        if(sum > target) {
            return;
        }

        if(sum == target) {
            results.add(new ArrayList<>(path));
            return;
        }

        for(int i=begin; i<end; i++) {
            path.add(idx, candidates[i]);
            _combination(candidates, target, sum + candidates[i],
                    i, end, idx + 1, path, results);
            path.remove(idx);
        }
    }
}
