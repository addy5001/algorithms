package questions;

import org.junit.Assert;
import org.junit.Test;

public class StringSearchTest {

    @Test
    public void testSearch() {
        String pattern = "adh";
        String txt = "aadhavanabagt";

        Assert.assertEquals(1, StringSearch.searchRollingHash(pattern, txt));
    }
}
