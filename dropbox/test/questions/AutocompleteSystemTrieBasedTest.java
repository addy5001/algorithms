package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AutocompleteSystemTrieBasedTest {

    @Test
    public void testLookup() {
        final AutocompleteSystemTrieBased autocomplete = new AutocompleteSystemTrieBased(
                new String[] {"aadhavan", "madhu", "ashok", "adithya", "arun"},
                new int[] {5, 10, 20, 25, 30});

        List<String> results = autocomplete.input('a');
        Assert.assertEquals(3, results.size());
        Assert.assertEquals("arun", results.get(0));
        Assert.assertEquals("adithya", results.get(1));
        Assert.assertEquals("ashok", results.get(2));
    }

    @Test
    public void testLookup_2() {
        final AutocompleteSystemTrieBased autocomplete = new AutocompleteSystemTrieBased(
                new String[] {"aadhavan", "aadhaban", "aadhazan"},
                new int[] {15, 15, 15});

        List<String> results = autocomplete.input('a');
        Assert.assertEquals(3, results.size());
        Assert.assertEquals("aadhaban", results.get(0));
        Assert.assertEquals("aadhavan", results.get(1));
        Assert.assertEquals("aadhazan", results.get(2));
    }
}
