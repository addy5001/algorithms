package ramesh.aadhavan.misc;

import org.junit.Test;

public class RestoreIpAddressesTest {

    final RestoreIpAddresses restoreIpAddresses = new RestoreIpAddresses();

    @Test
    public void testRestoreIpAddresses() {
        String test = "25525511135";
        System.out.println(restoreIpAddresses.restoreIpAddresses(test).toString());
    }
}
