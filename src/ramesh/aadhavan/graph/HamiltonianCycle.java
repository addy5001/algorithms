package ramesh.aadhavan.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code HamiltonianCycle} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class HamiltonianCycle {
    public static boolean detectHamiltonianCycle(int[][] graph, int n) {
        return _detectHamiltonian(graph, 0, n, new HashMap<>());
    }

    private static boolean _detectHamiltonian(int[][] graph, int vertex, int n, Map<Integer, Integer> visited) {
        if(vertex == 0) {
            long visitedNodeCount = visited.entrySet().stream().filter(e -> e.getValue() == 1).count();
            if(visitedNodeCount == n)
                return true;
        }

        for(int i=0; i<n; i++) {
            if(graph[vertex][i] == 1 && !visited.containsKey(i)) {
                visited.put(vertex, visited.getOrDefault(vertex, 0)+1);
                if(_detectHamiltonian(graph, i, n, visited))
                    return true;
                visited.put(vertex, visited.get(vertex) - 1);
            }

            if(vertex == (n-1) && graph[vertex][0] == 1) {
                visited.put(vertex, visited.getOrDefault(vertex, 0)+1);
                if(_detectHamiltonian(graph, 0, n, visited))
                    return true;
                visited.put(vertex, visited.get(vertex) - 1);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] g = {
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0}
        };

        System.out.println(detectHamiltonianCycle(g, 4));
    }
}
