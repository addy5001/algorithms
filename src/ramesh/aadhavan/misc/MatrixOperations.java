package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixOperations {

    public enum Direction {
        CLOCKWISE,
        COUNTER_CLOCKWISE;
    }

    public static void rotate(int[][] matrix, int n, Direction direction) {
        switch (direction) {
            case CLOCKWISE: {
                int k=n-1;
                for(int i=0; i<n; i++) {
                    for(int j=0; j<n; j++) {
                        int tmp = matrix[i][j];
                        matrix[i][j] = matrix[j][i];
                        matrix[j][i] = tmp;
                    }
                    k--;
                }
                break;
            }
            case COUNTER_CLOCKWISE: {
                break;
            }
            default:
                throw new IllegalArgumentException("Provide a valid direction");
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        return _2dBinarySearch(matrix, m, n,
                0, m*n - 1,
                0, m*n - 1,
                target);
    }


    private boolean _linearSearch(int[][] matrix, int m, int n, int target) {
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == target)
                    return true;
            }
        }

        return false;
    }

    private boolean _binarySearch(int[][] matrix, int m, int n, int target) {
        for(int i=0; i<m; i++) {
            int begin = 0;
            int end = n-1;

            if(begin == end)
                return matrix[i][begin] == target;
            else {
                while(begin <= end) {
                    int mid = (begin+end)/2;

                    if(matrix[i][mid] == target)
                        return true;
                    else if(target < matrix[i][mid])
                        end = mid-1;
                    else
                        begin = mid+1;
                }
            }
        }

        return false;
    }

    private boolean _2dBinarySearch(int[][] matrix, int m, int n,
                                    int mBegin, int mEnd,
                                    int nBegin, int nEnd,
                                    int target) {
        if(mBegin > mEnd || nBegin > nEnd)
            return false;

        int mMid = (mBegin+mEnd)/2;
        int nMid = (nBegin+nEnd)/2;

        int midElement = matrix[mMid%m][nMid%n];

        if(target == midElement)
            return true;
        else if(target < midElement)
            return _2dBinarySearch(matrix, m, n, mBegin, mMid-1, nBegin, nMid-1, target);
        else
            return _2dBinarySearch(matrix, m, n, mMid+1, mEnd, nMid+1, nEnd, target);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return Collections.emptyList();

        int m = matrix.length;
        int n = matrix[0].length;

        int[] xOffsets = {0, 1, 0, -1};
        int[] yOffsets = {1, 0, -1, 0};

        boolean[][] seen = new boolean[m][n];
        List<Integer> results = new ArrayList<>();

        int x = 0;
        int y = 0;
        int offset = 0;

        for(int i=0; i<m*n; i++) {
            results.add(matrix[x][y]);
            seen[x][y] = true;
            int xr = x + xOffsets[offset];
            int yc = y + yOffsets[offset];

            if(xr >= 0 && xr < m && yc >= 0 && yc < n && !seen[xr][yc]) {
                x = xr;
                y = yc;
            }
            else {
                offset = (offset + 1) % 4;

                x += xOffsets[offset];
                y += yOffsets[offset];
            }
        }

        return results;
    }

    public static List<Integer> diagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int numDiagonals = rows + cols - 1;
        List<Integer> results = new ArrayList<>();
        int r = 0, c = 0;
        boolean flipper = false;
        for(int i=0; i<numDiagonals; i++) {
            if(r < rows) {
                int j=r, k=c;
                while(j >= 0 && k < cols) {
                    results.add(matrix[j][k]);
                    j--;
                    k++;
                }
                r++;
            }
            else {
                if(c == 0) {
                    c++;
                }
                int j=r-1, k=c;
                while(j >= 0 && k < cols) {
                    results.add(matrix[j][k]);
                    j--;
                    k++;
                }
                c++;
            }
        }

        return results;
    }

    /**
     * 1. Check if first row contains zero
     * 2. Check if first column contains zero
     * 3. Starting from 1,1 if M[i][j] contains zero move it to first row and first col M[0][j] M[i][0]
     * 4. Starting from [0,1] -> [0,n] for every column containing zero make the column zero
     * 5. Starting from [1,0] -> [m,0] for every row containing zero make the row zero
     * 6. If firstRowContainsZero, make first row zero
     * 7. If firstColContainsZero, make first column zero
     * @param M
     */
    public void setMatrixZeroes(int[][] M) {
        int m = M.length;
        int n = M[0].length;

        boolean firstRowContainsZero = false;
        boolean firstColContainsZero = false;

        for(int j=0; j<n; j++) {
            if(M[0][j] == 0) {
                firstRowContainsZero = true;
                break;
            }
        }

        for(int i=0; i<m; i++) {
            if(M[i][0] == 0) {
                firstColContainsZero = true;
                break;
            }
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(M[i][j] == 0) {
                    M[0][j] = 0;
                    M[i][0] = 0;
                }
            }
        }

        for(int i=1; i<m; i++) {
            if(M[i][0] == 0) {
                for(int j=1; j<n; j++)
                    M[i][j] = 0;
            }
        }

        for(int j=1; j<n; j++) {
            if(M[0][j] == 0) {
                for(int i=1; i<m; i++)
                    M[i][j] = 0;
            }
        }

        if(firstRowContainsZero) {
            for(int j=0; j<n; j++)
                M[0][j] = 0;
        }

        if(firstColContainsZero) {
            for(int i=0; i<m; i++)
                M[i][0] = 0;
        }
    }

    public static boolean searchSortedMatrix(int matrix[][], int n, int element) {
        if (element < matrix[0][0] || element > matrix[n-1][n-1])
            return false;
        int r = 0; // row
        int c = n-1;// column
        while (r <= n-1 && c >= 0) {
            if (matrix[r][c] < element)
                r++;
            else if (matrix[r][c] > element)
                c--;
            else
                return true;
        }
        return false;
    }
}
