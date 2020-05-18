package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class MatrixOperationsTest {

    @Test
    public void testRotate_clockwise() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] expected = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };

        MatrixOperations.rotate(matrix, 3, MatrixOperations.Direction.CLOCKWISE);
        _assert2DArray(expected, matrix, 3);

        Deque<Integer> deque = new ArrayDeque<>();
    }

    private void _assert2DArray(int[][] expected, int[][] actual, int n) {
        for(int i=0; i<n; i++) {
            Assert.assertArrayEquals("Row "+i+" is not equal", expected[i], actual[i]);
        }
    }

    @Test
    public void testDiagonalOrder() {
        int[][] matrix = {  {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}
        };

        Assert.assertArrayEquals(new Integer[]{1, 4, 2, 7, 5, 3, 8, 6, 9}, MatrixOperations.diagonalOrder(matrix).toArray(new Integer[]{0}));
    }
}
