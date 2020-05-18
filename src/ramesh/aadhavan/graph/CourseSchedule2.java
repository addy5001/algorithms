package ramesh.aadhavan.graph;

import java.util.*;
import java.util.stream.IntStream;

public class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return _findOrderTopologicalSort(numCourses, prerequisites);
    }

    private int[] _findOrderTopologicalSort(int numCourses, int[][] prerequisites) {
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
        int[] order = new int[numCourses];
        int orderIdx = numCourses;

        for(int i = 0; i<indegrees.length; i++) {
            if(indegrees[i] == 0) {
                zeroDegrees.add(i);
                order[--orderIdx] = i;
            }
        }

        while(!zeroDegrees.isEmpty()) {
            int zeroDegreeNode = zeroDegrees.poll();
            List<Integer> neighbors = graph.get(zeroDegreeNode);
            for(int neighbor : neighbors) {
                indegrees[neighbor] -= 1;
                if(indegrees[neighbor] == 0) {
                    zeroDegrees.offer(neighbor);
                    order[--orderIdx] = neighbor;
                }
            }
        }

        return orderIdx == 0 ? order : new int[0];
    }
}
