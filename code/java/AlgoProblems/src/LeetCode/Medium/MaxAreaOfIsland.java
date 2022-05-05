package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
(representing land) connected 4-directionally (horizontal or vertical)
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array.
(If there is no island, the maximum area is 0.)

Note: The length of each dimension in the given grid does not exceed 50.

Is this question similar to number of islands ?
Google
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        // Count number of 1s in each component.
        int rows = grid.length;
        int columns = grid[0].length;

        int maxArea = 0;

         for(int i=0; i<rows; i++){
             for(int j=0; j< columns ; j++){
                 if(grid[i][j] == 1){
                     maxArea = Math.max(maxArea, getArea(grid, i, j, rows, columns));
                 }
             }
         }

        return maxArea ;
    }

    // i, j would be always in bounds.
    public int getArea(int[][] grid, int i, int j, int rows, int columns){
        /// Breadth First Search.
        int area = 1;
        grid[i][j] = -1;

        int[] xDir = { 1, 0, -1, 0 };
        int[] yDir = { 0, 1, 0, -1 };

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        while(!queue.isEmpty()){
            int[] item = queue.poll();

            for(int k=0; k< 4; k++){
                int x = item[0] + xDir[k];
                int y = item[1] + yDir[k];

                if(grid[x][y] == 1){
                    queue.add(new int[]{x, y});
                    area++;
                    grid[x][y] = -1;
                }
            }
        }

        return area;
    }

    // DFS
    public int maxAreaOfIsland2(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int maxNumberOfOnes = 0;

        for(int i=0; i< rows; i++){
            for(int j=0; j< columns; j++){
                if(grid[i][j] == 1){
                    maxNumberOfOnes = Math.max(maxNumberOfOnes, getNumberOfOnes(grid, i, j, 0));
                }
            }
        }

        return maxNumberOfOnes;
    }

    // Number of rows may not be equal to number of columns.
    public int getNumberOfOnes(int[][] grid, int i, int j, int numberOfOnes){
        if(i<0 || j<0 || i>= grid.length || j>=grid[0].length){
            return 0;
        }

        if(grid[i][j] != 1){
            return 0;
        }

        grid[i][j] = 2;

        numberOfOnes++;

        // start from 0 for recursive calls because we cannot say if 1s will be present there
        // or not.
        numberOfOnes += getNumberOfOnes(grid, i+1, j, 0);
        numberOfOnes += getNumberOfOnes(grid, i-1, j, 0);
        numberOfOnes += getNumberOfOnes(grid, i, j+1, 0);
        numberOfOnes += getNumberOfOnes(grid, i, j-1, 0);

        return numberOfOnes;
    }


}
