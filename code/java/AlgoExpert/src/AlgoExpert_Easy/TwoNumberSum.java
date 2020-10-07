package AlgoExpert_Easy;

import java.util.HashSet;
import java.util.Set;

public class TwoNumberSum {

    // O(n) time | O(n) space, very important approach.
    public static int[] twoNumberSum ( int[] array, int targetSum ) {
        Set<Integer> nums = new HashSet<>();
        for(int num: array){
            int potentialMatch = targetSum - num;

            if(nums.contains(potentialMatch)){
                return new int[] {potentialMatch, num};
            } else {
                nums.add(num);
            }
        }

        return new int[0];
    }

}
