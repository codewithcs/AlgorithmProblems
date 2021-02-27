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
 */

import java.util.List;

public class TweetCountsPerFrequency {

    class TweetCounts {

        public TweetCounts() {

        }

        public void recordTweet(String tweetName, int time) {

        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            return null; 
        }
    }

}
