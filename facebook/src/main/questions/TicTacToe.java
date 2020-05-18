package questions;

public class TicTacToe {

    int[][] grid;

    //Optimized Approach
    int[] rows;
    int[] cols;
    int fwdDiagonal;
    int bwdDiagonal;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        grid = new int[n][n];

        //OptimizedApproach
        rows = new int[n];
        cols = new int[n];
        fwdDiagonal = 0;
        bwdDiagonal = 0;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int n = grid.length;
        grid[row][col] = player;

        boolean hasWon = true;

        // Check Row
        for(int i=0; i<n; i++) {
            if(grid[row][i] != player) {
                hasWon = false;
                break;
            }
        }

        if(hasWon)
            return player;

        hasWon = true;
        // Check column
        for(int i=0; i<n; i++) {
            if(grid[i][col] != player) {
                hasWon = false;
                break;
            }
        }

        if(hasWon)
            return player;

        hasWon = true;
        // Check forward diagonal
        for(int i=0; i<n; i++) {
            if(grid[i][i] != player) {
                hasWon = false;
                break;
            }
        }

        if(hasWon)
            return player;

        hasWon = true;
        // Check backwards diagonal
        for(int i=0; i<n; i++) {
            if(grid[i][n - i - 1] != player) {
                hasWon = false;
                break;
            }
        }

        if(hasWon)
            return player;

        return 0;
    }

    private int _optimizedMove(int row, int col, int player) {
        int n = grid.length;
        int aux = (player == 1) ? 1 : -1;

        rows[row] += aux;
        cols[col] += aux;

        if(row == col)
            fwdDiagonal += aux;

        if(row == n - col - 1)
            bwdDiagonal += aux;

        if(Math.abs(rows[row]) == n
        || Math.abs(cols[col]) == n
        || Math.abs(fwdDiagonal) == n
        || Math.abs(bwdDiagonal) == n)
            return player;

        return 0;
    }
}
