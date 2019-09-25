package ramesh.aadhavan.dynamic;

public class MaxSubSquareOf1s {

    public static int maxSubSquare(int[][] matrix, int m, int n) {
        int[][] subSquare = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 && j == 0)
                    subSquare[i][j] = matrix[i][j];
                else if(i-1 < 0)
                    subSquare[i][j] = Math.max(subSquare[i][j-1], matrix[i][j]);
                else if(j-1 < 0)
                    subSquare[i][j] = Math.max(subSquare[i-1][j], matrix[i][j]);
                else {
                    if(matrix[i-1][j] == 1 && matrix[i][j-1] == 1
                            && matrix[i-1][j-1] == 1 && matrix[i][j] == 1)
                        subSquare[i][j] = subSquare[i-1][j-1] + 1;
                    else
                        subSquare[i][j] = Math.max(
                                Math.max(subSquare[i-1][j], subSquare[i][j-1]),
                                subSquare[i-1][j-1]
                        );
                }
            }
        }

        return subSquare[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        matrix[0][0] = 1;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 1;
        matrix[1][0] = 1;
        matrix[1][1] = 1;
        matrix[1][2] = 1;
        matrix[1][3] = 1;
        matrix[2][0] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 1;
        matrix[2][3] = 1;
        matrix[3][0] = 1;
        matrix[3][1] = 1;
        matrix[3][2] = 1;
        matrix[3][3] = 1;

        System.out.println(maxSubSquare(matrix, 4, 4));
    }
}
