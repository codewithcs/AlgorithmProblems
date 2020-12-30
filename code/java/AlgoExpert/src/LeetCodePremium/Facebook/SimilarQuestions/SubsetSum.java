package LeetCodePremium.Facebook.SimilarQuestions;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of non negative integers and a total, is there subset of numbers in this array which adds up to given total.
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 7, 8, 10 }; // n: number of elements in this array
        int target = 11; // k

        boolean[][] result = new boolean[arr.length+1][target+1];
        for(int i=0; i< result[0].length; i++){
            result[0][i] = false;
        }
        for(int i=0; i< result.length; i++){
            result[i][0] = true; // Don't pick anything for sum = 0.
        }
        List<Integer> answer = new ArrayList<>();
        System.out.println(checkSum(arr, target, result));

        boolean[] even = new boolean[target+1]; // by default false values.
        boolean[] odd = new boolean[target+1]; even[0] = true; odd[0] = true;
        System.out.println(checkSum2(arr, target, even, odd));

        formSolution(result, answer, arr, target);
        System.out.println("answer is : " + answer);
    }

    // O(n*k) time and space.
    public static boolean checkSum(int[] arr, int target, boolean[][] result){
        for(int i=1; i< result.length ; i++){
            for(int j=1; j< result[0].length ; j++){
                if(arr[i-1] > j){ // We only have the option of not taking this element.
                    result[i][j] = result[i-1][j];
                } else { // We can either take this element or don't
                    result[i][j] = result[i-1][j] || result[i-1][j-arr[i-1]];
                }
            }
        }
        return result[result.length-1][target];
    }

    // Can do better in O(2*k) space and O(n*k) time. But we won't be able to create the solution.
    public static boolean checkSum2(int[] arr, int target, boolean[] even, boolean[] odd){
        for(int i=1; i < arr.length+1; i++){
            // Initialize the array to be filled in the next iteration.
            if(i%2 == 1){
                odd = new boolean[target+1]; odd[0] = true;
            } else{
                even = new boolean[target+1]; even[0] = true;
            }

            for(int j=1; j < odd.length ; j++){
                if(i%2 == 1){
                    if(arr[i-1] > j){
                        odd[j] = even[j];
                    } else {
                        odd[j] = even[j] || even[j-arr[i-1]];
                    }
                } else{
                    if(arr[i-1] > j){
                        even[j] = odd[j];
                    } else {
                        even[j] = odd[j] || odd[j-arr[i-1]];
                    }
                }
            }
        }
        return arr.length % 2 == 1 ?  odd[target] : even[target];
    }

    // Forming a solution makes sense when the final result is True.
    // First we check if we can form the solution without including the current array element.
    // If not, then it is guaranteed that we will have to include this element.
    public static void formSolution(boolean[][] result, List<Integer> answer, int[] arr, int target){
        int i= target;
        System.out.println("result[5][11] is : " + result[5][11]);
        System.out.println("result[4][11] is : " + result[4][11]);
        for(int j = arr.length; j > 0 && i!= 0; j--){
            if(arr[j-1] != i){
                System.out.println("result[" + (j-1)+ "][" + i+ "] is : " + result[j-1][i]);
                while(result[j-1][i]){
                    j--;
                }
            }
            answer.add(arr[j-1]);
            i = i - arr[j-1];
        }
    }
}