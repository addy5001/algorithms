package ramesh.aadhavan.misc;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        return _uniquePaths(0, 0, m, n);
    }

    private int _uniquePaths(int i, int j, int m, int n) {
        if(i>=m || j>=n)
            return 0;

        if(i==(m-1) && j==(n-1))
            return 1;

        return _uniquePaths(i+1, j, m, n) + _uniquePaths(i, j+1, m, n);
    }

    public int uniquePathsMemoized(int m, int n) {
        int[][] cache = new int[m][n];
        _uniquePathsMemoized(0, 0, m, n, cache);

        return cache[0][0];
    }

    private void _uniquePathsMemoized(int i, int j, int m, int n, int[][] cache) {
        if(i == (m-1)) {
            cache[i][j] = 1;
        }
        else if(j == (n-1)) {
            cache[i][j] = 1;
        }
        else {
            if(cache[i+1][j] == 0)
                _uniquePathsMemoized(i+1, j, m, n, cache);

            if(cache[i][j+1] == 0)
                _uniquePathsMemoized(i, j+1, m, n, cache);

            cache[i][j] = cache[i+1][j] + cache[i][j+1];
        }
    }
}
