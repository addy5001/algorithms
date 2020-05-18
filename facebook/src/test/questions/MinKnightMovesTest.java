package questions;

import org.junit.Assert;
import org.junit.Test;

public class MinKnightMovesTest {

    final MinKnightMoves minKnightMoves = new MinKnightMoves();

    @Test
    public void testMinKnightMovesNaive_1() {
        Assert.assertEquals(1, minKnightMoves.minKnightMoves(2, 1));
    }

    @Test
    public void testMinKnightMovesNaive_2() {
        Assert.assertEquals(4, minKnightMoves.minKnightMoves(5, 5));
    }
}
