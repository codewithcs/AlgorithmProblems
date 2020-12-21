package LeetCodePremium.Facebook.DP;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubArraySum {
/*
Given a list of non-negative numbers and a target integer k, write a function
to check if the array has a continuous subarray of size at least 2
that sums up to a multiple of k, that is, sums up to n*k where n is also an integer

Constraints:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
*/
    // O(n) time and O(min(n, k)) space, n: size of the nums array.


    // O(n^3) time and O(1) space.
    public boolean checkSubarraySum3(int[] nums, int k) {
        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += nums[i];
                }
                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(n) time and O(min(n, k)) space.
    public static boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap <Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // To handle the case sum = nk which could lead to a subarray starting at index 0.
        // Since sum % k would be zero, that is why we choose the key to be 0.
        // To handle the case when the subarray which starts at index 0 is our answer.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k; //
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    // O(n^2) time and O(n) space.
    public boolean checkSubarraySum2(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int summ = sum[end] - sum[start] + nums[start];
                if (summ == k || (k != 0 && summ % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0};
        int k = 0;

        System.out.println(checkSubarraySum4(nums, k));
    }

    public static boolean checkSubarraySum4(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        for(int i=0; i< nums.length ; i++){
            sum += nums[i];

            if(sum == 0 && k== 0){ // Special Case
                if(i > 0) return true;
            } else if(k != 0){
                sum = sum % k;
                if(sum == 0){ // subarray starting at index 0
                    if(i > 0){
                        return true;
                    }
                }
            }

            if(map.containsKey(sum)){
                if(i - map.get(sum) > 1){
                    return true;
                }
            } else{
                map.put(sum, i);
            }
        }
        return false;
    }
}
