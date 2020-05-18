package ramesh.aadhavan.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code DirectedGraph} represents
 *
 * @author aadhavan.ramesh
 * @since
 */
public class DirectedGraph {

    public static boolean detectCycle(int[][] graph, int n) {
        for(int v=0; v<n; v++) {
            if(_detectCycle(graph, n, v, new HashSet<>())) {
                return true;
            }
        }

        return false;
    }

    private static boolean _detectCycle(int[][] graph, int n,
                                        int vertex, Set<Integer> visited) {
        for(int i=0; i<n; i++) {
            if(graph[vertex][i] == 1) {
                if(visited.contains(i))
                    return true;
                else {
                    visited.add(vertex);
                    if(_detectCycle(graph, n, i, visited))
                        return true;
                    visited.remove(vertex);
                }
            }
        }

        return false;
    }

    public static boolean pathExists(int[][] graph, int v, int w, int size, Set<Integer> visited) {
        if(v == w)
            return true;

        for(int i=0; i<size; i++) {
            boolean pathExists = false;
            if(graph[v][i] != 0 && !visited.contains(i)) {
                visited.add(i);
                pathExists = pathExists(graph, i, w, size, visited);
                visited.remove(i);
            }

            if(pathExists)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] g = {
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0}
        };

        System.out.println(detectCycle(g, 4));
    }
}
