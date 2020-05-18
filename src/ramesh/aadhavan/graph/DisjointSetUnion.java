package ramesh.aadhavan.graph;

import java.util.stream.IntStream;

public class DisjointSetUnion {
    private final int[] parent;
    private final int[] rank;

    public DisjointSetUnion(int size) {
        parent = new int[size];
        rank = new int[size];
        IntStream.range(0, size).forEach(i -> parent[i] = i);
    }

    public int[] getParent() {
        return parent;
    }

    public int find(int x) {
        if(parent[x] != x)
            parent[x] = find(parent[x]);

        return parent[x];
    }

    public boolean union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX == parentY)
            return false;
        else if(rank[parentX] < rank[parentY])
            parent[parentX] = parentY;
        else if(rank[parentX] > rank[parentY])
            parent[parentY] = parentX;
        else {
            parent[parentY] = parentX;
            rank[parentX]++;
        }

        return true;
    }
}
