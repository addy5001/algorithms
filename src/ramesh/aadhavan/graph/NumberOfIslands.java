package ramesh.aadhavan.graph;

public class NumberOfIslands {

    public static int findIslands(int[][] graph, int m, int n) {
        return _findIslands(graph, m, n);
    }

    private static int _findIslands(int[][] graph, int m, int n) {
        int numIslands=0;
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(graph[i][j] == 1 && !visited[i][j]) {
                    numIslands++;
                    _dfsVisit(graph, i, j, m, n, visited);
                }
            }
        }

        return numIslands;
    }

    private static void _dfsVisit(int[][] graph, int i, int j,
                                   int m, int n, boolean[][] visited) {
        if(i<0 || j<0 || i>=m || j>=n || graph[i][j]==0 || visited[i][j])
            return;

        visited[i][j] = true;

        _dfsVisit(graph, i-1, j, m, n, visited);
        _dfsVisit(graph, i, j-1, m, n, visited);
        _dfsVisit(graph, i+1, j, m, n, visited);
        _dfsVisit(graph, i, j+1, m, n, visited);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int maxSize = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    int size = _maxAreaOfIsland(grid, visited, m, n, i, j);
                    if(size > maxSize)
                        maxSize = size;
                }
            }
        }

        return maxSize;
    }

    private static int _maxAreaOfIsland(int[][] grid, boolean[][] visited, int m, int n, int i, int j) {
        if(i < 0 || i >= m || j < 0 || j >= n
                || grid[i][j] == 0 || visited[i][j])
            return 0;

        visited[i][j] = true;

        return 1 + _maxAreaOfIsland(grid, visited, m, n, i-1, j) +
                _maxAreaOfIsland(grid, visited, m, n, i, j-1) +
                _maxAreaOfIsland(grid, visited, m, n, i+1, j) +
                _maxAreaOfIsland(grid, visited, m, n, i, j+1);
    }
}
