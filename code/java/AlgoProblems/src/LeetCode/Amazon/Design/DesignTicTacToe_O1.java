package LeetCode.Amazon.Design;

public class DesignTicTacToe_O1 {
    private int[] rows; // each element stores the count in that row.
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /** Initialize your data structure here. */
    public DesignTicTacToe_O1(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1; // important that we add equal magnitude values. 

        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col){
            diagonal += toAdd;
        }

        if (col+row == cols.length- 1) {
            antiDiagonal += toAdd;
        }

        int size = rows.length;

        if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size  ||
                Math.abs(antiDiagonal) == size) {
            return player;
        }

        return 0;
    }
}
