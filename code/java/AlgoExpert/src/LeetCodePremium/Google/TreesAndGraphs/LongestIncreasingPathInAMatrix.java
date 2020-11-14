package LeetCodePremium.Google.TreesAndGraphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 */
public class LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int length = 1;

        for(int i=0 ; i<rows; i++){
            for(int j=0; j<columns; j++){
                length = Math.max(length, findLength(matrix, rows, columns, i, j));
            }
        }

        return 0;
    }
    public static int findLength(int[][] matrix, int rows, int columns, int startingRow, int startingColumn){
        int length = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startingRow, startingColumn});

        int[] dx = { 0, 1, -1, 0 };
        int[] dy = { 1, 0, 0, -1 };
        int level = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            level++ ;

            for(int i=0; i<size; i++){
                int[] current = queue.poll();

                for(int j=0 ; j<4 ; j++){

                    int x = current[0] + dx[j];
                    int y = current[1] + dy[j];

                    if(x>=0 && x< rows && y>=0 && y<columns ){
                        if(matrix[current[0]][current[1]] < matrix[x][y]){ // strictly increasing.
                            queue.add(new int[]{x, y});
                            length = Math.max(length, level+1);
                        }
                    }
                }

            }
        }

        return length;
    }

    // Depth First Search
    public static int findLength2(int[][] matrix, int rows, int columns, int startingRow, int startingColumn){
        int length = 1;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startingRow, startingColumn});

        int[] dx = { 0, 1, -1, 0 };
        int[] dy = { 1, 0, 0, -1 };
        boolean[][] visited = new boolean[rows][columns];
        int currentLength = 0;

        while(!stack.isEmpty()){
            int[] current = stack.pop();
            currentLength++;
                for(int j=0 ; j<4 ; j++) {
                    int x = current[0] + dx[j];
                    int y = current[1] + dy[j];

                    if (x >= 0 && x < rows && y >= 0 && y < columns && !visited[x][y]) {
                        if (matrix[current[0]][current[1]] < matrix[x][y]) { // strictly increasing.
                            stack.push(new int[]{x, y});
                            length = Math.max(length, level + 1);
                        }
                    }

                }
            visited[current[0]][current[1]] = true;
        }

        return length;
    }
}
