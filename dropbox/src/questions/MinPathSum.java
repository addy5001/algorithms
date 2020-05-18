package questions;

public class MinPathSum {
    public int minPathSum(int[][] grid) {
        return _minPathSumDynamicSpaceEfficient(grid);
    }

    private int _minPathSumRecursive(int[][] grid, int m, int n) {
        if(m < 0 || n < 0 || m >= grid.length || n >= grid[0].length)
            return Integer.MAX_VALUE;

        if(m == 0 && n == 0)
            return grid[0][0];

        return grid[m][n] +
                Math.min(_minPathSumRecursive(grid, m-1, n), _minPathSumRecursive(grid, m, n-1));
    }

    private int _minPathSumDynamic(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] matrix = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 && j==0) {
                    matrix[0][0] = grid[0][0];
                }
                else if(i==0) {
                    matrix[i][j] = matrix[i][j-1] + grid[i][j];
                }
                else if(j==0) {
                    matrix[i][j] = matrix[i-1][j] + grid[i][j];
                }
                else {
                    matrix[i][j] = grid[i][j] + Math.min(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        return matrix[m-1][n-1];
    }

    private int _minPathSumDynamicSpaceEfficient(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 && j==0) {
                    continue;
                }
                else if(i==0) {
                    grid[i][j] = grid[i][j] + grid[i][j-1];
                }
                else if(j==0) {
                    grid[i][j] = grid[i][j] + grid[i-1][j];
                }
                else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }

        return grid[m-1][n-1];
    }
}
