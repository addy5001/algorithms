package questions;

import java.util.*;

public class LibraryDependencies {

    public class CircularDependencyException extends Exception {
        public CircularDependencyException(String message) {
            super(message);
        }
    }

    Map<String, List<String>> dependencyGraph;

    public LibraryDependencies(Map<String, List<String>> dependencyGraph) {
        this.dependencyGraph = dependencyGraph;
    }

    public List<String> getDependencies(String library) throws CircularDependencyException {
        Set<String> dependencies = new HashSet<>();
        _dfs(library, new HashSet<>(), new HashSet<>(), dependencies);
        return new ArrayList<>(dependencies);
    }

    private void _dfs(String node, Set<String> visited, Set<String> visiting, Set<String> dependencies)
            throws CircularDependencyException {
        if(visited.contains(node))
            return;

        visiting.add(node);
        List<String> neighbors = dependencyGraph.getOrDefault(node, Collections.emptyList());
        for(String neighbor : neighbors) {
            if(visiting.contains(neighbor))
                throw new CircularDependencyException(
                        String.format("Detected cyclical dependency [%s]", visiting.toString()));

            visiting.add(neighbor);
            dependencies.add(neighbor);
            _dfs(neighbor, visited, visiting, dependencies);
        }

        visiting.remove(node);
        visited.add(node);
    }
}
