package LeetCodePremium.Facebook.SimilarQuestions;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 7, 8, 10 };
        int target = 11;

        boolean[][] result = new boolean[arr.length+1][target+1];
        for(int i=0; i< result[0].length; i++){
            result[0][i] = false;
        }
        for(int i=0; i< result.length; i++){
            result[i][0] = true; // don't pick anything for sum = 0.
        }
        List<Integer> answer = new ArrayList<>();
        System.out.println(checkSum(arr, target, result, answer));
        System.out.println("answer is : " + answer);
    }

    public static boolean checkSum(int[] arr, int target, boolean[][] result, List<Integer> answer){
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
}
