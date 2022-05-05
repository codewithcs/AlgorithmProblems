package LeetCode.Medium.Trie_Problems;

/*
Given an m x n grid of characters board and a string word,
return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once.

Constraints:
m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and
uppercase English letters.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {

        int rows = board.length;
        int columns = board[0].length ;

        for(int i=0; i< rows ; i++){
            for(int j=0; j< columns; j++){
                if(board[i][j] == word.charAt(0)){
                    if(findWord(board, word, 0, i, j, rows, columns, new boolean[rows][columns])) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

    public boolean findWord(char[][] board, String word, int currentIndex, int i, int j, int rows, int columns, boolean[][] visited){
        if(currentIndex >= word.length()) return true;

        if(i<0 || i> rows-1 || j < 0 || j > columns-1) return false;

        if(visited[i][j]) return false;
        // If already visited, then we have already explored the path.

        boolean b = false;

        if(word.charAt(currentIndex) == board[i][j]){

            visited[i][j] = true;

            b = findWord(board, word, currentIndex + 1, i + 1, j, rows, columns, visited);
            b = b || findWord(board, word, currentIndex+1, i, j+1, rows, columns, visited);
            b = b || findWord(board, word, currentIndex+1, i-1, j, rows, columns, visited);
            b = b || findWord(board, word, currentIndex+1, i, j-1, rows, columns, visited);

            visited[i][j] = false;
        }

        return b;
    }
}
