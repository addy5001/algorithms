package ramesh.aadhavan.dynamic;

public class MinimumCostMatrix {
    public static int minCostPathOfMatrix(int[][] costMatrix, int m, int n) {
        return _dynamicCost(costMatrix, m, n);
    }

    private static int _recursiveCost(int[][] costMatrix, int m, int n) {
        if(m < 0 || n < 0)
            return Integer.MAX_VALUE;

        if(m == 0 && n == 0)
            return costMatrix[m][n];

        return costMatrix[m][n] + Math.min(Math.min(_recursiveCost(costMatrix, m-1, n), _recursiveCost(costMatrix, m, n-1)), _recursiveCost(costMatrix, m-1, n-1));
    }

    private static int _dynamicCost(int[][] matrix, int m, int n) {
        int[][] cost = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i == 0 && j == 0)
                    cost[i][j] = matrix[i][j];
                else if(i-1 < 0)
                    cost[i][j] = matrix[i][j] + cost[i][j-1];
                else if(j-1 < 0)
                    cost[i][j] = matrix[i][j] + cost[i-1][j];
                else
                    cost[i][j] = Math.min(Math.min((matrix[i][j] + cost[i-1][j]), (matrix[i][j] + cost[i][j-1])), (matrix[i][j] + cost[i-1][j-1]));
            }
        }

        return cost[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][3];
        matrix[0][0] = 3;
        matrix[0][1] = 2;
        matrix[0][2] = 8;
        matrix[1][0] = 1;
        matrix[1][1] = 9;
        matrix[1][2] = 7;
        matrix[2][0] = 0;
        matrix[2][1] = 5;
        matrix[2][2] = 2;
        matrix[3][0] = 6;
        matrix[3][1] = 4;
        matrix[3][2] = 3;

        System.out.println(minCostPathOfMatrix(matrix, 4, 3));
    }
}
