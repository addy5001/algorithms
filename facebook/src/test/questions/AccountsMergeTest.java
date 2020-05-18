package questions;

import org.junit.Test;

import java.util.List;

public class AccountsMergeTest {
    final AccountsMerge accountsMerge = new AccountsMerge();

    @Test
    public void testAccountsMerge() {
        List<List<String>> test = List.of(
                List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
                List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com"),
                List.of("John","johnnybravo@mail.com"));

        accountsMerge.accountsMerge(test);
    }
}
