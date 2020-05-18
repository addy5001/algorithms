package ramesh.aadhavan.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordLadderTest {

    final WordLadder wordLadder = new WordLadder();

    @Test
    public void testLadderLength() {
        List<String> test = new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));
        Assert.assertEquals(5, wordLadder.ladderLength("hit", "cog", test));
    }

    @Test
    public void testLadderLength_unreachable() {
        List<String> test = new ArrayList<>(List.of("hot","dot","dog","lot","log"));
        Assert.assertEquals(0, wordLadder.ladderLength("hit", "cog", test));
    }

    @Test
    public void testFindLadders() {
        List<String> test = new ArrayList<>(List.of("hot","dot","dog","lot","log","cog"));
        List<List<String>> allPaths = wordLadder.findLadders("hit", "cog", test);
        Assert.assertEquals(2, allPaths.size());
    }

    @Test
    public void testFindLadders_2() {
        List<String> test = new ArrayList<>(List.of("a", "b", "c"));
        List<List<String>> allPaths = wordLadder.findLadders("a", "c", test);
        Assert.assertEquals(1, allPaths.size());
    }

    @Test
    public void testFindLadders_unreachable() {
        List<String> test = new ArrayList<>(List.of("hot","dot","dog","lot","log"));
        List<List<String>> allPaths = wordLadder.findLadders("hit", "cog", test);
        Assert.assertEquals(0, allPaths.size());
    }
}
