package LeetCode.Facebook.Recursion;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer array nums, return all possible subsets (the power set).
The solution set must not contain duplicate subsets.

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
public class Subsets {
    // All three approaches: O(N*2^N) time and space.
    // Backtracking:
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); result.add(new ArrayList<Integer>());
        for(int i=1; i<=nums.length; i++){
            generateSubset(result, nums, i, new ArrayList<>(), 0);
        }
        return result;
    }

    public void generateSubset(List<List<Integer>> result, int[] nums, int k, List<Integer> current, int index){
        if(current.size() == k){
            result.add(new ArrayList<>(current));
            return ; // Optimization
        }

        for(int i=index; i<nums.length; i++){
            if(!current.contains(nums[i])){
                current.add(nums[i]);
                generateSubset(result, nums, k, current, i+1);
                current.remove(current.size()-1);
            }
        }
    }

    // Cascading
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        subsets3(new int[]{1, 2, 3});
    }

    // Lexicographic (Binary Sorted) Subsets.
    public static List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;

        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            System.out.println("i is : " + i);
            System.out.println("Integer.toBinaryString(i) is : " + Integer.toBinaryString(i));
            String bitmask = Integer.toBinaryString(i).substring(1);
            System.out.println("bitmask is : " + bitmask);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') {
                    curr.add(nums[j]);
                }
            }
            output.add(curr);
        }
        return output;
    }
}
