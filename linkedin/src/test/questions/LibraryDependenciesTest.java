package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class LibraryDependenciesTest {

    @Test
    public void testGetDependencies() throws LibraryDependencies.CircularDependencyException {
        Map<String, List<String>> testDeps = Map.of(
                "A", List.of("B", "C", "D"),
                "B", List.of("E", "F", "G"),
                "C", List.of("H", "I", "J", "K"),
                "D", List.of("L"),
                "J", List.of("M", "N", "O", "P"));

        final LibraryDependencies libraryDependencies = new LibraryDependencies(testDeps);
        List<String> deps = libraryDependencies.getDependencies("A");
        System.out.println(deps.toString());
    }

    @Test
    public void testGetDependencies_2() throws LibraryDependencies.CircularDependencyException {
        Map<String, List<String>> testDeps = Map.of(
                "A", List.of("B", "C", "D"),
                "B", List.of("E", "F", "G"),
                "C", List.of("H", "I", "J", "K"),
                "D", List.of("L", "J"),
                "J", List.of("M", "N", "O", "P"));

        final LibraryDependencies libraryDependencies = new LibraryDependencies(testDeps);
        List<String> deps = libraryDependencies.getDependencies("A");
        System.out.println(deps.toString());
    }

    @Test
    public void testGetDependencies_3() throws LibraryDependencies.CircularDependencyException {
        Map<String, List<String>> testDeps = Map.of(
                "A", List.of("B", "C", "D"),
                "B", List.of("E", "F", "G"),
                "C", List.of("H", "I", "J", "K"),
                "D", List.of("L", "J"),
                "J", List.of("M", "N", "O", "B"));

        final LibraryDependencies libraryDependencies = new LibraryDependencies(testDeps);
        List<String> deps = libraryDependencies.getDependencies("A");
        System.out.println(deps.toString());
    }

    @Test
    public void testGetDependencies_cycle() throws LibraryDependencies.CircularDependencyException {
        Map<String, List<String>> testDeps = Map.of(
                "A", List.of("B", "C", "D"),
                "B", List.of("E", "F", "G"),
                "C", List.of("H", "I", "J", "K"),
                "D", List.of("L", "J", "A"),
                "J", List.of("M", "N", "O", "B"));

        final LibraryDependencies libraryDependencies = new LibraryDependencies(testDeps);
        List<String> deps = libraryDependencies.getDependencies("A");
        System.out.println(deps.toString());
    }
}
