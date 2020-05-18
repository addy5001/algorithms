package ramesh.aadhavan.graph;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DetectCycles<T> {

    private final Map<Integer, GraphNode<T>> adjacencyList;

    public DetectCycles(Map<Integer, GraphNode<T>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public boolean hasCycle() {
        final Set<GraphNode<T>> visiting = new HashSet<>();
        final Set<GraphNode<T>> visited = new HashSet<>();
        return adjacencyList.values()
                .stream().anyMatch(node -> hasCycle(node, visiting, visited));
    }

    private boolean hasCycle(GraphNode<T> node, Set<GraphNode<T>> visiting, Set<GraphNode<T>> visited) {
        if(node == null || visited.contains(node))
            return false;

        if(visiting.contains(node))
            return true;

        visiting.add(node);

        for(GraphNode<T> gNode : node.getNeighbors()) {
            if(hasCycle(gNode, visiting, visited))
                return true;
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }
}
