package questions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinKnightMoves {

    private static final int[][] OFFSETS = {{1,2}, {1,-2}, {2,1}, {2,-1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

    public int minKnightMoves(int x, int y) {
        return _minKnightMovesBfs(Math.abs(x), Math.abs(y));
    }

    private int _minKnightMovesBfs(int endX, int endY) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[]{0,0});
        visited.add("0:0");

        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int[] currentPos = queue.poll();
                int currentX = currentPos[0];
                int currentY = currentPos[1];

                if(currentX == endX && currentY == endY)
                    return result;

                for(int j=0; j<OFFSETS.length; j++) {
                    int nextX = currentX + OFFSETS[j][0];
                    int nextY = currentY + OFFSETS[j][1];
                    String currentPosString = nextX+":"+nextY;
                    if(!visited.contains(currentPosString) && nextX >= -1 && nextY >= -1) {
                        visited.add(currentPosString);
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }

            result++;
        }

        return -1;
    }

    /**
     * int f( int x , int y )
     * {
     *     int delta = x - y;
     *
     *     if( y > delta )
     *         return 2 * ( ( y - delta ) / 3 ) + delta;
     *     else
     *         return delta - 2 * ( ( delta - y ) / 4 );
     * }
     * @param x
     * @param y
     * @return
     */
    public int minKnightMovesO1(int x, int y) {
        // Symmetry for axes
        x = Math.abs(x);
        y = Math.abs(y);
        // Symmetry for diagonal
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 1 && y == 0) {
            return 3;
        }
        if (x == 2 && y == 2) {
            return 4;
        }
        int delta = x - y;
        if (y > delta) {
            return (int) (delta - 2 * Math.floor((float) (delta - y) / 3));
        } else {
            return (int) (delta - 2 * Math.floor((delta - y) / 4));
        }
    }
}
