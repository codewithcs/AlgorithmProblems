package LeetCodePremium.Medium;

/*
The board is made up of an m x n grid of cells, where each cell has an initial state:
live (represented by a 1) or dead (represented by a 0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
(taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population.
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
The next state is created by applying the above rules simultaneously to every cell in the current state,
where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.

Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] is 0 or 1.

Follow up:
Could you solve it in-place? Remember that the board needs to be updated simultaneously:
You cannot update some cells first and then use their updated values to update other cells.

In this question, we represent the board using a 2D array. In principle, the board is infinite,
which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
How would you address these problems?
 */

public class GameOfLife {

    // O(mn) space and time.
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;

        int[][] board2 = new int[rows][columns];

        // Important Step.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board2[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int neighbors = 0;
                if (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] == 1) {
                    neighbors++;
                }
                if (i - 1 >= 0 && board[i - 1][j] == 1) {
                    neighbors++;
                }
                if (i - 1 >= 0 && j + 1 < columns && board[i - 1][j + 1] == 1) {
                    neighbors++;
                }
                if (j - 1 >= 0 && board[i][j - 1] == 1) {
                    neighbors++;
                }
                if (j + 1 < columns && board[i][j + 1] == 1) {
                    neighbors++;
                }
                if (i + 1 < rows && j - 1 >= 0 && board[i + 1][j - 1] == 1) {
                    neighbors++;
                }
                if (i + 1 < rows && board[i + 1][j] == 1) {
                    neighbors++;
                }
                if (i + 1 < rows && j + 1 < columns && board[i + 1][j + 1] == 1) {
                    neighbors++;
                }

                if (board[i][j] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        board2[i][j] = 0;
                    } else if (neighbors == 3) {
                        board2[i][j] = 1;
                    }
                } else if (neighbors == 3) {
                    board2[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = board2[i][j];
            }
        }
    }

    // O(mn) time and O(1) space.
    public void gameOfLife2(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int neighbors = 0;
                if (i - 1 >= 0 && j - 1 >= 0 && (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == -1 ) ) {
                    neighbors++;
                }
                if (i - 1 >= 0 && (board[i - 1][j] == 1 || board[i-1][j] == -1 )) {
                    neighbors++;
                }
                if (i - 1 >= 0 && j + 1 < columns && (board[i - 1][j + 1] == 1 || board[i-1][j+1] == -1) ){
                    neighbors++;
                }
                if (j - 1 >= 0 && (board[i][j - 1] == 1 || board[i][j-1] == -1 )) {
                    neighbors++;
                }
                if (j + 1 < columns && (board[i][j + 1] == 1 || board[i][j+1] == -1)) {
                    neighbors++;
                }
                if (i + 1 < rows && j - 1 >= 0 && (board[i + 1][j - 1] == 1 || board[i+1][j-1] == -1)) {
                    neighbors++;
                }
                if (i + 1 < rows && (board[i + 1][j] == 1 || board[i+1][j] == -1)) {
                    neighbors++;
                }
                if (i + 1 < rows && j + 1 < columns && (board[i + 1][j + 1] == 1 || board[i+1][j+1] == -1)) {
                    neighbors++;
                }

                if (board[i][j] == 1 ) {
                    if (neighbors < 2 || neighbors > 3) {
                        board[i][j] = -1;  // 1 to 0
                    }
                } else if (neighbors == 3) { // 0 to +1
                    board[i][j] = 2;
                }
            }
        }

        for(int i=0; i< rows; i++){
            for(int j=0; j< columns ; j++){
                if(board[i][j] == -1){
                    board[i][j] = 0;
                } else if (board[i][j] == 2){
                    board[i][j] = 1;
                }
            }
        }
    }

    // Replacing 8 ifs in a loop.
    public void gameOfLife3(int[][] board) {
        // Neighbors array to find 8 neighboring cells for a given cell
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // Iterate through board cell by cell.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell count the number of live neighbors.
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) { // Out of 9 values we are only interested in 8.
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // Check the validity of the neighboring cell.
                            // and whether it was originally a live cell.
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) { // Using absolute value to denote +1 or -1.
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // Rule 1 or Rule 3
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // -1 signifies the cell is now dead but originally was live.
                    board[row][col] = -1;
                }
                // Rule 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 signifies the cell is now live but was originally dead.
                    board[row][col] = 2;
                }
            }
        }

        // Get the final representation for the newly updated board.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

}