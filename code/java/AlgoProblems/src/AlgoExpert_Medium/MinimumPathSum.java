package AlgoExpert_Medium;

/*
Imp Q
Amazon, Fb, Google, Snapchat, Apple, etc

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100
 */
public class MinimumPathSum {

    // First Attempt.
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[rows-1][columns-1] = grid[rows-1][columns-1];

        int initialRowIndex = rows-2;

        for(int sum = rows+columns-3 ; sum>=0 ; sum--){
            int i = Math.max(initialRowIndex, 0);
            int j = sum - i;

            while(i< rows && j >= 0){
                dp[i][j] = grid[i][j];
                int total = dp[i][j];

                boolean b = false;
                if(j+1 < columns){
                    total += dp[i][j+1]; b = true;
                }

                if(i+1 < rows){
                    total = b ? Math.min(total, dp[i+1][j] + dp[i][j]) : dp[i+1][j] + dp[i][j];
                }

                dp[i][j] = total;

                i++; j--;
            }

            initialRowIndex--;
        }

        return dp[0][0];
    }

    // Approach 1: Brute Force, O(2^(m+n)) time and O(m+n) space.
    public int calculate(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }
    public int minPathSum1(int[][] grid) {
        return calculate(grid, 0, 0);
    }

    // Filling subproblems row wise.
    // O(mn) time and O(mn) space.
    public int minPathSum2(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[rows-1][columns-1] = grid[rows-1][columns-1];

        for(int i=rows-1; i>=0 ; i--){
            for(int j=columns-1; j>=0 ; j--){
                if(i != rows-1 && j != columns-1){
                    dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
                } else if(i!= rows-1){
                    dp[i][j] = grid[i][j] + dp[i+1][j];
                } else if( j!= columns-1){
                    dp[i][j] = grid[i][j] + dp[i][j+1];
                } else{
                    dp[i][j] = grid[i][j];
                }
            }
        }

        return dp[0][0];
    }

    // 1d Cache, O(mn) time and O(columns) space
    public int minPathSum3(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    dp[j] = grid[i][j] +  dp[j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + dp[j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                else
                    dp[j] = grid[i][j];
            }
        }
        return dp[0];
    }

    // O(mn) time and O(1) space.
    public int minPathSum4(int[][] grid) {
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    grid[i][j] = grid[i][j] +  grid[i][j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j],grid[i][j + 1]);
            }
        }

        return grid[0][0];
    }
}
