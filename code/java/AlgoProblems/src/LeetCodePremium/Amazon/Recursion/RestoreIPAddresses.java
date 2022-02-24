package LeetCodePremium.Amazon.Recursion;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s.
You can return them in any order.

A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros.
For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245",
"192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> answer = new ArrayList<>();
        traverse(0, answer, 0, s, s);
        return answer;
    }

    // Added important optimization: Do not go above 3 digits number. Duplicates will not be produced.
    public void traverse(int index, List<String> answer, int count, String current, String s){
        // Base Case
        if(count == 3){ // Goal
            String[] values = current.split("\\.");
            for(String value: values){
                if(value.length() >3 || value.length() > 1 && value.charAt(0) == '0' ) return; // no need to check for 0 length as we never make a bad ip, like 123..12
                int intValue = Integer.parseInt(value);
                if(intValue < 0 || intValue > 255){ // Constraints
                    return;
                }
            }

            answer.add(current);
            return;
        }

        // Choice
        for(int i=index; i-index<=3 && i<current.length()-1; i++)  { // Do not add . when we are at the last index.
            String currentString = current.substring(0, i+1) + "." + current.substring(i+1);
            traverse(i+2, answer, count+1, currentString, s);
        }
    }

    // See this:
    public List<String> restoreIpAddresses2(String s) {
        List<String> restoredIps = new ArrayList<>();
        restoreIps(0, 0, new int[4], s, restoredIps);
        return restoredIps;
    }

    private void restoreIps(int progressIndex, int currentSegment, int[] ipAddressSegments, String rawIpString, List<String> restoredIps) {
    /*
      If we have filled 4 segments (0, 1, 2, 3) and we are on the 4th,
      we will only record an answer if the string was decomposed fully
    */
        if (currentSegment == 4 && progressIndex == rawIpString.length()) {
            restoredIps.add(buildIpStringFromSegments(ipAddressSegments));
        } else if (currentSegment == 4) {
            return;
        }

    /* Generate segments to try, a segment can be 1 to 3 digits long. */
        for (int segLength = 1; segLength <= 3 && progressIndex + segLength <= rawIpString.length(); segLength++) {
            // Calculate 1 index past where the segment ends index-wise in the original raw ip string
            int onePastSegmentEnd = progressIndex + segLength;

            // Extract int value from our snapshot from the raw ip string
            String segmentAsString = rawIpString.substring(progressIndex, onePastSegmentEnd);
            int segmentValue = Integer.parseInt(segmentAsString);

            // Check the "snapshot's" validity - if invalid break iteration
            if (segmentValue > 255 || segLength >= 2  && segmentAsString.charAt(0) == '0') {
                break;
            }

            // Add the extracted segment to the working segments
            ipAddressSegments[currentSegment] = segmentValue;

            // Recurse on the segment choice - when finished & we come back here, the next loop iteration will try another segment
            restoreIps(progressIndex + segLength, currentSegment + 1, ipAddressSegments, rawIpString, restoredIps);
        }
    }

    private String buildIpStringFromSegments(int[] ipAddressSegments) {
        return ipAddressSegments[0] + "." + ipAddressSegments[1] + "."+ ipAddressSegments[2] + "." + ipAddressSegments[3];
    }
}
