package LeetCodePremium.Facebook.ArrayAndString;

import java.util.HashMap;

/*
Given an array of integers nums and an integer k, return the total number of
continuous subarrays whose sum equals to k.

Constraints:
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
public class SubArraySumEqualsK {

    // Brute Force: O(n^3) time and O(1) space.
    // Considering every possible subarray takes O(n^2) time. For each of the subarray
    // we calculate the sum taking O(n) time in the worst case, taking total of O(n^3) time.
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Approach 2: Using cumulative sum. O(n^2) time and O(n) space.
    // Considering every possible subarray takes O(n^2) time.
    // Finding out the sum of any subarray takes O(1) time after the initial
    // processing of O(n) for creating the cumulative sum array.
    // Space: O(n), cumulative space fo size n+1 is used.
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Approach 3: Without Space, O(n^2) time and O(1) space.
    /* Instead of considering all the start and end points and then finding the sum for each subarray corresponding to those points,
    we can directly find the sum on the go while considering different end points. i.e.
    We can choose a particular start point and while iterating over the end points, we can add the element corresponding to the
    end point to the sum formed till now.
    Whenever the sum equals the required k value, we can update the count value.
    We do so while iterating over all the end indices possible for every start index.
    Whenever, we update the start index, we need to reset the sum value to 0.
     */
    public int subarraySum3(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum=0;
            for (int end = start; end < nums.length; end++) {
                sum+=nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Using a Map. O(n) time and O(n) space.
    public int subarraySum4(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<> ();
        map.put(0, 1); // Important initialization.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
