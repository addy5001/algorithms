package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DetectCyclesTest {

    @Test
    public void testHasCycle() {
        GraphNode<Integer> node1 = new GraphNode<>(1);
        GraphNode<Integer> node2 = new GraphNode<>(2);
        GraphNode<Integer> node3 = new GraphNode<>(3);
        GraphNode<Integer> node4 = new GraphNode<>(4);

        node1.setNeighbors(List.of(node2, node3));
        node2.setNeighbors(List.of(node4));
        node3.setNeighbors(List.of(node4));

        DetectCycles<Integer> detectCycles =
                new DetectCycles<>(Map.of(1, node1, 2, node2, 3, node3, 4, node4));

        Assert.assertFalse(detectCycles.hasCycle());
    }

    @Test
    public void testHasCycle_cycle() {
        GraphNode<Integer> node1 = new GraphNode<>(1);
        GraphNode<Integer> node2 = new GraphNode<>(2);
        GraphNode<Integer> node3 = new GraphNode<>(3);
        GraphNode<Integer> node4 = new GraphNode<>(4);

        node1.setNeighbors(List.of(node2));
        node2.setNeighbors(List.of(node3));
        node3.setNeighbors(List.of(node4));
        node4.setNeighbors(List.of(node1));

        DetectCycles<Integer> detectCycles =
                new DetectCycles<>(Map.of(1, node1, 2, node2, 3, node3, 4, node4));

        Assert.assertTrue(detectCycles.hasCycle());
    }
}
