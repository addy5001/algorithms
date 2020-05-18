package ramesh.aadhavan.misc;

import com.google.common.annotations.VisibleForTesting;

import java.util.*;

public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        return _multiplyNaive(A,B);
    }

    @VisibleForTesting
    int[][] _multiplySparse(int[][] A, int[][] B) {
        Map<Integer, List<int[]>> sparseMap = new HashMap<>();

        // Compute sparse list of A[][]
        for(int i=0; i<A.length; i++) {
            for(int j=0; j<A[0].length; j++) {
                if(A[i][j] != 0) {
                    final int colKey = j;
                    sparseMap.compute(i, (row, val) -> {
                        if(val == null) {
                            val = new ArrayList<>();
                        }
                        int[] entry = new int[2];
                        entry[0] = colKey;
                        entry[1] = A[row][colKey];
                        val.add(entry);
                        return val;
                    });
                }
            }
        }

        int[][] C = new int[A.length][B[0].length];

        for(int i=0; i<A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                int sum = 0;
                for (int[] item : sparseMap.getOrDefault(i, Collections.emptyList())) {
                    int col = item[0];
                    sum += item[1] * B[col][j];
                }
                C[i][j] = sum;
            }
        }

        return C;
    }

    @VisibleForTesting
    int[][] _multiplyNaive(int[][] A, int[][] B) {
        int row = A.length;
        int col = B[0].length;

        int[][] C = new int[row][col];

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                for(int k=0; k<A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
}
