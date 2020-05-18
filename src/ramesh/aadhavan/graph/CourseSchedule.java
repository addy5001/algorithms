package ramesh.aadhavan.graph;

import java.util.*;
import java.util.stream.IntStream;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return _canFinishTopologicalSort(numCourses, prerequisites);
    }

    private boolean _canFinishTopologicalSort(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        IntStream.range(0, numCourses).forEach(course -> graph.put(course, new ArrayList<>()));
        for(int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];

            graph.computeIfAbsent(course, key -> new ArrayList<>()).add(prereq);
        }

        int[] indegrees = new int[numCourses];
        for(List<Integer> values : graph.values()) {
            for(int val : values) {
                indegrees[val] += 1;
            }
        }

        Queue<Integer> zeroDegrees = new LinkedList<>();
        int totalNodesRemoved = 0;

        for(int i = 0; i<indegrees.length; i++) {
            if(indegrees[i] == 0) {
                zeroDegrees.add(i);
                totalNodesRemoved++;
            }
        }

        while(!zeroDegrees.isEmpty()) {
            int zeroDegreeNode = zeroDegrees.poll();
            List<Integer> neighbors = graph.get(zeroDegreeNode);
            for(int neighbor : neighbors) {
                indegrees[neighbor] -= 1;
                if(indegrees[neighbor] == 0) {
                    zeroDegrees.offer(neighbor);
                    totalNodesRemoved++;
                }
            }
        }

        return totalNodesRemoved == numCourses;
    }

    private boolean _canFinishDfs(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];

            graph.computeIfAbsent(course, key -> new ArrayList<>()).add(prereq);
        }

        Set<Integer> visited = new HashSet<>();
        for (int i=0; i<numCourses; i++) {
            if(visited.contains(i))
                continue;

            if(!_dfs(i, graph, visited, new HashSet<>()))
                return false;
        }

        return true;
    }

    private boolean _dfs(int course, Map<Integer, List<Integer>> directedGraph,
                         Set<Integer> visited, Set<Integer> visiting) {
        if(visited.contains(course))
            return true;

        if(visiting.contains(course))
            return false;

        visiting.add(course);
        for(int prereq : directedGraph.getOrDefault(course, Collections.emptyList())) {
            if(!_dfs(prereq, directedGraph, visited, visiting))
                return false;
        }

        visiting.remove(course);
        visited.add(course);

        return true;
    }
}
