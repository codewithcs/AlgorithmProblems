package LeetCode.Amazon.Recursion;

/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells
are horizontally or vertically neighboring.

The same letter cell may not be used more than once.

Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = { {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'b'} } ;
        System.out.println("Result is : " + exist(board, "aaaaaaaaaaaaaaaaaaaa"));
    }

    public static boolean exist(char[][] board, String word) {
        boolean result = false;
        for(int i=0 ; i< board.length; i++){
            for(int j=0; j< board[0].length ; j++){
                result = helper(board, i, j, word, "");
                if(result) return true;
            }
        }
        return result;
    }

    public static boolean helper(char[][] board, int i, int j, String word,
                          String current){
        boolean[][] visited = new boolean[board.length][board[0].length];
        if(word.charAt(0) != board[i][j]){ // Backtracking Step.
            return false;
        }
        return dfs(board, i, j, word, current, visited, 0);
    }

    // Approach 1: Not working
    public static boolean dfs(char[][] board, int i, int j, String word,
                       String current, boolean[][] visited, int currentIdx) {
        System.out.println("currentIdx is : " + currentIdx);
        System.out.println("current word is : " + current);
        System.out.println();

        int rows = board.length;
        int columns = board[0].length;

        if (currentIdx >= word.length()) return true;

        if (i < 0 || j < 0 || i > rows - 1 || j > columns - 1 || word.charAt(currentIdx)!= board[i][j] || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;

        char c = board[i][j];

        boolean b1 = dfs(board, i + 1, j, word, current + c, visited, currentIdx + 1);
        boolean b2 = dfs(board, i, j + 1, word, current + c, visited, currentIdx + 1);
        boolean b3 = dfs(board, i, j - 1, word, current + c, visited, currentIdx + 1);
        boolean b4 = dfs(board, i - 1, j, word, current + c, visited, currentIdx + 1);

        boolean result = b1 || b2 || b3 || b4;

        visited[i][j] = false;

        return result;
    }


    // Approach 2:
    public boolean exist2(char[][] board, String word) {

        for(int i=0 ; i< board.length; i++){
            for(int j=0; j< board[0].length ; j++){
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, 0, word)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, int i, int j, int count, String word){
        if(count == word.length()){
            return true;
        }

        if(i<0 || i>=board.length || j<0 || j>= board[0].length || board[i][j] != word.charAt(count)){
            return false;
        }

        char temp = board[i][j];
        board[i][j] = ' '; // mark as visited.

        boolean found = dfs(board, i+1, j, count+1, word)
                || dfs(board, i-1, j, count+1, word)
                || dfs(board, i, j+1, count+1, word)
                || dfs(board, i, j-1, count+1, word);

        board[i][j] = temp;
        return found;
    }

    // Approach 3: Using Bit Manipulation.
    public boolean exist3(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist3(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist3(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist3(board, y, x+1, word, i+1)
                || exist3(board, y, x-1, word, i+1)
                || exist3(board, y+1, x, word, i+1)
                || exist3(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }

}
