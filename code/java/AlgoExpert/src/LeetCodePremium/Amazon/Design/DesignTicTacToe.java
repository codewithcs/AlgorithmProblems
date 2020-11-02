package LeetCodePremium.Amazon.Design;

/*
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

Implement the TicTacToe class:
TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board.
The move is guaranteed to be a valid move.

Follow up:
Could you do better than O(n2) per move() operation?

Constraints:

2 <= n <= 100
player is 1 or 2.
1 <= row, col <= n
(row, col) are unique for each different call to move.
At most n^2 calls will be made to move.
 */

import java.util.HashSet;
import java.util.Set;

public class DesignTicTacToe {
    /** Initialize your data structure here. */
    char[][] board;

    public DesignTicTacToe(int n) {
        board = new char[n][n] ;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(player == 1){
            board[row][col] = 'X';
            if(checkEquality(board, 'X')){
                return 1;
            } else {
                return 0;
            }
        }
        else {
            board[row][col] = 'O';
            if(checkEquality(board, 'O')) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    public boolean checkEquality(char[][] board, char move){
        Set<Integer> visitedColumns = new HashSet<>();

        // Row-wise
        for(int i=0 ; i< board.length; i++){
            boolean found = true;
            for(int j=0; j< board[0].length; j++) {
                if (board[i][j] != move) {
                    found= false; visitedColumns.add(j);
                    break;
                }
            }
            if(found) {
                return true;
            }
        }

        // Column-wise
        for(int i=0 ; i< board[0].length; i++){
            if(visitedColumns.contains(i)) continue;
            boolean found = true;
            for(int j=0; j< board.length; j++) {
                if (board[j][i] != move) { // imp: will be [j][i] or swap i and j in the loop.
                    found= false;
                    break;
                }
            }
            if(found) {
                return true;
            }
        }

        // Diagonal
        boolean found = true;
        for(int i=0 ; i< board.length; i++){
            if(board[i][i] != move){
                found = false;
                break;
            }
        }

        if(found) {
            return true;
        }

        found = true;
        for(int i=0; i<board.length; i++){
            if(board[i][board.length-i-1] != move){
                found = false;
                break;
            }
        }
        if(found) {
            return true;
        }

        return false;
    }
}
