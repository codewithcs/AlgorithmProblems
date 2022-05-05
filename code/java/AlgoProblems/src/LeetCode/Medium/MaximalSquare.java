package LeetCode.Medium;

/*
Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300, n can be 0, so be careful.
matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxArea = 0;

        int rows = matrix.length;
        int columns = matrix[0].length;

        int minDimension = 1;
        int maxDimension = -1;
        if (rows == columns) {
            maxDimension = rows;
        } else if (rows < columns) {
            maxDimension = rows;
        } else {
            maxDimension = columns;
        }

        for (int i = minDimension; i <= maxDimension; i++) {

        }

        return 0;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '1'}, {'1', '1'}};
        System.out.println(maximalSquare2(matrix));
    }

    public static int maximalSquare2(char[][] matrix) {
        int largestSquare = 0;
        int currentSquare = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                boolean oneNotFound = false;

                if (matrix[i][j] == '1') {
                    currentSquare++;
                    int row = i + 1;
                    int column = j + 1;
                    while (!oneNotFound && row < matrix.length && column < matrix[0].length && matrix[row][column] == '1') {
                        for (int k = j; k <= column; k++) {
                            if (matrix[row][k] != '1') {
                                oneNotFound = true;
                                break;
                            }
                        }

                        for (int k = i; k <= row; k++) {
                            if (matrix[k][column] != '1') {
                                oneNotFound = true;
                                break;
                            }
                        }

                        if (!oneNotFound) {
                            currentSquare++;
                        }

                        row = row + 1;
                        column = column + 1;
                    }

                    currentSquare *= currentSquare;
                    if (currentSquare >= largestSquare) {
                        largestSquare = currentSquare;
                    }
                }

                currentSquare = 0;
            }
        }

        return largestSquare;
    }

    public static int maximalSquare3(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    // Using two 1d arrays.
    public static int maximalSquare4(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] odd = new int[cols+1];
        int[] even = new int[cols+1];
        int maxLength = 0;

        for(int i=1; i<= rows; i++){
            for(int j=1; j<= cols ; j++){
                if(matrix[i-1][j-1] == '1'){
                    if(i%2 == 1){
                        odd[j] = Math.min(Math.min(odd[j-1], even[j-1]), even[j]) + 1;
                        maxLength = Math.max(odd[j], maxLength);
                    } else {
                        even[j] = Math.min(Math.min(even[j-1], odd[j-1]), odd[j]) + 1;
                        maxLength = Math.max(even[j], maxLength);
                    }
                }
            }

            if(i%2 == 1){
                even = new int[cols+1];
            } else{
                odd = new int[cols+1];
            }
        }

        return maxLength * maxLength;
    }

    // Using one 1d array. Try to understand this.
    public int maximalSquare5(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
