package LeetCode.Medium;

/*
There are n cities. Some of them are connected, while some are not.
If city a is connected directly with city b, and city b is connected directly with city c,
then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and
no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the
jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Constraints:
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */

public class NumberOfProvinces {

    // O(n^2) time and O(n) space using both DFS and BFS.
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length ;

        int count = 0;

        for(int i=0; i< n ; i++){
            count += helper(i, n, isConnected);
        }

        return count;
    }

    public int helper(int startingVertex, int n, int[][] isConnected){
        if(isConnected[startingVertex][startingVertex] == -1){
            return 0;
        }

        isConnected[startingVertex][startingVertex] = -1;

        for(int k=0; k< n ; k++){ // Iterate over the neighbors.
            if(isConnected[k][k] != -1 && isConnected[startingVertex][k] == 1){ // Neighbor has not been visited and there is an edge.
                helper(k, n, isConnected);
            }
        }

        return 1;
    }

    // Approach 3: Union-Find, O(n^3) time and O(n) space.
}
