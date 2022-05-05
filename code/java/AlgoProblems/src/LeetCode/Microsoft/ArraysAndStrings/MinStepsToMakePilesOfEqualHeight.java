package LeetCode.Microsoft.ArraysAndStrings;

/*
Alexa is given n piles of equal or unequal heights.
In one step, Alexa can remove any number of boxes from the pile which has
the maximum height and try to make it equal to the one which is just lower than
the maximum height of the stack.

Determine the minimum number of steps required to
make all of the piles equal in height.
 */

import java.util.Collections;
import java.util.List;

/*
The final answer will not depend on the ordering of the piles.
We are interested in the maximum and 2nd maximum pile height.
 */
public class MinStepsToMakePilesOfEqualHeight {
    public static int minSteps(List<Integer> nums) {
        int nums_size = nums.size();

        if (nums_size < 2) {
            return 0;
        }

        Collections.sort(nums); // Sort in ascending order.
        int sum = 0;

        for (int i = 1; i < nums_size; ++i) {
            if (nums.get(nums_size - i - 1) != nums.get(nums_size - i)) {
                sum += i;
            }
        }

        return sum;
    }
}
