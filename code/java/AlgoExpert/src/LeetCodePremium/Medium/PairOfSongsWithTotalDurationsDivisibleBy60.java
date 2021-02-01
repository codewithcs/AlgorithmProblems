package LeetCodePremium.Medium;

/*
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their
total duration in seconds is divisible by 60.

Formally, we want the number of indices i, j such that i < j
with (time[i] + time[j]) % 60 == 0.

Constraints:
1 <= time.length <= 6 * 10^4
1 <= time[i] <= 500
 */

import java.util.HashMap;
import java.util.Map;

public class PairOfSongsWithTotalDurationsDivisibleBy60 {
    // Output Limit Exceeded.
    public int numPairsDivisibleBy60(int[] time) {
        int pairs = 0;
        for(int i=0; i< time.length-1; i++){
            for(int j=i+1; j< time.length; j++){
                if( (time[i] + time[j]) % 60 == 0){
                    pairs++;
                }
            }
        }
        return pairs;
    }

    // Based on 2-Sum logic.
    public int numPairsDivisibleBy60_2(int[] time) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : time) {
            if(t % 60 == 0) {
                count += map.getOrDefault(0, 0);
            } else {
                count += map.getOrDefault(60 - t % 60, 0);
            }

            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }

        return count;
    }

    public int numPairsDivisibleBy60_3(int[] time) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : time) {

            // Special Case: When t is 60. First do the search, then add to the hashmap.
            if(t % 60 == 0){
                if(map.containsKey(0)){
                    count += map.get(0);
                }
            } else {
                int numberToSearch = 60 - t % 60;
                if(map.containsKey(numberToSearch)){
                    count = count + map.get(numberToSearch);
                }
            }

            if(!map.containsKey(t % 60)){
                map.put(t % 60, 1);
            } else {
                map.put(t % 60, map.get(t % 60) + 1);
            }
        }

        return count;
    }

    public int numPairsDivisibleBy60_4(int[] time) {
        int array[] = new int[60];
        int retVal = 0;
        for(int i : time) {
            int n = i % 60;
            retVal += array[n==0 ? 0 : 60-n];
            array[n]++;
        }
        return retVal;
    }
}
