package ramesh.aadhavan.graph;

import java.util.*;

public class GraphSearch {

    public static boolean breadFirstSearch(Map<String, List<String>> graph, String start, String element) {
        Queue<String> queue = new ArrayDeque<>();
        Set<String> searched = new HashSet<>();
        queue.add(start);

        while(Objects.nonNull(queue.peek())) {
            String node = queue.poll();
            if(element.equals(node)) {
                return true;
            }

            if(!searched.contains(node)) {
                searched.add(node);
                List<String> children = graph.getOrDefault(node, Collections.emptyList());
                children.forEach(queue::add);
            }
        }

        return false;
    }
}
