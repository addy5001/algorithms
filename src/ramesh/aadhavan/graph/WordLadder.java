package ramesh.aadhavan.graph;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();

        wordList.add(beginWord);
        for(int i=0; i<wordList.size(); i++) {
            String source = wordList.get(i);
            for(int j=i+1; j<wordList.size(); j++) {
                String destination = wordList.get(j);
                if(_isOneEdit(source, destination)) {
                    graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
                    graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
                }
            }
        }

        return _bfs(beginWord, endWord, graph);
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();

        boolean found = false;
        for(String word : wordList) {
            if(word.equals(beginWord)) {
                found = true;
                break;
            }
        }

        if(!found)
            wordList.add(beginWord);

        for(int i=0; i<wordList.size(); i++) {
            String source = wordList.get(i);
            for(int j=i+1; j<wordList.size(); j++) {
                String destination = wordList.get(j);
                if(_isOneEdit(source, destination)) {
                    graph.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
                    graph.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
                }
            }
        }

        int totalSteps = _bfs(beginWord, endWord, graph);
        List<List<String>> allPaths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        _dfsBacktrack(beginWord, endWord, 1, totalSteps, graph, new HashSet<>(), path, allPaths);
        return allPaths;
    }

    private boolean _isOneEdit(String source, String destination) {
        int n = source.length();
        for(int i=0; i<n; i++) {
            if(source.charAt(i) != destination.charAt(i))
                return (i == source.length()-1) || source.substring(i+1).equals(destination.substring(i+1));
        }

        return false;
    }

    private int _bfs(String source, String destination, Map<String, List<String>> graph) {
        Queue<String> bfsQueue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        bfsQueue.add(source);
        visited.add(source);
        int ladderLength = 1;

        while(!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();

            for(int i=0; i<size; i++) {
                String nextWord = bfsQueue.poll();

                if (nextWord.equals(destination))
                    return ladderLength;

                for (String connectingWord : graph.getOrDefault(nextWord, Collections.emptyList())) {
                    if (!visited.contains(connectingWord)) {
                        visited.add(connectingWord);
                        bfsQueue.add(connectingWord);
                    }
                }
            }

            ladderLength++;
        }

        return 0;
    }

    private void _dfsBacktrack(String source,
                               String destination,
                               int steps,
                               int totalSteps,
                               Map<String, List<String>> graph,
                               Set<String> visiting,
                               List<String> path,
                               List<List<String>> allPaths) {
        if(steps == totalSteps) {
            if(source.equals(destination)) {
                allPaths.add(new ArrayList<>(path));
            }

            return;
        }

        for(String connectingNode : graph.getOrDefault(source, Collections.emptyList())) {
            if(!visiting.contains(connectingNode)) {
                visiting.add(connectingNode);
                path.add(connectingNode);
                _dfsBacktrack(connectingNode, destination, steps+1, totalSteps, graph, visiting, path, allPaths);
                path.remove(path.size() - 1);
                visiting.remove(connectingNode);
            }
        }
    }
}
