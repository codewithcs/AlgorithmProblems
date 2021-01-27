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

    // This also works now by adding equality. O(n) time and O(1) space.
    // Like a Greedy approach. Can use a 1d arrays of size 7 instead of hashmap.
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

        }

        for(int i=0; i< length; i++){
            if(!map2.containsKey(B[i])){
                map2.put(B[i], 0);
            }
            map2.put(B[i], map2.get(B[i]) + 1);
        }

        for(int i=0; i< length ; i++){
            if(A[i] != B[i]){
                int max = map1.get(A[i]) >= map2.get(B[i]) ? A[i] : B[i];

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
        }

        for(int i=0; i< length ; i++){ // Adding this logic to prevent key checks on hashmap.
            if(!map1.containsKey(B[i])){
                map1.put(B[i], 0);
            }
        }

        for(int i=0; i< length; i++){
            if(!map2.containsKey(B[i])){
                map2.put(B[i], 0);
            }
            map2.put(B[i], map2.get(B[i]) + 1);
        }

        for(int i=0; i< length ; i++){
            if(!map2.containsKey(A[i])){
                map2.put(A[i], 0);
            }
        }


        for(int i=0; i< length ; i++){
            if(A[i] != B[i]){
                int max = map1.get(A[i]) >= map2.get(B[i]) ? A[i] : B[i]; // Added equality here.

                    if( (map1.get(max) > map2.get(max) && max == B[i] )  || map1.get(max) < map2.get(max) && max == A[i] ){
                        // Update hash map
                        map1.put(A[i], map1.get(A[i]) - 1);
                        map2.put(B[i], map2.get(B[i]) - 1);

                        map1.put(B[i], map1.get(B[i]) + 1);
                        map2.put(A[i], map2.get(A[i]) + 1);

                        // Important to do the swap after updating hash maps.
                        int temp = A[i];
                        A[i] = B[i];
                        B[i] = temp;
                        swaps ++;
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

    public int minDominoRotations3 (int[] A, int[] B) {
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] countSame = new int[7];

        for (int i = 0; i < A.length; i++) {
            ++countA[A[i]];
            ++countB[B[i]];
            if (A[i] == B[i]) {
                ++countSame[A[i]];
            }
        }

        int ans =  A.length;

        // If there exists a solution, the equal values will be either A[0] or B[0].
        for (int i = 1; i <= 6; i++) {
            if (countA[i] + countB[i] - countSame[i] == A.length) {
                return Math.min (countA[i] - countSame[i], countB[i] - countSame[i]);
            }
        }

        return -1;
    }

    /*
      Return min number of rotations
      if one could make all elements in A or B equal to x.
      Else return -1.
    */

    public int check(int x, int[] A, int[] B, int n) {
        // how many rotations should be done
        // to have all elements in A equal to x
        // and to have all elements in B equal to x
        int rotations_a = 0, rotations_b = 0;

        for (int i = 0; i < n; i++) {
            // rotations coudn't be done
            if (A[i] != x && B[i] != x) {
                return -1;
            }
                // A[i] != x and B[i] == x
            else if (A[i] != x) {
                rotations_a++;
            }
                // A[i] == x and B[i] != x
            else if (B[i] != x) {
                rotations_b++;
            }
        }

        // Min number of rotations to have all
        // elements equal to x in A or B
        return Math.min(rotations_a, rotations_b);
    }

    public int minDominoRotations4(int[] A, int[] B) {
        int n = A.length;
        int rotations = check(A[0], B, A, n);
        // If one could make all elements in A or B equal to A[0]
        if (rotations != -1 || A[0] == B[0]) {
            return rotations;
        }
        // If one could make all elements in A or B equal to B[0]
        else {
            return check(B[0], B, A, n);
        }
    }


    // Simplest Solution, 4 Cases, O(n) time and O(1) space.
    public int minDominoRotations5(int[] A, int[] B) {
        int swaps1 = helper(A[0], A, B);
        int swaps2 = helper(B[0], A, B);
        if(swaps1 == -1) return swaps2;
        if(swaps2 == -1) return swaps1;
        return Math.min(swaps1, swaps2);
    }

    public int helper(int startValue, int[] A, int[] B){
        int swaps1 = 0; int swaps2 = 0;

        for(int i=0; i< A.length; i++){
            if(A[i] != startValue){
                if(B[i] != startValue){
                    swaps1 = -1;
                    break;
                } else {
                    swaps1++;
                }
            }
        }

        for(int i=0; i< B.length; i++){
            if(B[i] != startValue){
                if(A[i] != startValue){
                    swaps2 = -1;
                    break;
                } else {
                    swaps2++;
                }
            }
        }

        if(swaps1 == -1) return swaps2;
        if(swaps2 == -1) return swaps1;
        return Math.min(swaps1, swaps2);
    }


    // Using 1d arrays for storing counts instead of HashMap.
    public int minDominoRotations6(int[] A, int[] B) {
        int[] countA = new int[7];
        int[] countB = new int[7];

        for (int i = 0; i < A.length; i++) {
            ++countA[A[i]];
            ++countB[B[i]];
        }

        int length = A.length;
        int swaps = 0;

        for(int i=0; i< length ; i++){
            if(A[i] != B[i]){
                int max = countA[A[i]] >= countB[B[i]] ? A[i] : B[i]; // Added equality here.

                if( (countA[max] > countB[max] && max == B[i] )  || countA[max] < countB[max] && max == A[i] ){
                    // Update Arrays
                    countA[A[i]]--;
                    countB[B[i]]--;

                    countA[B[i]]++;
                    countB[A[i]]++;

                    // Important to do the swap after updating hash maps.
                    int temp = A[i];
                    A[i] = B[i];
                    B[i] = temp;
                    swaps ++;
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
