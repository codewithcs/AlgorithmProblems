package LeetCodePremium.Easy;

import java.util.LinkedList;
import java.util.Queue;

/*
You are given row x col grid representing a map where grid[i][j] = 1
represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally).
The grid is completely surrounded by water, and
there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
Determine the perimeter of the island.

Constraints:
row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        int perimeter = 0;
        boolean found = false;

        for(int i=0; i<rows; i++){
            for(int j=0; j< columns ; j++){
                if(grid[i][j] == 1){ // Only need to find the first 1.
                    perimeter = getPerimeter(grid, i, j, rows, columns);
                    found = true;
                    break;
                }
            }
            if(found){
                break;
            }
        }

        return perimeter;
    }

    public int getPerimeter(int[][] grid, int i, int j, int rows, int columns){
        /// Breadth First Search.
        int perimeter = 0;
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

                if(x>=0 && x< rows && y>=0 && y< columns){
                    if(grid[x][y] == 1){
                        queue.add(new int[]{x, y});
                        grid[x][y] = -1;
                    } else if(grid[x][y] == 0) {
                        perimeter++;
                    }
                } else {
                    perimeter++;
                }
            }
        }

        return perimeter;
    }
}
