package AlgoExpert_VeryHard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfBinaryTreeTopologies {

    public static int numberOfBinaryTreeTopologies(int n){
        if(n == 0){
            return 1;
        }
        int numberOfTrees = 0;

        for(int leftTreeSize = 0; leftTreeSize < n ; leftTreeSize++){
            int rightTreeSize = n-1-leftTreeSize;
            int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize);
            int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize);
            numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
        }

        return numberOfTrees;
    }


    // AlgoExpert solution 2: O(n^2) time | O(n) space
    public static int numberOfBinaryTreeTopologies2(int n){
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        return numberOfBinaryTreeTopologies2(n, cache);
    }

    // Top Down approach: From top, Reach till the base cases, fill the cache and then backtrack.
    public static int numberOfBinaryTreeTopologies2(int n, Map<Integer, Integer> cache){
        if(cache.containsKey(n)){
            return cache.get(n);
        }
        int numberOfTrees = 0;

        for(int leftTreeSize = 0; leftTreeSize < n ; leftTreeSize++){
            int rightTreeSize = n-1-leftTreeSize;
            int numberOfLeftTrees = numberOfBinaryTreeTopologies2(leftTreeSize);
            int numberOfRightTrees = numberOfBinaryTreeTopologies2(rightTreeSize);
            numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
        }

        cache.put(n, numberOfTrees);
        return numberOfTrees;
    }

    // AlgoExpert solution 3: O(n^2) time | O(n) space
    // Bottom Up Approach
    public static int numberOfBinaryTreeTopologies3(int n){
        List<Integer> cache = new ArrayList<>(); // can also use a map, but using a list is a bit clever as making use of indices as the number of nodes.
        cache.add(1);

        for(int m=1; m < n+1 ; m++){ // total number of nodes in the tree.
            int numberOfTrees = 0;
            for(int leftTreeSize = 0; leftTreeSize < m ; leftTreeSize++){
                int rightTreeSize = m-1-leftTreeSize;
                int numberOfLeftTrees = cache.get(leftTreeSize);
                int numberOfRightTrees = cache.get(rightTreeSize);
                numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
            }
            cache.add(numberOfTrees);
        }
        return cache.get(n);
    }
}