package LeetCode.Google.Recursion;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        // Treat every character as the starting point.
        for(int i=0; i<board.length; i++){
            for(int j=0; j< board[0].length; j++){
                if(traverse( board, i, j, "", word)){
                    return true;
                }
            }
        }

        return false;
    }

    // Time Limit Exceeded.
    public boolean traverse( char[][] board, int i, int j, String current, String word){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;

        if(board[i][j] == '#') return false;

        current = current + board[i][j];

        if(current.equals(word)) return true;

        char currentCharacter = board[i][j];
        board[i][j] = '#';

        boolean b1 = traverse(board, i+1, j, current, word);
        boolean b2 = traverse(board, i-1, j, current, word);
        boolean b3 = traverse(board, i, j-1, current, word);
        boolean b4 = traverse(board, i, j+1, current, word);

        board[i][j] = currentCharacter ;

        return b1 || b2 || b3 || b4;
    }

    // 4 Important Optimizations:
    /*
      1: Matching the individual characters rather than the whole string.
      2. Returning before going into unwanted recursive calls if previous call returned true.
      3.  Good way of marking visited by using '#'
      4. Do the Clean Up if necessary. Break using a loop instead of return.
     */
    public boolean traverse2( char[][] board, int i, int j, String word, int currentIndex){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(currentIndex)) return false;

        if(board[i][j] == '#') return false; // redundant check

        if(currentIndex >= word.length()-1) return true;

        char currentCharacter = board[i][j];
        board[i][j] = '#'; // good way to mark as visited.

        boolean b = traverse2(board, i+1, j, word, currentIndex+1);
        if(b) return true; // Very Important optimization: Useful in tree and graph problems as well.

        b = b || traverse2(board, i-1, j, word, currentIndex+1);
        if(b) return true;

        b = b || traverse2(board, i, j-1, word, currentIndex+1);
        if(b) return true;

        b = b || traverse2(board, i, j+1, word, currentIndex+1);

        board[i][j] = currentCharacter ;

        return b;
    }
    // The above program would leave the matched letters in the original to '#'


    // Using a for loop to call the recursion.
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist2(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row)
            for (int col = 0; col < this.COLS; ++col)
                if (this.backtrack(row, col, word, 0))
                    return true;

        return false;
    }

    // Nice Approach: We do the cleanup before returning.
    protected boolean backtrack(int row, int col, String word, int index) {
        /* Step 1). check the bottom case. */
        if (index >= word.length())
            return true;

        /* Step 2). Check the boundaries. */
        if (row < 0 || row == this.ROWS || col < 0 || col == this.COLS
                || this.board[row][col] != word.charAt(index))
            return false;

        /* Step 3). explore the neighbors in DFS */
        boolean ret = false;
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1};
        int[] colOffsets = {1, 0, -1, 0};
        for (int d = 0; d < 4; ++d) {
            ret = this.backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
            if (ret)
                break; // break rather than return true. Done in order to do the clean up on line 117.
        }

        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }
}
