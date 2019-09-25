package ramesh.aadhavan.graph;

import java.util.*;

public class Graph {
    static class Node {
        int id;
        Node next;

        Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private List<Node> nodeList = new ArrayList<>();

    public Node addNode(int id) {
        Optional<Node> node = nodeList.stream().filter(node1 -> node1.id == id).findFirst();
        if(node.isPresent())
            return node.get();
        else {
            Node newNode = new Node(id);
            nodeList.add(newNode);
            return newNode;
        }
    }

    public void addEdge(int id1, int id2) {
        boolean e1Found = false;
        boolean e2Found = false;
        Node e1 = null;
        Node e2 = null;

        for(Node node : nodeList) {
            if(node.id == id1) {
                e1Found = true;
                e1 = node;
            }

            if(node.id == id2) {
                e2Found = true;
                e2 = node;
            }
        }

        if(!e2Found) {
            e2 = addNode(id2);
        }

        if(!e1Found) {
            e1 = addNode(id1);
        }

        Node temp = e2;
        e2.next = e1.next;
        e1.next = temp;
    }

    public Node getNode(int id) {
        return nodeList.stream().filter(node -> node.id == id).findAny().orElse(null);
    }

    private Map<Node, Integer> calculateInDegrees() {
        Map<Node, Integer> inDegrees = new HashMap<>();

        for(Node node : nodeList) {
            Node temp = node.next;

            while(temp!=null) {
                if(inDegrees.containsKey(temp)) {
                    inDegrees.put(temp, inDegrees.get(temp) + 1);
                }
                else {
                    inDegrees.put(temp, 1);
                }
                temp = temp.next;
            }
        }
        return inDegrees;
    }

    private List<Node> calculateZeroDegreeNodes(Map<Node, Integer> inDegrees) {
        final List<Node> zeroDegreesList = new ArrayList<>();
        nodeList.forEach(node -> {
            if(!inDegrees.containsKey(node))
                zeroDegreesList.add(node);
        });
        return zeroDegreesList;
    }

    public void topologicalSort() {
        Map<Node, Integer> inDegreesMap = calculateInDegrees();
        List<Node> zeroDegreesList = calculateZeroDegreeNodes(inDegreesMap);

        while(!zeroDegreesList.isEmpty()) {
            Node temp = zeroDegreesList.remove(0);
            System.out.print(temp.id+" ");

            temp = temp.next;
            while(temp!=null) {
                Node neighbour = temp;
                if(inDegreesMap.containsKey(neighbour)) {
                    int inDegrees = inDegreesMap.get(neighbour) - 1;
                    if(inDegrees==0) {
                        zeroDegreesList.add(neighbour);
                        inDegreesMap.remove(neighbour);
                    }
                    else
                        inDegreesMap.put(neighbour, inDegrees);
                }
                temp = temp.next;
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(3,5);
        graph.addEdge(2,6);
        graph.addEdge(2,7);

        graph.topologicalSort();
    }
}
