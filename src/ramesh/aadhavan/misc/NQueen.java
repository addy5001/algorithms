package ramesh.aadhavan.misc;

public class NQueen {

    public static void findNQueens(int n) {
        int[][] matrix = new int[n][n];
        _nQueens(matrix, 0, n);
    }

    private static void _nQueens(int[][] matrix, int row, int n) {
        if(row == n) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(matrix[i][j] == 1)
                        System.out.print("Q ");
                    else
                        System.out.print(". ");
                }
                System.out.println();
            }

            return;
        }

        for(int j=0; j<n; j++) {
            if(_checkDirections(matrix, row, j, n)) {
                matrix[row][j] = 1;
                _nQueens(matrix, row+1, n);
                matrix[row][j] = 0;
            }
        }
    }

    private static boolean _checkDirections(int[][] matrix, int x, int y, int n) {
        for(int i=x; i>=0; i--) {
            if (matrix[i][y] == 1) {
                return false;
            }
        }

        for(int i=x, j=y; (i-1) >= 0 && (j-1) >= 0; i--,j--) {
            if(matrix[i-1][j-1] == 1) {
                return false;
            }
        }

        for(int i=x, j=y; (i-1) >= 0 && (j+1) < n; i--, j++) {
            if(matrix[i-1][j+1] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        findNQueens(8);
    }
}
