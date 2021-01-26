package LeetCodePremium.Medium;

/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.
(A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the ith domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same,
or all the values in B are the same.

If it cannot be done, return -1.

Constraints:
2 <= A.length == B.length <= 2 * 10^4
1 <= A[i], B[i] <= 6
 */

import java.util.HashMap;
import java.util.Map;

public class MinimumDominoRotationsForEqualRow {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 6};
        int[] B = {6, 6, 6, 6, 5};

        System.out.println(minDominoRotations(A, B));
    }
    public static int minDominoRotations(int[] A, int[] B) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        int length = A.length;
        int swaps = 0;

        for(int i=0; i< length; i++){
            if(!map1.containsKey(A[i])){
                map1.put(A[i], 0);
            }
            map1.put(A[i], map1.get(A[i]) + 1);

//            if(!map1.containsKey(B[i])){
//                map1.put(B[i], 0);
//            }
        }

        for(int i=0; i< length; i++){
            if(!map2.containsKey(B[i])){
                map2.put(B[i], 0);
            }
            map2.put(B[i], map2.get(B[i]) + 1);
        }

        for(int i=0; i< length ; i++){
            if(A[i] != B[i]){
                int max = map1.get(A[i]) > map2.get(B[i]) ? A[i] : B[i];

                if(map1.containsKey(max) && map2.containsKey(max)){
                    if( (map1.get(max) > map2.get(max) && max == B[i] )  || map1.get(max) < map2.get(max) && max == A[i] ){
                        // Update hash map
                        map1.put(A[i], map1.get(A[i]) - 1);
                        map2.put(B[i], map2.get(B[i]) - 1);

                        if(!map1.containsKey(B[i])){
                            map1.put(B[i], 1);
                        } else {
                            map1.put(B[i], map1.get(B[i]) + 1);
                        }

                        if(!map2.containsKey(A[i])){
                            map2.put(A[i], 1);
                        } else {
                            map2.put(A[i], map2.get(A[i]) + 1);
                        }

                        int temp = A[i];
                        A[i] = B[i];
                        B[i] = temp;
                        swaps ++;
                    }
                }

            }
        }

        int num = A[0]; boolean equal = true;
        for(int i=1; i< A.length ; i++){
            if(num != A[i]){
                equal = false;
                break;
            }
        }

        if(equal){
            return swaps >= 0 ? swaps : -1;
        }

        equal = true; num = B[0];
        for(int i=1; i< B.length ; i++){
            if(num != B[i]){
                equal = false;
                break;
            }
        }

        return equal && swaps >= 0 ? swaps : -1;
    }

    // Add elements of B in map1 which do not exist in A.
    public static int minDominoRotations2(int[] A, int[] B) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        int length = A.length;
        int swaps = 0;

        for(int i=0; i< length; i++){
            if(!map1.containsKey(A[i])){
                map1.put(A[i], 0);
            }
            map1.put(A[i], map1.get(A[i]) + 1);

//            if(!map1.containsKey(B[i])){
//                map1.put(B[i], 0);
//            }
        }

        for(int i=0; i< length; i++){
            if(!map2.containsKey(B[i])){
                map2.put(B[i], 0);
            }
            map2.put(B[i], map2.get(B[i]) + 1);
        }

        for(int i=0; i< length ; i++){
            if(A[i] != B[i]){
                int max = map1.get(A[i]) > map2.get(B[i]) ? A[i] : B[i];

                if(map1.containsKey(max) && map2.containsKey(max)){
                    if( (map1.get(max) > map2.get(max) && max == B[i] )  || map1.get(max) < map2.get(max) && max == A[i] ){
                        // Update hash map
                        map1.put(A[i], map1.get(A[i]) - 1);
                        map2.put(B[i], map2.get(B[i]) - 1);

                        if(!map1.containsKey(B[i])){
                            map1.put(B[i], 1);
                        } else {
                            map1.put(B[i], map1.get(B[i]) + 1);
                        }

                        if(!map2.containsKey(A[i])){
                            map2.put(A[i], 1);
                        } else {
                            map2.put(A[i], map2.get(A[i]) + 1);
                        }

                        int temp = A[i];
                        A[i] = B[i];
                        B[i] = temp;
                        swaps ++;
                    }
                }

            }
        }

        int num = A[0]; boolean equal = true;
        for(int i=1; i< A.length ; i++){
            if(num != A[i]){
                equal = false;
                break;
            }
        }

        if(equal){
            return swaps >= 0 ? swaps : -1;
        }

        equal = true; num = B[0];
        for(int i=1; i< B.length ; i++){
            if(num != B[i]){
                equal = false;
                break;
            }
        }

        return equal && swaps >= 0 ? swaps : -1;
    }
}
