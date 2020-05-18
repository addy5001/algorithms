package questions;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class IPAddressSearchTest {

    final IPAddressSearch ipAddressSearch = new IPAddressSearch();

    @Test
    public void testParseIpAddresses_valid() throws IOException {
        List<String> allMatches = ipAddressSearch.parseIpAddresses(new File("src/test/resources/logfile.txt"));
        Assert.assertEquals(5, allMatches.size());
        Assert.assertEquals(List.of("192.168.1.1", "127.0.0.1", "0.0.0.0", "255.255.255.255", "1.2.3.4"), allMatches);
    }

    @Test
    public void testParseIpAddresses_valid_2() throws IOException {
        List<String> allMatches = ipAddressSearch.parseIpAddresses(new File("src/test/resources/logfileMultiple.txt"));
        Assert.assertEquals(6, allMatches.size());
        Assert.assertEquals(List.of("192.168.1.1", "127.0.0.1", "0.0.0.0", "255.255.255.255", "1.2.3.4", "255.124.6.7"), allMatches);
    }
}
