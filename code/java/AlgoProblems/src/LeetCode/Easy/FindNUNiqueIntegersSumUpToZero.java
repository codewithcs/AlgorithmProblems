package LeetCode.Easy;
/*
Given an integer n, return any array
containing n unique integers such that they add up to 0.
 */

public class FindNUNiqueIntegersSumUpToZero {
    public int[] sumZero(int n) {
        int[] result = new int[n];

        for(int i=0; i<= n/2 -1; i++){
            result[i] = i+1;
        }

        for(int i=result.length-1; i>n/2-1 ; i--){
            result[i] = -result[result.length-i-1];
        }

        return result;
    }
}
