package LeetCodePremium.Facebook.DP;

/*
Given a 2D matrix matrix, find the sum of the elements inside
the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

// O(m) time per query. O(mn) pre-computation time and space.
public class RangeSumQuery2D {
    private int[][] dp;

    public RangeSumQuery2D (int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return  ;

        dp = new int[matrix.length][matrix[0].length];

        for(int i=0; i<matrix.length; i++){
            dp[i][0] = matrix[i][0];
        }

        for(int i=0; i< dp.length; i++){
            for(int j=1; j< dp[0].length; j++){
                dp[i][j] = dp[i][j-1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int row = row1; row <= row2; row++){
            if(col1 == 0){ // Imp check
                sum += dp[row][col2];
            } else {
                sum += dp[row][col2] - dp[row][col1-1];
            }
        }
        return sum;
    }

    // Caching Smarter, O(1) time per query, O(mn) time pre-computation.
    // O(mn) space. 
    private int[][] dp2;

    public void setValue(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return  ;

        dp2 = new int[matrix.length+1][matrix[0].length+1];

        for(int i=1; i< dp2.length; i++){ // Filling a particular region.
            for(int j=1; j< dp2[0].length; j++){
                dp2[i][j] = dp2[i-1][j] + dp2[i][j-1] + matrix[i-1][j-1] - dp2[i-1][j-1];
            }
        }
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        return dp2[row2 + 1][col2 + 1] - dp2[row1][col2 + 1] - dp2[row2 + 1][col1] + dp2[row1][col1];
    }
}
