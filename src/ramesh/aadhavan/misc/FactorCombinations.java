package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        _getFactors(2, 1, n, new ArrayList<>(), results);
        return results;
    }

    private void _getFactors(int start, int product, int n, List<Integer> factors, List<List<Integer>> results) {
        if(start > n || product > n)
            return;

        if(product == n) {
            results.add(new ArrayList<>(factors));
            return;
        }

        for(int i=start; i<n; i++) {
            if(i * product > n)
                break;

            if(n%i == 0) {
                factors.add(i);
                _getFactors(i, i*product, n, factors, results);
                factors.remove(factors.size() - 1);
            }
        }
    }
}
