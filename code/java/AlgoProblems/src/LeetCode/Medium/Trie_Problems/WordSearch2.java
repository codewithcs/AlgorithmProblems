package LeetCode.Medium.Trie_Problems;

import java.util.ArrayList;
import java.util.List;

/*
Given an m x n board of characters and a list of strings words,
return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells,
where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

Clarifying questions to ask:
Duplicate strings in the words[] array ?
Some words can be prefix or suffix of other words.

Constraints:
m == board.length
n == board[i].length

1 <= m, n <= 12
board[i][j] is a lowercase English letter.

1 <= words.length <= 3*10^4
1 <= words[i].length <= 10

words[i] consists of lowercase English letters.
All the strings of words are unique.
 */
public class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();

        int rows = board.length;
        int columns = board[0].length;

        TrieNode2 root = new TrieNode2();

        for(String word: words){
            TrieNode2 current = root;

            for(char currentChar: word.toCharArray()){
                int index = currentChar - 'a';

                if(current.children[index] == null){
                    current.children[index] = new TrieNode2();
                }

                current = current.children[index];
            }

            current.word = word;
        }

        for(int i= 0; i< rows; i++){
            for(int j=0; j< columns; j++){
                dfs(board, i, j, rows, columns, root, answer);
            }
        }

        return answer;
    }

    public void dfs(char[][] board, int i, int j, int rows, int columns, TrieNode2 node, List<String> answer){
        if(i<0 || j<0 || i>= rows || j>= columns) return;

        char currentChar = board[i][j];

        if(board[i][j] == ' ' || node.children[currentChar -'a'] == null) return;

        board[i][j] = ' ';

        node = node.children[currentChar -'a'];

        if(node.word != null) {
            answer.add(node.word);
            node.word = null;
        }
        // Do not return in this if statement if we are using the logic of visited in the given 2d grid itself.
        // This is because we have to reach the unmark logic to restore the 2d grid to its original value.
        // If we use boolean[][] for visited, then we can return in this if statement.

        dfs(board, i+1, j, rows, columns, node, answer);
        dfs(board, i, j-1, rows, columns, node, answer);
        dfs(board, i, j+1, rows, columns, node, answer);
        dfs(board, i-1, j, rows, columns, node, answer);

        board[i][j] = currentChar;
    }


}

class TrieNode2{
    TrieNode2[] children ; // Can make this Map<Character, TrieNode> as well.
    String word;

    TrieNode2(){
        children = new TrieNode2[26];
        word = null ;
    }
}