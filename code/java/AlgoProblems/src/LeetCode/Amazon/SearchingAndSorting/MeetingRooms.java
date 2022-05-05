package LeetCode.Amazon.SearchingAndSorting;

import java.util.Arrays;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.
 */

public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length <=1 ) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]) );

        int[] current = intervals[0];

        for(int i=1; i<intervals.length ; i++){
            if(current[1] > intervals[i][0] ){
                return false;
            }
            current = intervals[i];
        }

        return true;
    }
}
