package ramesh.aadhavan.graph;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        return _findMinHeightTreesBfs(n, edges);
    }

    private List<Integer> _findMinHeightTreesBfs(int n, int[][] edges) {
        if(n == 1)
            return Collections.singletonList(0);

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            graph.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        }

        List<Integer> leaves = graph.keySet().stream()
                .filter(key -> graph.get(key).size() == 1)
                .collect(Collectors.toList());

        int vertices = n;
        while(vertices > 2) {
            vertices -= leaves.size();

            List<Integer> newLeaves = new ArrayList<>();
            for(int leaf : leaves) {
                int connectingNode = graph.get(leaf).iterator().next();
                graph.get(connectingNode).remove(leaf);
                if(graph.get(connectingNode).size() == 1)
                    newLeaves.add(connectingNode);
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}
