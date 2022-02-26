package practice_Amazon;

import java.util.Arrays;

public class ProuctOfArrayExceptSelf {

    /*
    Given an integer array nums, return an array answer such that answer[i] is equal to the
    product of all the elements of nums except nums[i].

    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

    You must write an algorithm that runs in O(n) time and without using the division operation.

    Thought Process:


     */

    public int[] productExceptSelf(int[] nums) {
        int[] leftAnswer = new int[nums.length];
        Arrays.fill(leftAnswer, 1);

        int[] rightAnswer = new int[nums.length];
        Arrays.fill(rightAnswer, 1);

        for(int i=1; i< nums.length ; i++){
            leftAnswer[i] *= leftAnswer[i-1] * nums[i-1];
        }

        for(int i=nums.length-2; i>=0; i--){
            rightAnswer[i] *=  rightAnswer[i+1] * nums[i+1];
        }

        int[] answer = new int[nums.length];
        for(int i=0; i< answer.length; i++){
            answer[i] = leftAnswer[i] * rightAnswer[i];
        }

        return answer;
    }

    public int[] productExceptSelf3(int[] nums) {
        int[] leftProducts = new int[nums.length];
        Arrays.fill(leftProducts, 1);
        int prod = 1 ;

        for(int i=1; i< nums.length; i++){
            prod *= nums[i-1];
            leftProducts[i] = prod ;
        }

        prod =1;

        for(int i=nums.length-2; i>=0 ; i--){
            prod *= nums[i+1];
            leftProducts[i] *= prod ;
        }

        return leftProducts;
    }


    // O(n^2) time, Time Limit Exceeded. 18/20 test cases passed.
    public int[] productExceptSelf2(int[] nums) {
        int[] answer = new int[nums.length];
        Arrays.fill(answer, 1);

        for(int i=0; i< nums.length; i++){
            for(int j=i+1; j< nums.length; j++){
                answer[i] *= nums[j];
            }
        }

        for(int i=nums.length-1; i>=0; i--){
            for(int j=i-1; j>=0; j--){
                answer[i] *= nums[j];
            }
        }

        return answer;
    }
}
