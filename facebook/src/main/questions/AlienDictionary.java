package questions;

import java.util.*;

public class AlienDictionary {

    /**
     * Build index with a hashmap char -> increasing order
     * iterate through words array and compare two pairs
     * @param words
     * @param order
     * @return
     */
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> index = _index(order);

        for(int i=1; i<words.length; i++) {
            if(!_compare(words[i-1], words[i], index))
                return false;
        }

        return true;
    }

    private boolean _compare(String s, String p, Map<Character, Integer> index) {
        int sLen = s.length();
        int pLen = p.length();

        int i = 0;
        while(i < sLen && i < pLen) {
            int sVal = index.get(s.charAt(i));
            int pVal = index.get(p.charAt(i));

            if(sVal < pVal)
                return true;
            else if(sVal > pVal)
                return false;
            else
                i++;
        }

        return pLen >= sLen;
    }

    private Map<Character, Integer> _index(String order) {
        Map<Character, Integer> index = new HashMap<>();
        for(int i = 0; i<order.length(); i++) {
            index.put(order.charAt(i), i);
        }

        return index;
    }

    /**
     * Create a dependency graph
     * for all characters that should come after it
     * c -> a,b,d
     *
     * Create an indegree map of characters that point to it
     *
     * Do a BFS topological sort to get the order of characters from zero degree to highest indegree
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        return _bfsTopologicalSort(words);
    }

    private String _bfsTopologicalSort(String[] words) {
        if(words == null || words.length == 0)
            return "";

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegreeMap = new HashMap<>();

        for(String word : words) {
            for(char c : word.toCharArray()) {
                indegreeMap.putIfAbsent(c, 0);
            }
        }

        for(int i=0; i<words.length-1; i++) {
            String first = words[i];
            String second = words[i+1];

            int length = Math.min(first.length(), second.length());
            for(int j=0; j<length; j++) {
                if(first.charAt(j) != second.charAt(j)) {
                    Set<Character> set = new HashSet<>();
                    if(graph.containsKey(first.charAt(j)))
                        set = graph.get(first.charAt(j));

                    if(!set.contains(second.charAt(j))) {
                        set.add(second.charAt(j));
                        graph.put(first.charAt(j), set);
                        indegreeMap.put(second.charAt(j), indegreeMap.get(second.charAt(j)) + 1);
                    }

                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        Queue<Character> bfsQ = new LinkedList<>();
        indegreeMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 0)
                .map(Map.Entry::getKey)
                .forEach(bfsQ::add);

        while(!bfsQ.isEmpty()) {
            Character c = bfsQ.poll();
            result.append(c);

            if(graph.containsKey(c)) {
                Set<Character> neighbors = graph.get(c);
                for(char n : neighbors) {
                    indegreeMap.put(n, indegreeMap.get(n) - 1);
                    if(indegreeMap.get(n) == 0)
                        bfsQ.add(n);
                }
            }
        }

        return result.length() == indegreeMap.size() ? result.toString() : "";
    }
}
