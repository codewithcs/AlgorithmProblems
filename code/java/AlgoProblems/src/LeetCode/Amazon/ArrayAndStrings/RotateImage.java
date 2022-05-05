package LeetCode.Amazon.ArrayAndStrings;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.
 */

public class RotateImage {

    // O(n^2) time and O(1) space.
    public void rotate(int[][] matrix) {
        for(int i=0 ; i< matrix.length; i++){
            for(int j=0 ; j< matrix[i].length; j++){
                if( j < matrix[i].length/2) {
                    swap(matrix, i, j, i, matrix[i].length - 1 - j);
                } else {
                    break;
                }
            }
        }

        // First I set the positions for the off diagonal correctly.
        // Then I iterate over off diagonals and swap the elements.

        int diff = matrix.length-1; // 3
        int k=0 ;

        while(k<= matrix.length-2 && diff> 0){
            int i=0;
            int j = i+matrix.length-1-diff;
            while(i<=k && j>=0){
                swap(matrix, i, j, diff+i, diff+j);
                i++ ; j--;
            }
            diff--; k++;
        }

    }

    void swap(int[][] matrix,  int i, int j, int x, int y){
        int num = matrix[i][j];
        matrix[i][j] = matrix[x][y];
        matrix[x][y] = num;
    }

    // Rotate  4 rectangles in 1 single loop.
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i ++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    // Rotate 4 rectangles.
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int[] tmp = new int[4];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }


    // Transpose and then reverse.

    public void rotate4(int[][] matrix) {
        int n = matrix.length;
        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

}
