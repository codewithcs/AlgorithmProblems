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
}
