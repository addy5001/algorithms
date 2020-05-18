package ramesh.aadhavan.graph;

import com.google.common.annotations.VisibleForTesting;

import java.util.*;

public class ShortestWordDistance {

    Map<String, List<String>> graph;

    public ShortestWordDistance(Map<String, List<String>> graph) {
        this.graph = graph;
    }

    public ShortestWordDistance(String[] words) {
        this(_createGraph(words));
    }

    private class AnnotatedNode {
        String node;
        int level;

        public AnnotatedNode(String node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public int shortestDistance(String word1, String word2) {
        if(graph.isEmpty())
            return -1;

        Queue<AnnotatedNode> bfsQ = new LinkedList<>();
        bfsQ.add(new AnnotatedNode(word1, 1));
        Set<String> visited = new HashSet<>();

        while(!bfsQ.isEmpty()) {
            AnnotatedNode node = bfsQ.poll();
            if(visited.contains(node.node))
                continue;

            visited.add(node.node);
            for(String neighbor : graph.getOrDefault(node.node, Collections.emptyList())) {
                if(word2.equals(neighbor))
                    return node.level;
                else {
                    bfsQ.add(new AnnotatedNode(neighbor, node.level+1));
                }
            }
        }

        return -1;
    }

    @VisibleForTesting
    static Map<String, List<String>> _createGraph(String[] words) {
        Map<String, List<String>> graph = new HashMap<>();

        for(int i=0; i<words.length; i++) {
            if(i+1 < words.length) {
                final String wordToAdd = words[i+1];
                graph.compute(words[i], (key, oldVal) -> {
                    if(oldVal == null) {
                        List<String> neighbors = new ArrayList<>();
                        neighbors.add(wordToAdd);
                        return neighbors;
                    }
                    else {
                        if(!oldVal.contains(wordToAdd))
                            oldVal.add(wordToAdd);
                        return oldVal;
                    }
                });
            }

            if(i-1 >= 0) {
                final String wordToAdd = words[i-1];
                graph.compute(words[i], (key, oldVal) -> {
                   if(oldVal == null) {
                       List<String> neighbors = new ArrayList<>();
                       neighbors.add(wordToAdd);
                       return neighbors;
                   }
                   else {
                       if(!oldVal.contains(wordToAdd))
                           oldVal.add(wordToAdd);
                       return oldVal;
                   }
                });
            }
        }

        return graph;
    }

    public int shortestDistance(String[] words, String word1, String word2) {

        int m = -1;
        int n = -1;
        int minDistance = Integer.MAX_VALUE;

        for(int i=0; i<words.length; i++) {
            if(word1.equals(words[i])) {
                m = i;
                if(n != -1) {
                    minDistance = Math.min(minDistance, m-n);
                }
            }
            else if(word2.equals(words[i])) {
                n = i;
                if(m != -1) {
                    minDistance = Math.min(minDistance, n-m);
                }
            }
        }

        return minDistance;
    }
}
