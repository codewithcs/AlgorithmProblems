package LeetCodePremium.Facebook.TreesAndGraph;

import java.util.LinkedList;
import java.util.Queue;

/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.

Note:
There will be at least one building in the given graph.
If it is not possible to build such house according to the above rules, return -1.
 */

/*
Approach:
1. Beats 98% 9ms JAVA BFS
2. Basically use lee-algroithm, bfs each 1 to find out min distance to each 0, accumulate this
distances to each 0 location: distance[][], finally find out min value from distance[][]
3. In the case of cannot find 0 from each house:

  4. not all 0s can reach each house: reachCount[][] to count the # of house each 0 can reach, only >= houseCount valid
  5. [~~improve speed~~]：not all house can reach each house, in this case, we cannot build such house,
        -count reachable house #, if < houseCount, return -1 directly!!!!!
 */

public class ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;

        // O(mn) space
        int[][] distance = new int[m][n];  // accumulated distance of each house (1) to each 0
        int[][] reachCount = new int[m][n]; // count reachable house for each 0

        int houseCount = 0;

        // O(mn)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    houseCount++;
                }
            }
        }

        //
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (!bfs(grid, distance, reachCount, houseCount, m, n, i, j)) {
                        return -1;
                    }
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reachCount[i][j] == houseCount) {
                    minDistance = Math.min(minDistance, distance[i][j]);
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private boolean bfs(int[][] grid, int[][] distance, int[][] housesReachable, int houseCount, int rows, int columns, int startingRow, int startingColumn) {

        int[][] visited = new int[rows][columns]; // Need it for graph problems.
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startingRow, startingColumn});

        int[] dx = new int[]{ 0, 0, -1, 1 };
        int[] dy = new int[]{ 1, -1, 0, 0 };

        int level = 0;
        int housesReachableFromStartingPoint = 0; // number of houses reachable in this BFS traversal.

        while (!q.isEmpty()) {
            int size = q.size();
            level++; // Need to do level order traversal in order to keep track distance using the "level" variable.

            for (int i = 0; i < size; i++) { // level by level
                int[] current = q.poll();

                for (int k = 0; k < 4; k++) { // visit all neighbors & accumulate distance
                    int x = current[0] + dx[k];
                    int y = current[1] + dy[k];

                    if (x >=0 && y >= 0 && x < rows && y < columns  && visited[x][y] == 0) {
                        if (grid[x][y] == 0) {
                            distance[x][y] += level;
                            visited[x][y] = 1;
                            housesReachable[x][y]++;
                            q.offer(new int[]{x, y});
                        } else if (grid[x][y] == 1) {
                            housesReachableFromStartingPoint++;
                            visited[x][y] = 1;
                        }
                    }
                }
            }
        }

        return housesReachableFromStartingPoint == houseCount;
    }
}
