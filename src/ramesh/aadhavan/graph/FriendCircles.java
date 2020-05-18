package ramesh.aadhavan.graph;

import java.util.HashSet;
import java.util.Set;

public class FriendCircles {
    public int findCircleNum(int[][] M) {
        return _findCircles(M);
    }

    private int _findCircles(int[][] M) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();

        int circles = 0;

        for(int i=0; i<M.length; i++) {
            for(int j=0; j<M.length; j++) {
                if(M[i][j] == 0 || visited.contains(i))
                    continue;

                circles++;
                _dfs(M, visited, visiting, j);
            }
        }

        return circles;
    }

    private void _dfs(int[][] M, Set<Integer> visited, Set<Integer> visiting, int node) {
        if(visited.contains(node))
            return;

        visiting.add(node);

        for(int j=0; j<M[node].length; j++) {
            if(j == node || M[node][j] == 0 || visiting.contains(j))
                continue;

            _dfs(M, visited, visiting, j);
        }

        visiting.remove(node);
        visited.add(node);
    }
}
