package ramesh.aadhavan.graph;

import java.util.*;

public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        return _findItineraryDfs(tickets, "JFK");
    }

    public List<String> _findItineraryDfs(List<List<String>> tickets, String source) {
        Map<String, Queue<String>> graph = new HashMap<>();
        for(List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
            graph.putIfAbsent(to, new PriorityQueue<>());
        }

        LinkedList<String> order = new LinkedList<>();
        _dfs(source, order, graph);
        return order;
    }

    private void _dfs(String source, LinkedList<String> order, Map<String, Queue<String>> graph) {
        while(!graph.get(source).isEmpty()) {
            String next = graph.get(source).poll();
            _dfs(next, order, graph);
        }

        order.addFirst(source);
    }
}
