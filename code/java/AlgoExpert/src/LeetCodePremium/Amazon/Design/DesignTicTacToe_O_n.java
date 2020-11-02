package LeetCodePremium.Amazon.Design;

public class DesignTicTacToe_O_n {
    int[][] grid;

    /** Initialize your data structure here. */
    public DesignTicTacToe_O_n(int n) {
        grid = new int[n][n];
    }

    public int move(int row, int col, int player) {
        if (row >= grid.length || col >= grid.length) {
            return 0; // out of the grid
        }

        if (grid[row][col] != 0) return 0; // cell is used, no need to use boolean[][] array.

        grid[row][col] = player == 1 ? 1 : 2;

        if (checkVerticallyWin(col, player)) {
            return player;
        }
        if (checkHorizontallyWin(row, player)) {
            return player;
        }
        if (checkDiagonallyWin(row, col, player)) {
            return player;
        }

        return 0;
    }

    private boolean checkVerticallyWin(int col, int player) {
        for (int i=0; i<grid.length; i++) {
            if (grid[i][col] != player) return false;
        }
        return true;
    }

    private boolean checkHorizontallyWin(int row, int player) {
        for (int j=0; j<grid[0].length; j++) {
            if (grid[row][j] != player) return false;
        }
        return true;
    }

    private boolean checkDiagonallyWin(int row, int col, int player) {
        if (row != col && row+col != grid.length-1) {
            return false;
        }

        boolean topLeftToBottomRight = true;
        boolean topRightToBottomLeft = true;
        for (int i=0; i<grid.length; i++) {
            if (grid[i][i] != player) topLeftToBottomRight = false;
        }

        for (int i=0; i<grid.length; i++) {
            if (grid[i][grid.length-1-i] != player) topRightToBottomLeft = false;
        }

        // don;t need to have 2 ifs on topLeftToBottomRight and topRightToBottomLeft.
        // Instead can do the following: Cleaner code.
        return topRightToBottomLeft || topLeftToBottomRight;
    }

}
