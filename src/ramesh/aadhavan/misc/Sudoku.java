package ramesh.aadhavan.misc;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Sudoku {

    public static boolean isValidSudoku(char[][] board) {
        return _rowCheck(board) &&
                _columnCheck(board) &&
                _blockCheck(board);
    }

    public static boolean isValidSudokuMultipleBoardsConcurrent(char[][][] board)
            throws InterruptedException, ExecutionException {
        int numBoards = board.length;
        ExecutorService executorService = Executors.newFixedThreadPool(numBoards);
        List<Callable<Boolean>> isValidSudokuTasks = new ArrayList<>();

        for(int i=0; i<board.length; i++) {
            final int boardNumber = i;
            isValidSudokuTasks.add(() -> isValidSudoku(board[boardNumber]));
        }

        final List<Future<Boolean>> futures = executorService.invokeAll(isValidSudokuTasks);

        try {
            for(Future<Boolean> future : futures) {
                if(!future.get())
                    return false;
            }
        }
        catch (ExecutionException e) {
            throw e;
        }
        finally {
            executorService.shutdownNow();
        }

        return true;
    }

    public static boolean isValidSudokuConcurrent(final char[][] board) throws InterruptedException,
            ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(3);
        final List<Callable<Boolean>> tasks = new ArrayList<>();

        tasks.add(() -> _rowCheck(board));
        tasks.add(() -> _columnCheck(board));
        tasks.add(() -> _blockCheck(board));

        final List<Future<Boolean>> futures = executorService.invokeAll(tasks);

        try {
            for (Future<Boolean> future : futures) {
                if (!future.get())
                    return false;
            }
        }
        catch (ExecutionException e) {
           System.err.println("Execution Error: "+e);
           throw e;
        }
        finally {
            executorService.shutdown();
            if(!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        }

        return true;
    }

    private static boolean _rowCheck(char[][] board) {
        Preconditions.checkNotNull(board);

        int[] dup = new int[10];
        for(int i=0; i<9; i++) {
            _clear(dup);
            for(int j=0; j<9; j++) {
                char num = board[i][j];
                if(num != '.') {
                    dup[num - 48]++;
                }
            }
            for(int k : dup) {
                if(k > 1)
                    return false;
            }
        }

        return true;
    }

    private static boolean _columnCheck(char[][] board) {
        int[] dup = new int[10];
        for(int i=0; i<9; i++) {
            _clear(dup);
            for(int j=0; j<9; j++) {
                char num = board[j][i];
                if(num != '.') {
                    dup[num - 48]++;
                }
            }
            for(int k : dup) {
                if(k > 1)
                    return false;
            }
        }

        return true;
    }

    private static boolean _blockCheck(char[][] board) {
        int x = 0;
        for(int i=0; i<3; i++) {
            int y=0;
            for(int j=0; j<3; j++) {
                if(!_blockCheck(board, x, x+3, y, y+3))
                    return false;
                y+=3;
            }
            x+=3;
        }

        return true;
    }

    private static boolean _blockCheck(char[][] board, int beginX, int endXExclusive,
                                       int beginY, int endYExclusive) {
        int[] dup = new int[10];
        for(int i=beginX; i<endXExclusive; i++) {
            for(int j=beginY; j<endYExclusive; j++) {
                char num = board[i][j];
                if(num != '.') {
                    dup[num - 48]++;
                }
            }
        }

        for(int i : dup) {
            if(i > 1)
                return false;
        }

        return true;
    }

    private static void _clear(int[] arr) {
        Arrays.fill(arr, 0);
    }
}
