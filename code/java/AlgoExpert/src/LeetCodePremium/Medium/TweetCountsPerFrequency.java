package LeetCodePremium.Medium;

/*
Implement the class TweetCounts that supports two methods:

1. recordTweet(string tweetName, int time)
Stores the tweetName at the recorded time (in seconds).

2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)
Returns the total number of occurrences for the given tweetName per minute, hour, or day (depending on freq)
starting from the startTime (in seconds) and ending at the endTime (in seconds).

freq is always minute, hour or day, representing the time interval to get the total number of occurrences for the given tweetName.

The first time interval always starts from the startTime, so the time intervals are [startTime, startTime + delta*1>,
[startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)>
for some non-negative number i and delta (which depends on freq).

Constraints:
There will be at most 10000 operations considering both
recordTweet and getTweetCountsPerFrequency.
0 <= time, startTime, endTime <= 10^9
0 <= endTime - startTime <= 10^4
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetCountsPerFrequency {

    class TweetCounts {

        Map<String, List<Integer>> map;
        public TweetCounts() {
            map = new HashMap<>();
        }

        // No need to sort List<Integer> as we are mapping times to buckets. 
        public void recordTweet(String tweetName, int time) {
            if(!map.containsKey(tweetName)){
                map.put(tweetName, new ArrayList<>());
            }
            map.get(tweetName).add(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            int frequency = -1;

            if(freq.equals("minute")){
                frequency = 60;
            } else if(freq.equals("hour")){
                frequency = 3600;
            } else if(freq.equals("day")){
                frequency = 24 * 3600;
            }

            int lastIndex = (endTime-startTime)/frequency; // most important step.
            int[] result = new int[lastIndex+1];

            for(int time: map.get(tweetName)){
                if(time < startTime || time > endTime){
                    continue;
                }
                int bucket = (time-startTime)/frequency;
                result[bucket] = result[bucket] + 1;
            }

            List<Integer> res = new ArrayList<>();
            for (int value : result) {
                res.add(value);
            }

            return res;
        }
    }

}
