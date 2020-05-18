package ramesh.aadhavan.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordDictionaryTest {

    final static WordDictionary wordDictionary = new WordDictionary();

    @BeforeClass
    public static void init() {
        wordDictionary.addWord("aadhavan");
        wordDictionary.addWord("abc");
        wordDictionary.addWord("madhu");
        wordDictionary.addWord("z");
        wordDictionary.addWord("madmadhu");
    }

    @Test
    public void testSearch_exactWords() {
        Assert.assertTrue(wordDictionary.search("abc"));
        Assert.assertTrue(wordDictionary.search("aadhavan"));
        Assert.assertTrue(wordDictionary.search("madhu"));
        Assert.assertTrue(wordDictionary.search("z"));
        Assert.assertTrue(wordDictionary.search("madmadhu"));
    }

    @Test
    public void testSearch_regex() {
        Assert.assertTrue(wordDictionary.search("..."));
        Assert.assertFalse(wordDictionary.search("ab.."));
        Assert.assertTrue(wordDictionary.search(".adhavan"));
        Assert.assertFalse(wordDictionary.search("ma..u."));
        Assert.assertTrue(wordDictionary.search("z"));
        Assert.assertTrue(wordDictionary.search("m.dm.dh."));
    }
}
