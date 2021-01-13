package LeetCodePremium.Medium;

/*
Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxArea = 0;

        int rows = matrix.length;
        int columns = matrix[0].length;

        int minDimension = 1; int maxDimension = -1;
        if(rows == columns){
            maxDimension = rows;
        } else if(rows < columns){
            maxDimension = rows;
        } else {
            maxDimension = columns;
        }

        for(int i= minDimension; i<= maxDimension; i++){

        }

        return 0;
    }

    public static void main(String[] args) {
        char[][] matrix = { { '1', '1' }, { '1', '1' }};
        System.out.println(maximalSquare2(matrix));
    }

    public static int maximalSquare2(char[][] matrix) {
        int largestSquare = 0;
        int currentSquare = 0;

        for(int i=0; i< matrix.length ; i++){
            for(int j=0; j< matrix[0].length; j++){
                boolean oneNotFound = false;

                if(matrix[i][j] == '1'){
                    currentSquare++;
                    int row = i+1;
                    int column = j+1;
                    while(!oneNotFound && row < matrix.length && column < matrix[0].length && matrix[row][column] == '1'){
                        for(int k = j; k<= column; k++){
                            if(matrix[row][k] != '1'){
                                oneNotFound = true;
                                break;
                            }
                        }

                        for(int k=i; k<= row; k++){
                            if(matrix[k][column] != '1'){
                                oneNotFound = true;
                                break;
                            }
                        }

                        if(!oneNotFound){
                            currentSquare++;
                        }

                        row = row+1;
                        column = column+1;
                    }

                    currentSquare *= currentSquare;
                    if(currentSquare >= largestSquare){
                        largestSquare = currentSquare;
                    }
                }

                currentSquare = 0;
            }
        }

        return largestSquare;
    }
}
