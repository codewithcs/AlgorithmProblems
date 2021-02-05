package LeetCodePremium.Easy;

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
}
