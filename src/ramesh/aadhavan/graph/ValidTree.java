package ramesh.aadhavan.graph;

import java.util.*;

public class ValidTree {

    public boolean validTree(int n, int[][] edges) {
        return _validTreeDsu(n, edges);
    }

    private boolean _validTreeDsu(int n, int[][] edges) {
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(n);

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if(!disjointSetUnion.union(u, v))
                return false;
        }

        int rootCount = 0;
        int[] parent = disjointSetUnion.getParent();
        for(int i=0; i<parent.length; i++) {
            if(parent[i] == i)
                rootCount++;
        }

        return rootCount == 1;
    }

    private boolean _validTreeDfs(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Set<Integer> visited = new HashSet<>();

        if(!_dfs(0, -1, visited, graph))
            return false;

        return visited.size() == n;
    }

    private boolean _dfs(int node, int parent, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        if(visited.contains(node))
            return false;

        visited.add(node);

        for(int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if(neighbor != parent && !_dfs(neighbor, node, visited, graph))
                return false;
        }

        return true;
    }
}
