package questions;

import org.junit.Test;

public class RandomPickIndexTest {

    @Test
    public void testPick() {
        int[] test = new int[] {1,2,3,3,3};
        final RandomPickIndex randomPickIndex = new RandomPickIndex(test);

        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(3));
    }
}
