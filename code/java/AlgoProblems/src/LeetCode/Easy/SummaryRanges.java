package LeetCode.Easy;

import java.util.ArrayList;
import java.util.List;

/*
You are given a sorted unique integer array nums.

Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
That is, each element of nums is covered by exactly one of the ranges, and there is no
integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b

Constraints:
0 <= nums.length <= 20
-2^31 <= nums[i] <= 2^31 - 1
All the values of nums are unique.
nums is sorted in ascending order.
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        if(nums.length == 0){
            return result;
        }

        String current = "" + nums[0];
        int currentLength = 1; // chain length.

        for(int i=1; i< nums.length ;i++){
            if(nums[i] == nums[i-1] + 1){
                currentLength++;
            } else {
                if(currentLength > 1){
                    result.add(current + "->" + nums[i-1]);
                } else {
                    result.add(current);
                }
                current = "" + nums[i];
                currentLength = 1;
            }
        }

        if(currentLength == 1){
            result.add(current);
        } else if(currentLength > 1){
            result.add(current + "->" + nums[nums.length-1]);
        }

        return result;
    }


    /// Using String Builder
    public List<String> summaryRanges2(int[] nums) {
        List<String> result = new ArrayList<>();

        if(nums.length == 0){
            return result;
        }

        StringBuilder current = new StringBuilder("" + nums[0]);
        int currentLength = 1; // chain length.

        for(int i=1; i< nums.length ;i++){
            if(nums[i] == nums[i-1] + 1){
                currentLength++;
            } else {
                if(currentLength > 1){
                    result.add(current.append("->").append(nums[i - 1]).toString());
                } else {
                    result.add(current.toString());
                }
                current = new StringBuilder("" + nums[i]);
                currentLength = 1;
            }
        }

        if(currentLength == 1){
            result.add(current.toString());
        } else if(currentLength > 1){
            result.add(current.append("->").append(nums[nums.length - 1]).toString());
        }

        return result;
    }

    // Nice Solution:
    public List<String> summaryRanges3(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i, j = 0; j < nums.length; ++j){
            i = j;
            // try to extend the range [nums[i], nums[j]]
            while (j + 1 < nums.length && nums[j + 1] == nums[j] + 1) {
                ++j;
            }
            // put the range into the list
            if (i == j) {
                summary.add(nums[i] + "");
            } else {
                summary.add(nums[i] + "->" + nums[j]);
            }
        }
        return summary;
    }
}
