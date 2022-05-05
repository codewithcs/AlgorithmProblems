package LeetCode.Amazon.TreesAndGraph;

/*
You are asked to cut off trees in a forest for a golf event. The forest is
represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and
this positive number represents the tree's height.
In one step you can walk in any of the four directions top, bottom, left and right also
when standing in a point which is a tree you can decide whether or not to cut off the tree.

You are asked to cut off all the trees in this forest in the order of tree's height - always
cut off the tree with lowest height first. And after cutting, the original
place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to
cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and
there is at least one tree needs to be cut off.

Constraints:
1 <= forest.length <= 50
1 <= forest[i].length <= 50
0 <= forest[i][j] <= 10^9
 */

import java.util.*;

public class CutOfTreesForGolfEvent {
/*
Very Important Test Case:
[[54581641, 64080174, 24346381, 69107959],
[86374198, 61363882, 68783324, 79706116],
[668150, 92178815, 89819108, 94701471],
[83920491, 22724204, 46281641, 47531096],
[89078499, 18904913, 25462145, 60813308]]
 */
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int cutOffTree2(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList();

        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c}); // We need these 3 things to get complete information.
            }
        }

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));
        // No need to iterate over the list and sort individual elements.

        int ans = 0, sr = 0, sc = 0;
        for (int[] tree: trees) {
            int d = bfs(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1]; sc = tree[2];
        }

        return ans;
    }

    public int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();

        Queue<int[]> queue = new LinkedList(); // bfs calculates the shortest path.

        queue.add(new int[]{sr, sc, 0}); // third value is the number of steps.

        boolean[][] seen = new boolean[R][C]; // new boolean array at every function call as we might require to visit an element that was marked visited in previous call.
        seen[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tr && cur[1] == tc) return cur[2];

            for (int di = 0; di < 4; ++di) {
                int r = cur[0] + dr[di];
                int c = cur[1] + dc[di];
                if (0 <= r && r < R && 0 <= c && c < C &&
                        !seen[r][c] && forest.get(r).get(c) > 0) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c, cur[2]+1});
                }
            }

        }

        return -1;
    }



}
