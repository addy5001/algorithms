package ramesh.aadhavan.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RedundantConnection {

    Set<Integer> visited = new HashSet<>();
    final int MAX_SIZE = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        return dsuFindRedundantConnection(edges);
    }

    private int[] dsuFindRedundantConnection(int[][] edges) {
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(MAX_SIZE + 1);
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if(!disjointSetUnion.union(u, v))
                return edge;
        }

        return null;
    }

    private int[] dfsFindRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_SIZE + 1];
        for(int i=0; i<=MAX_SIZE; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            visited.clear();
            int u = edge[0];
            int v = edge[1];

            if(!graph[u].isEmpty() && !graph[v].isEmpty() && _dfs(graph, u, v)) {
                return edge;
            }

            graph[u].add(v);
            graph[v].add(u);
        }

        return null;
    }

    private boolean _dfs(ArrayList<Integer>[] graph, int u, int v) {
        if(!visited.contains(u)) {
            visited.add(u);

            if(u == v)
                return true;

            for(int neighbor : graph[u]) {
                if(_dfs(graph, neighbor, v))
                    return true;
            }
        }

        return false;
    }
}
