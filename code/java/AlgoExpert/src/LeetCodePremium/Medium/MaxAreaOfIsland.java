package LeetCodePremium.Medium;

import sun.awt.image.ImageWatched;

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
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        // Count number of 1s in each component.
        int rows = grid.length;
        int columns = grid[0].length;

        int maxArea = Integer.MIN_VALUE;

         for(int i=0; i<rows; i++){
             for(int j=0; j< columns ; j++){
                 if(grid[i][j] == 1){
                     maxArea = Math.max(maxArea, getArea(grid, i, j, rows, columns));
                 }
             }
         }

        return maxArea == Integer.MIN_VALUE ? 0 : maxArea;
    }

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
}
