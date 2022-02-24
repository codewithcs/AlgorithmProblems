package AlgoExpert_ExtremelyHard;

import java.util.List;

public class SquareOfZeroes2 {

    // O(n^4) time | O(1) space
    public static boolean squareOfZeroes(List<List<Integer>> matrix) {
        int n = matrix.size();

        // Important to start from top left corner or some corner.
        for(int topRow = 0; topRow< n ; topRow++){
            for(int leftCol = 0 ; leftCol < n ; leftCol++){
                int squareLength = 2; // start from minimum square length in the boundary.

                while(squareLength <= n-leftCol && squareLength <= n - topRow){
                    int bottomRow = topRow + squareLength -1 ;
                    int rightCol = leftCol + squareLength -1;
                    if(isSquareOfZeroes(matrix, topRow, leftCol, bottomRow, rightCol)){
                        return true;
                    }
                    squareLength++;
                }

            }
        }
        return false;
    }

    // r1 is the top row, c1 is the left column
    // r2 is the bottom row, c2 is the right column
    public static boolean isSquareOfZeroes(
            List<List<Integer>> matrix, int r1, int c1, int r2, int c2) {
        for (int row = r1; row < r2 + 1; row++) {
            if(matrix.get(row).get(c1) != 0 || matrix.get(row).get(c2) != 0){
                return false;
            }
        }
        for (int col = c1; col < c2 + 1; col++) {
            if(matrix.get(r1).get(col) != 0 || matrix.get(r2).get(col) != 0){
                return false;
            }
        }
        return true;
    }
}

