package ramesh.aadhavan.graph;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {
    static class Edge {
        private final int u;
        private final int v;
        private final int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }
    }

    static class Graph {
        private final int V;
        private final List<Edge> edgeList;

        public Graph(int v, List<Edge> edgeList) {
            V = v;
            this.edgeList = edgeList;
        }

        public int getV() {
            return V;
        }

        public List<Edge> getEdgeList() {
            return edgeList;
        }
    }

    public static boolean getShortestPaths(Graph g, int source, int[] distances) {
        int V = g.getV();

        for(int i=0; i<V; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0; //distance of source to itself is always 0

        for(int i=0; i<V; i++) {
            for(Edge e : g.getEdgeList()) {
                int u = e.getU();
                int v = e.getV();
                int w = e.getW();
                if(distances[u] != Integer.MAX_VALUE && distances[v] > distances[u] + w) {
                    distances[v] = distances[u] + w;
                }
            }
        }

        for(Edge e : g.getEdgeList()) {
            int u = e.getU();
            int v = e.getV();
            int w = e.getW();

            if(distances[u] != Integer.MAX_VALUE && distances[v] > distances[u] + w) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1, 2));
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(1, 3, 4));
        edges.add(new Edge(1, 4, 10));
        edges.add(new Edge(2, 4, 6));
        edges.add(new Edge(3, 5, 2));
        edges.add(new Edge(4, 5, 9));
        edges.add(new Edge(4, 0, -7));
        Graph g = new Graph(6, edges);

        int[] distances = new int[6];
        boolean hasCycles = getShortestPaths(g, 0, distances);
        if(!hasCycles) {
            for(int i=0; i<6; i++) {
                System.out.println("Distance from edge 0 to "+i+" = "+distances[i]);
            }
        }
        else {
            System.out.print("Has negative cycle");
        }
    }
}
