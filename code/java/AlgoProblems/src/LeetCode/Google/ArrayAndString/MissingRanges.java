package LeetCode.Google.ArrayAndString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
You are given an inclusive range [lower, upper] and a sorted
unique integer array nums, where all elements are in the inclusive range.

A number x is considered missing if x is in the range [lower, upper]
and x is not in nums.

Return the smallest sorted list of ranges that cover every missing number exactly.
That is, no element of nums is in any of the ranges, and each
missing number is in one of the ranges.

Each range [a,b] in the list should be output as:
"a->b" if a != b
"a" if a == b

Constraints:
-10^9 <= lower <= upper <= 10^9
0 <= nums.length <= 100
lower <= nums[i] <= upper
All the values of nums are unique.
 */

public class MissingRanges {

    // O(n) time
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();

        if(nums.length == 0){
            if(lower == upper){
                list.add(String.valueOf(lower));
            } else {
                list.add(lower + "->" + upper);
            }
            return list;
        }

        int diff = nums[0] - lower;

        if(diff ==1 ){
            list.add(String.valueOf(lower));
        } else if(diff > 1){
            list.add(String.valueOf(lower) + "->" + String.valueOf(nums[0] - 1));
        }

        for(int i=0; i<nums.length-1 ; i++){
            int first = nums[i];
            int second = nums[i+1];
            String s = generateRange(first, second);
            if(s.length() > 0){
                list.add(s);
            }
        }

        diff = upper - nums[nums.length-1];
        if(diff == 1){
            list.add(String.valueOf(upper));
        } else if(diff >1){
            list.add( (nums[nums.length-1] + 1) + "->" + upper );
        }

        return list;
    }

    public String generateRange(int first, int second){
        if(first == second || second-first==1) {
            return "";
        }

        if(second-first==2){
            return "" + (first+1);
        }

        return (first+1) + "->" + (second-1);
    }

    // Approach 2: Perfect example of how to write clean code.
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        int n = nums.length;

        if (n == 0) {
            return Collections.singletonList(formatRange(lower, upper));
        }

        List<String> missingRanges = new ArrayList<>();

        // Edge case 1) Missing ranges at the beginning
        if (nums[0] > lower) {
            missingRanges.add(formatRange(lower, nums[0] - 1));
        }

        // Missing ranges between array elements
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > 1) {
                missingRanges.add(formatRange(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        // Edge case 2) Missing ranges at the end
        if (nums[n - 1] < upper) {
            missingRanges.add(formatRange(nums[n - 1] + 1, upper));
        }

        return missingRanges;
    }

    // formats range in the requested format
    String formatRange(int lower, int upper) {
        if (lower == upper) {
            return String.valueOf(lower);
        } else {
            return lower + "->" + upper;
        }
    }

    public List<String> findMissingRanges3(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        int n = nums.length;

        if(n==0){
            list.add(generateRange2(lower, upper));
            return list;
        }

        if(nums[0] > lower){
            list.add(generateRange2(lower, nums[0]-1));
        }

        for(int i=0; i<nums.length-1 ; i++){
            int first = nums[i]+1;
            int second = nums[i+1]-1;
            String s = generateRange2(first, second);
            if(s.length() > 0){
                list.add(s);
            }
        }

        if(nums[n-1] < upper){
            list.add(generateRange2(nums[n-1]+1, upper));
        }

        return list;
    }

    public String generateRange2(int first, int second){
        if(first>second){
            return "";
        }
        if(first == second){
            return String.valueOf(first);
        } else {
            return (first) + "->" + (second);
        }
    }
}
