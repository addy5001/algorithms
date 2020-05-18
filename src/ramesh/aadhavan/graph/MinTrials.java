package ramesh.aadhavan.graph;

import ramesh.aadhavan.dynamic.EditDistance;

import java.util.*;
import java.util.stream.Stream;

public class MinTrials {

    private class Node {
        String val;
        Node next;

        public Node(String val) {
            this.val = val;
        }
    }

    private final Map<String, Node> graph = new HashMap<>();
    private final EditDistance editDistance = new EditDistance();

    void addNode(String val) {
        graph.put(val, new Node(val));
    }

    void addEdge(String val1, String val2) {
        graph.computeIfPresent(val1, (k, node1) -> {
            final Node node2 = new Node(val2);
            node2.next = node1.next;
            node1.next = node2;
            return node1;
        });
    }

    public void createGraph(String[] dictionary) {
        Stream.of(dictionary).forEach(this::addNode);

        for(int i=0; i<dictionary.length; i++) {
            for(int j=i+1; j<dictionary.length; j++) {
                if(editDistance.findMinEditDistance(dictionary[i], dictionary[j]) == 1) {
                    addEdge(dictionary[i], dictionary[j]);
                    addEdge(dictionary[j], dictionary[i]);
                }
            }
        }
    }

    private class QueueNode {
        Node node;
        int level;

        public QueueNode(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public int findMinTrials(String s1, String s2) {
        if(!graph.containsKey(s1) || !graph.containsKey(s2))
            throw new IllegalArgumentException("Invalid inputs");

        Queue<QueueNode> bfsQueue = new LinkedList<>();
        Node source = graph.get(s1);
        bfsQueue.add(new QueueNode(source, 1));
        Set<Node> visited = new HashSet<>();

        while(!bfsQueue.isEmpty()) {
            QueueNode qnode = bfsQueue.poll();
            Node node = qnode.node;
            if(visited.contains(node))
                continue;

            visited.add(node);
            Node connectingNode = node.next;

            while(connectingNode != null) {
                if(s2.equals(connectingNode.val)) {
                    return qnode.level;
                }

                bfsQueue.add(new QueueNode(graph.get(connectingNode.val), qnode.level+1));
                connectingNode = connectingNode.next;

            }
        }

        return -1;
    }
}
