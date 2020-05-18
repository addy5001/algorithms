package questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BracketHostnameTest {

    final BracketHostname bracketHostname = new BracketHostname();

    @Test
    public void testParseHosts() {
        String test = "app(1,3,4,5,6,10).prod";
        List<String> expected = List.of("app1.prod", "app3.prod", "app4.prod", "app5.prod", "app6.prod", "app10.prod");
        Assert.assertEquals(expected, bracketHostname.parseHosts(test));
    }
}
