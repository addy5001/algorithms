package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ShortestWordDistanceTest {

    @Test
    public void testCreateGraph() {
        String[] words = {"word", "makes", "coding", "makes", "perfect"};
        Map<String, List<String>> map = ShortestWordDistance._createGraph(words);

        Assert.assertEquals(1, map.get("word").size());
        Assert.assertEquals(3, map.get("makes").size());
        Assert.assertEquals(1, map.get("coding").size());
        Assert.assertEquals(1, map.get("perfect").size());
    }

    @Test
    public void testShortestDistance() {
        String[] words = {"word", "makes", "coding", "makes", "perfect"};
        final ShortestWordDistance shortestWordDistance = new ShortestWordDistance(words);

        Assert.assertEquals(1, shortestWordDistance.shortestDistance("word", "makes"));
        Assert.assertEquals(1, shortestWordDistance.shortestDistance("coding", "makes"));
        Assert.assertEquals(1, shortestWordDistance.shortestDistance("makes", "perfect"));
        Assert.assertEquals(2, shortestWordDistance.shortestDistance("coding", "perfect"));
        Assert.assertEquals(2, shortestWordDistance.shortestDistance("word", "perfect"));
    }
}
