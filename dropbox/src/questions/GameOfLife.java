package questions;

import java.util.Arrays;

public class GameOfLife {

    public void gameOfLife(int[][] board) {
        _gameOfLifeSpaceEfficient(board);
    }

    private void _gameOfLifeSpaceEfficient(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int previouslyDead = 2;
        int previouslyAlive = -1;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                int neighborLiveCount = _getNeighborLiveCountSpaceEfficient(board, i, j, m, n);
                int liveOrDie = _liveOrDie(board[i][j], neighborLiveCount);
                if(liveOrDie != board[i][j]) {
                    if(liveOrDie == 0)
                        board[i][j] = previouslyAlive;
                    else
                        board[i][j] = previouslyDead;
                }
            }
        }

        //Reconstruct next state
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == previouslyAlive)
                    board[i][j] = 0;
                else if(board[i][j] == previouslyDead)
                    board[i][j] = 1;
            }
        }
    }

    private int _getNeighborLiveCountSpaceEfficient(int[][] board, int i, int j, int m, int n) {
        int[] xOffsets = {1, 0, -1};
        int[] yOffsets = {1, 0, -1};

        int totalLiveCount = 0;
        for(int x=0; x<xOffsets.length; x++) {
            for(int y=0; y<yOffsets.length; y++) {
                if(xOffsets[x] == 0 && yOffsets[y] == 0)
                    continue;

                int state = _getNeighbor(board, i+xOffsets[x], j+yOffsets[y], m, n);
                switch (state) {
                    case 1:
                    case -1: {
                        totalLiveCount+=1;
                        break;
                    }
                    case 0:
                    case 2:
                    default:
                        break;
                }
            }
        }

        return totalLiveCount;
    }

    private void _gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int[][] nextState = new int[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                int numNeighborLiveCount = _getNeighborLiveCount(board, i, j, m, n);
                nextState[i][j] = _liveOrDie(board[i][j], numNeighborLiveCount);
            }
        }

        for(int i=0; i<m; i++) {
            board[i] = Arrays.copyOf(nextState[i], n);
        }
    }

    private int _liveOrDie(int currentState, int numNeighborLiveCount) {
        if(currentState == 0) {
            if(numNeighborLiveCount == 3)
                return 1;
            else
                return 0;
        }
        else {
            if(numNeighborLiveCount < 2)
                return 0;
            else if(numNeighborLiveCount == 2 || numNeighborLiveCount == 3)
                return 1;
            else
                return 0;
        }
    }

    private int _getNeighborLiveCount(int[][] board, int i, int j, int m, int n) {
        int[] xOffsets = {1, 0, -1};
        int[] yOffsets = {1, 0, -1};

        int totalLiveCount = 0;
        for(int x=0; x<xOffsets.length; x++) {
            for(int y=0; y<yOffsets.length; y++) {
                if(xOffsets[x] == 0 && yOffsets[y] == 0)
                    continue;

                totalLiveCount += _getNeighbor(board, i+xOffsets[x], j+yOffsets[y], m, n);
            }
        }

        return totalLiveCount;
    }

    private int _getNeighbor(int[][] board, int i, int j, int m, int n) {
        if(i<0 || j<0 || i>=m || j>=n)
            return 0;

        return board[i][j];
    }
}
