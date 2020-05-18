package ramesh.aadhavan.dynamic;

/**
 * The minimum edit distance can be computed by
 *  f(A,B) -> {
 *      if(Ai == Bi)
 *          f(Ai-1, Bi-i) // no edits required
 *      else
 *          min {
 *              1 + f(Ai, Bi-1),  // deletion
 *              1 + f(Ai-1, Bi),  // insertion
 *              1 + f(Ai-1, Bi-1) // substitution
 *          }
 *  }
 */
public class EditDistance {

    @FunctionalInterface
    private interface TriFunction<T, A, B, C> {
        T compute(A a, B b, C c);
    }

    TriFunction<Integer, Integer, Integer, Integer> min = (x, y ,z) -> Math.min(x, Math.min(y, z));

    public int findMinEditDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] matrix = new int[m+1][n+1]; // Extra row and col for null strings

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(i == 0) {
                    matrix[i][j] = j;
                }
                else if(j == 0) {
                    matrix[i][j] = i;
                }
                else if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1];
                }
                else {
                    matrix[i][j] = min.compute(1 + matrix[i-1][j], 1 + matrix[i][j-1], 1 + matrix[i-1][j-1]);
                }
            }
        }

        return matrix[m][n];
    }


}
