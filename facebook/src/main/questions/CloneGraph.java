package questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

public class CloneGraph {
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;

        Map<Node, Node> visited = new HashMap<>();
        return _clone(node, visited);
    }

    private Node _clone(Node node, Map<Node, Node> visited) {
        if(visited.containsKey(node)) {
            return visited.get(node);
        }

        Node copy = new Node();
        copy.val = node.val;
        copy.neighbors = new ArrayList<>(node.neighbors.size());
        visited.put(node, copy);

        for(int i=0; i<node.neighbors.size(); i++) {
            copy.neighbors.add(i, _clone(node.neighbors.get(i), visited));
        }

        return copy;
    }
}
