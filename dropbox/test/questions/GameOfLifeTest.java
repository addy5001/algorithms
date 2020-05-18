package questions;

import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeTest {
    final GameOfLife gameOfLife = new GameOfLife();

    @Test
    public void testGameOfLife() {
        int[][] test = {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };

        int[][] expected = {
                {0,0,0},
                {1,0,1},
                {0,1,1},
                {0,1,0}
        };

        gameOfLife.gameOfLife(test);

        for(int i=0; i<test.length; i++) {
            Assert.assertArrayEquals(expected[i], test[i]);
        }
    }
}
