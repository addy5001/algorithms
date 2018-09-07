package ramesh.aadhavan.dynamic;

public class LongestCommonSubsequence {
    public static void findSubsequence(String string1, String string2) {
        int x = string1.length();
        int y = string2.length();
        int[][] grid = new int[x][y];

        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++) {
                if(string1.charAt(i) == string2.charAt(j))
                    grid[i][j] = ((i-1) < 0 || (j-1) < 0) ? 1 : grid[i-1][j-1] + 1;
                else
                    grid[i][j] = ((i-1) < 0 || (j-1) < 0) ? 0 : Math.max(grid[i-1][j], grid[i][j-1]);
            }
        }

        System.out.println("------------GRID FOR LCS------------");
        int max = 0;
        StringBuilder lcsBuilder = new StringBuilder();
        for(int i=0; i<x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(grid[i][j] + " ");
                if(grid[i][j] > max) {
                    max = grid[i][j];
                    lcsBuilder.append(string1.charAt(i));
                }
            }
            System.out.println();
        }

        System.out.println("------------LONGEST COMMON SUBSTRING LENGTH------------");
        System.out.println(max);
        System.out.println("------------LONGEST COMMON SUBSTRING------------");
        System.out.println(lcsBuilder.toString());
    }

    public static void main(String[] args) {
        LongestCommonSubsequence.findSubsequence("ijijijikikikijikhik", "ijijikikijikijihjki");
    }
}
