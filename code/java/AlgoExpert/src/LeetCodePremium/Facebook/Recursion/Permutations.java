package LeetCodePremium.Facebook.Recursion;

import java.util.*;

/*
Given an array nums of distinct integers, return all the possible permutations.
You can return the answer in any order.

Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */
public class Permutations {

    // Heap's Algorithm.
    public void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int first) {
        // if all integers are used up
        if (first == n) {
            output.add(new ArrayList<Integer>(nums));
        }
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums) {
            nums_lst.add(num);
        }

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }

    // Solution 2: Backtracking
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        generateAllPermutations(new ArrayList<>(), nums, allPermutations);
        return allPermutations;
    }

    private void generateAllPermutations(List<Integer> runningChoices, int[] originalArray, List<List<Integer>> allPermutations) {
        if (runningChoices.size() == originalArray.length) {
            allPermutations.add(new ArrayList<>(runningChoices));
            return;
        }

    /*
      Every stack frame of this function call represents the expression of trying (almost) all items in every "slot" in the array.
      The recursion stops when we are choosing on 1 past the final "slot".
    */
        for (int i = 0; i < originalArray.length; i++) {
            int choice = originalArray[i];

            // Skip if element already exists in 'runningChoices'
            if (runningChoices.contains(choice)) { //
                continue;
            }

            // 1.) Choose - Add the item to the 'runningChoices'
            runningChoices.add(choice);

            // 2.) Explore - Recurse on the choice
            generateAllPermutations(runningChoices, originalArray, allPermutations);

            // 3.) Unchoose - We have returned from the recursion, remove the choice we made.
            // The next iteration will try another item in the "slot" we are working on.
            runningChoices.remove(runningChoices.size() - 1);
        }
    }
}
