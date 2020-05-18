package ramesh.aadhavan.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DjikstrasShortestPaths {
    public static int[] shortestPaths(int[][] graph, int vertex) {
        int[] distanceVector = new int[graph.length];
        Arrays.fill(distanceVector, Integer.MAX_VALUE);
        distanceVector[vertex] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for(int w=0; w<graph.length; w++) {
                int d = graph[v][w];
                if(graph[v][w] != 0 && distanceVector[w] > distanceVector[v] + d) {
                    distanceVector[w] = distanceVector[v] + d;
                    queue.add(w);
                }
            }
        }

        return distanceVector;
    }
}
