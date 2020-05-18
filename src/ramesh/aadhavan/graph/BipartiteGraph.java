package ramesh.aadhavan.graph;

import java.util.*;

public class BipartiteGraph {
    public boolean isBipartite(int[][] graph) {
        return _isBipartite(graph);
    }

    private boolean _isBipartite(int[][] graph) {
        int[] group = new int[graph.length];
        Arrays.fill(group, -1);

        for(int i=0; i<graph.length; i++) {
            if(group[i] == -1) {
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(i);
                group[i] = 0;

                while(!stack.isEmpty()) {
                    int node = stack.pop();

                    for(int neighbor : graph[node]) {
                        if(group[neighbor] == -1) {
                            group[neighbor] = group[node] ^ 1;
                            stack.push(neighbor);
                        }
                        else {
                            if(group[node] == group[neighbor])
                                return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
