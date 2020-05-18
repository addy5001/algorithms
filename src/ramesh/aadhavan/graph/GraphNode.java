package ramesh.aadhavan.graph;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    private Object val;
    private List<GraphNode<T>> neighbors;

    public GraphNode(T val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }

    public T getVal() {
        return (T) val;
    }

    public List<GraphNode<T>> getNeighbors() {
        return neighbors;
    }

    @VisibleForTesting
    void setNeighbors(List<GraphNode<T>> neighbors) {
        this.neighbors = neighbors;
    }
}
