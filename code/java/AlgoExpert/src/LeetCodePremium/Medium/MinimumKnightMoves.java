package LeetCodePremium.Medium;


import java.util.*;

/*
In an infinite chess board with coordinates from -infinity to +infinity,
you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below.
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y].
It is guaranteed the answer exists.

Constraints:
|x| + |y| <= 300
 */
public class MinimumKnightMoves {
    public int minKnightMoves(int x, int y) {
        // Symmetry for axes
        x = Math.abs(x);
        y = Math.abs(y);
        // Symmetry for diagonal
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 1 && y == 0) {
            return 3;
        }
        if (x == 2 && y == 2) {
            return 4;
        }
        int delta = x - y;
        if (y > delta) {
            return (int) (delta - 2 * Math.floor((float) (delta - y) / 3));
        } else {
            return (int) (delta - 2 * Math.floor((delta - y) / 4));
        }
    }


    private final int[][] solutions = new int[][] {
            {0, 3, 2, 3, 2},
            {3, 2, 1, 2, 3},
            {2, 1, 4, 3, 2},
            {3, 2, 3, 2, 3},
            {2, 3, 2, 3, 4}
    };

    public int minKnightMoves2(int x, int y) {
        int moves = 0;
        x = Math.abs(x);
        y = Math.abs(y);

        while (x > 4 || y > 4) {
            if (x > y) {
                x = x - 2;
                y = (y > 0) ? y - 1 : y + 1;
            } else {
                y = y - 2;
                x = (x > 0) ? x - 1 : x + 1;
            }
            moves++;
        }
        return moves + solutions[y][x];
    }


    // BFS
    //store possible moves from one point as an array of changes in coordinates
    static int[][] d = new int[][] {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
    };

    // Using encodings and storing them in the queue.
    public int minKnightMoves3(int x, int y) {
        //We can invert the sign of the number - it doesn't affect the result
        x = Math.abs(x); y = Math.abs(y);

        //store seen cells
        Set<Integer> seen = new HashSet();

        //this is queue for the BFS, initialize it with 0,0 position
        Queue<Integer> q = new LinkedList<>();

        q.add(0);

        //this will store the number of moves
        int res = 0;

        // Start BFS
        while (!q.isEmpty()) {

            // On each step we only poll number of cells that we have in the queue now.
            // everything added after this will be counted at the next step
            int size = q.size();

            for (int i = 0; i < size; i++) { // Level Order Traversal
                // Get the encoded num, convert it to coordinated and check if it's our target
                int next = q.poll(); // Retrieves and removes the head of the queue.
                int curX = (next>>10), curY = next - (curX<<10);
                if (curX == x && curY == y) {
                    return res;
                }

                // If not the target - make all possible moves
                for (int k = 0; k < d.length; k++) {
                    //each next move
                    int x1 = curX + d[k][0], y1 = curY + d[k][1];
                    // Encode the move to store it in the set of visited cells
                    int curEnc = y1 + (x1<<10);

                    if (x1 >= -2 && y1 >= -2 && seen.add(curEnc)) {
                        q.add(curEnc);
                    }
                }
            }
            // After we done with all moves from all points stored in the queue at the beginning -
            // Increment the step counter
            res++;
        }

        return -1;
    }

    // Storing a Node in the queue. No need to use encodings.
    public int minKnightMoves4(int x, int y) {
        // We can invert the sign of the number - it doesn't affect the result
        x = Math.abs(x); y = Math.abs(y);

        // store seen cells
        Set<Node> seen = new HashSet<>();

        // this is queue for the BFS, initialize it with 0,0 position
        Queue<Node> q = new LinkedList<>();
        Node initial = new Node(0, 0);
        // q.add(0);
        q.add(initial);

        // this will store the number of moves
        int res = 0;

        // Start BFS
        while (!q.isEmpty()) {

            // On each step we only poll number of cells that we have in the queue now.
            // everything added after this will be counted at the next step
            int size = q.size();

            for (int i = 0; i < size; i++) { // Level Order Traversal
                // Get the encoded num, convert it to coordinated and check if it's our target
                Node next = q.poll(); // Retrieves and removes the head of the queue.
                int curX = next.x, curY = next.y;
                if (curX == x && curY == y) {
                    return res;
                }

                // If not the target - make all possible moves
                for (int k = 0; k < d.length; k++) {
                    //each next move
                    int x1 = curX + d[k][0], y1 = curY + d[k][1];
                    // Encode the move to store it in the set of visited cells
                    Node current = new Node(x1, y1);

                    // Why check for >= -2 and >= -2 ?
                    if (x1 >= -2 && y1 >= -2 && seen.add(current)) { // Can we use seen.contains(current) here ?
                        q.add(current);
                    }
                }
            }
            // After we done with all moves from all points stored in the queue at the beginning -
            // Increment the step counter
            res++;
        }

        return -1;
    }

    /* This piece of code leads to Output Limit Exceeded. Why ?
    boolean containsNode = false;
    for(Node n : seen){
        if(n.x == current.x && n.y == current.y){
            containsNode = true; break;
        }
    }

    if (x1 >= -2 && y1 >= -2 && !containsNode) {
        q.add(current);
    }
     */

    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
