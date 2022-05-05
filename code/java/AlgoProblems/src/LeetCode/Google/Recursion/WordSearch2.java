package LeetCode.Google.Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSearch2 {

    // It is necessary to do the cleanup in the recursive function.
    public List<String> findWords(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();

        for(String word: words){
            boolean found = false;
            for(int i=0; i<board.length; i++){
                for(int j=0; j< board[0].length; j++){
                    if(traverse( board, i, j, word, 0)){
                        answer.add(word);
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }
        }

        return answer;
    }


    public boolean traverse( char[][] board, int i, int j, String word, int currentIndex){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(currentIndex)) return false;

        if(currentIndex >= word.length()-1) return true;

        char currentCharacter = board[i][j];
        board[i][j] = '#'; // good way to mark as visited.

        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0,  0, -1};

        boolean b = false;
        for(int k=0; k<4; k++){
            b = traverse(board, i+dx[k], j+dy[k], word, currentIndex+1);
            if(b) break;
        }

        board[i][j] = currentCharacter ; // Important CleanUp Step.
        return b;
    }

    // Backtracking with Trie.
    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;
        public TrieNode() {}
    }

    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords2(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}
