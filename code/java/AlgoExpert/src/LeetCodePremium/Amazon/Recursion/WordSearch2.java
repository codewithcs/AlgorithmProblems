package LeetCodePremium.Amazon.Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells
are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

Note:
All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */

public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();

        for(String word: words){
            if(exist(board, word)){
                list.add(word);
            }
        }

        return list;
    }

    public boolean exist(char[][] board, String word) {
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


    // Approach 2: Backtracking + Trie
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];

        if (c == '#' || p.next[c - 'a'] == null) return; // Either already visited or not in the Trie.

        p = p.next[c - 'a'];

        // p.word would not be null for a node where a word ends. As we only filled the word value for a node when
        // we finished inserting a string in the Trie.
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;    // Important Step // de-duplicate
        }

        board[i][j] = '#'; // mark as visited

        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);

        board[i][j] = c; // Backtrack to Original
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word; // Word formed so far till this node.
    }

    // Approach 3:
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords3(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode2 root = new TrieNode2();
        for (String word : words) {
            TrieNode2 node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode2 newNode = new TrieNode2();
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

    private void backtracking(int row, int col, TrieNode2 parent) {
        Character letter = this._board[row][col];
        TrieNode2 currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = { -1, 0, 1, 0 };
        int[] colOffset = {  0, 1, 0, -1 };
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

    class TrieNode2 {
        HashMap<Character, TrieNode2> children = new HashMap<Character, TrieNode2>();
        String word = null;
        public TrieNode2() {}
    }
}
