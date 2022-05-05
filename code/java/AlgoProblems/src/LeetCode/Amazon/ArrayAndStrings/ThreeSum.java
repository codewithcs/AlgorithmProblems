package LeetCode.Amazon.ArrayAndStrings;

import java.util.*;

// target sum = 0;
public class ThreeSum {
// The numbers are not necessarily distinct. That is why we need a hash map.

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{0, 0, 0}));
    }

    // Time Limit Exceeded.
    public static List<List<Integer>> threeSum(int[] nums) {
        Map<List<Integer>, Integer> map = new HashMap<>();

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    System.out.println("sum is :" + sum);
                    map.put(Arrays.asList(nums[i], nums[left], nums[right]), 0);
                    left++;
                    right--;
                }

            }

        }

        for (List<Integer> list1 : map.keySet()) {
            list.add(list1);
        }

        return list;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) // Nice  optimization: nums[i] <= 0
            if (i == 0 || nums[i - 1] != nums[i]) { // Handling duplicates.
                twoSum(nums, i, res);
            }
        return res;
    }

    public static void twoSum(int[] nums, int i, List<List<Integer>> res) {
        Set<Integer> seen = new HashSet<Integer>(); // Adding the logic of 2 sum problem using a set.
        // Have to re-initialize the set for each call to twoSum.

        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) // Another good optimization.
                    ++j;
            }
            seen.add(nums[j]);
        }
    }


    /// 2 pointer approach:
    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {// Or nums[i] <= target if some other target is given.
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        }
        return res;
    }

    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int left = i + 1, right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if (sum < 0) {
                ++left;
            } else if (sum > 0) {
                --right;
            } else {
                res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                while (left < right && nums[left] == nums[left - 1]) { // Another good optimization.
                    ++left;
                }
            }
        }
    }

    // No-Sort approach:
    public List<List<Integer>> threeSum4(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }


}