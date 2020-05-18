package ramesh.aadhavan.misc;

import org.junit.Assert;
import org.junit.Test;

public class SparseMatrixMultiplicationTest {

    SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication();

    @Test
    public void testMultiplyNaive() {
        int[][] A = {{1,0,0},{-1,0,3}};
        int[][] B = {{7,0,0}, {0,0,0}, {0,0,1}};
        int[][] C = sparseMatrixMultiplication._multiplyNaive(A,B);

        Assert.assertArrayEquals(new int[]{7,0,0}, C[0]);
        Assert.assertArrayEquals(new int[]{-7,0,3}, C[1]);
    }

    @Test
    public void testMultiplySparse() {
        int[][] A = {{1,0,0},{-1,0,3}};
        int[][] B = {{7,0,0}, {0,0,0}, {0,0,1}};
        int[][] C = sparseMatrixMultiplication._multiplySparse(A,B);

        Assert.assertArrayEquals(new int[]{7,0,0}, C[0]);
        Assert.assertArrayEquals(new int[]{-7,0,3}, C[1]);
    }
}
