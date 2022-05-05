package LeetCode.Amazon.TreesAndGraph;

/*
Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or
vertically. You may assume all four edges of the grid are all surrounded by water.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = { {'1', '1', '1', '1', '0' } , {'1','1','0', '1', '0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'} };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int count = 0;

        for(int i=0 ; i< grid.length ; i++){
            for(int j=0 ; j< grid[0].length ; j++){
                if(grid[i][j] == '1'){
                    count += helper(i , j, grid, visited);
                }
            }
        }

        return count;
    }

    public static int helper(int i, int j, char[][] grid, boolean[][] visited){
        if(visited[i][j] || grid[i][j] == '0') return 0;

        visited[i][j] = true;

        if(i>=1 ){
            helper(i-1, j, grid, visited);
        }

        if(j>=1){
            helper(i, j-1, grid, visited);
        }

        if(i+1 < grid.length){
            helper(i+1, j, grid, visited);
        }

        if(j+1 < grid[0].length){
            helper(i, j+1, grid, visited);
        }

        return 1 ;
    }

    // We can avoid using boolean by marking the current node as '0' if it was previously 1.
    // If recursive calls visit this node again, then we return 0 as it is '0' and not '1'.
    public static int helper2(int i, int j, char[][] grid){
        if(i<0 || j <0 || i>grid.length || j>grid[0].length){
            return 0 ;
        }

        if(grid[i][j] == '0'){
            return 0;
        }

        grid[i][j] = '0';

        // We do this logic just to mark the 1s as visited.
        helper2(i-1, j, grid);
        helper2(i, j-1, grid);
        helper2(i+1, j, grid);
        helper2(i, j+1, grid);

        return 1 ;
    }

    // Approach 3: Breadth First Search, better in space complexity.
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
                            neighbors.add((row-1) * nc + col);
                            grid[row-1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row+1][col] == '1') {
                            neighbors.add((row+1) * nc + col);
                            grid[row+1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
                            neighbors.add(row * nc + col-1);
                            grid[row][col-1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col+1] == '1') {
                            neighbors.add(row * nc + col+1);
                            grid[row][col+1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

    // Simple BFS: O(MN) time and O(min(M, N)) space.
    public int numIslands3(char[][] grid) {
        int rows = grid.length ;
        int columns =  grid[0].length ;
        int count = 0;
        for(int i=0 ; i< rows; i++){
            for(int j=0; j<columns; j++){
                if(grid[i][j] == '1' ){
                    count += traverseGrid(grid, rows, columns, i, j);
                }
            }
        }
        return count;
    }

    public int traverseGrid(char[][] grid, int rows, int columns, int startingRow, int startingColumn){
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};

        // Iterative way of BFS.
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startingRow, startingColumn});

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            for(int i=0 ; i<4; i++){
                int x = current[0] + dx[i];
                int y = current[1] + dy[i];
                if(x>=0 && x<rows && y>=0 && y<columns){
                    if(grid[x][y] == '1'){
                        queue.add(new int[]{x, y});
                        grid[x][y] = '2';
                    }
                }
            }
        }

        grid[startingColumn][startingColumn] = '2';

        return 1;
    }
}
