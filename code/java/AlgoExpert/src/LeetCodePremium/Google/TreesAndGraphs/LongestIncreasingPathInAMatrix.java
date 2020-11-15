package LeetCodePremium.Google.TreesAndGraphs;

import java.util.*;

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
    // BFS: Time Limit Exceeded
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

    // Depth First Search: Time Limit Exceeded
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }

        return ans;
    }

    private int dfs(int[][] matrix, int i, int j) {
        int ans = 0;
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                ans = Math.max(ans, dfs(matrix, x, y));
            }
        }
        return ++ans;
    }

    // DFS with Memoization.
    public int longestIncreasingPath3(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length; n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, cache));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }

        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
            }
        }

        return ++cache[i][j];
    }

    // Topological Sort:
    public int longestIncreasingPath4(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;

        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i) {
            System.arraycopy(grid[i], 0, matrix[i + 1], 1, n); // n: length
        }

        // calculate outdegrees
        int[][] outdegree = new int[m + 2][n + 2];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int[] d: dirs)
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]]) {
                        outdegree[i][j]++;
                    }
            }
        }

        // find leaves who have zero out degree as the initial level
        n += 2;
        m += 2;
        List<int[]> leaves = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                if (outdegree[i][j] == 0) {
                    leaves.add(new int[]{i, j});
                }
            }
        }

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            List<int[]> newLeaves = new ArrayList<>();

            for (int[] node : leaves) {
                for (int[] d:dirs) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (matrix[node[0]][node[1]] > matrix[x][y]) {
                        if (--outdegree[x][y] == 0) {
                            newLeaves.add(new int[]{x, y});
                        }
                    }
                }
            }

            leaves = newLeaves;
        }

        return height;
    }

}
