package LeetCodePremium.Amazon.TreesAndGraph;

/*
An image is represented by a 2-D array of integers, each integer representing the
pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the
flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally
to the starting pixel of the same color as the starting pixel, plus any pixels
connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on.
Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Note:
The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class FloodFill {
    public static void main(String[] args) {
        int[][] image = { {0, 0, 0}, {0, 1, 1} } ;
        int[][] result = floodFill(image, 1, 1, 1);
    }
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        helper(image, sr, sc, color, newColor, visited);
        return image;
    }

    public static void helper(int[][] image, int sr, int sc, int color, int newColor, boolean[][] visited){
        if(visited[sr][sc]) {
            return;
        } else{
            visited[sr][sc] = true;
        }

        if(sr < 0 || sc < 0 || sr > image.length-1 || sc > image[0].length-1 ){
            return;
        }

        image[sr][sc] = newColor;

        if(sc > 0 && color == image[sr][sc-1]){
            helper(image, sr, sc-1, color, newColor, visited);
        }

        if(sr< image.length-1 && color == image[sr+1][sc]){
            helper(image, sr+1, sc, color, newColor, visited);
        }

        if(sc< image[0].length-1 && color == image[sr][sc+1]){
            helper(image, sr, sc+1, color, newColor, visited);
        }

        if(sr > 0 && color == image[sr-1][sc]){
            helper(image, sr-1, sc, color, newColor, visited);
        }
    }

    // Approach 2: No need to use a visited[][] boolean if order of recursive calls is correct.
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) dfs(image, sr, sc, color, newColor);
        return image;
    }
    public void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) { // We don't need the boolean because we set the pixel to a new colour and we have a check for that which does its work similar to that of a boolean.
            image[r][c] = newColor;
            if (r >= 1) dfs(image, r-1, c, color, newColor);
            if (c >= 1) dfs(image, r, c-1, color, newColor);
            if (r+1 < image.length) dfs(image, r+1, c, color, newColor);
            if (c+1 < image[0].length) dfs(image, r, c+1, color, newColor);
        }
    }
    // Treating each element separately in each of the recursive call. This makes the code a lot cleaner.
}
