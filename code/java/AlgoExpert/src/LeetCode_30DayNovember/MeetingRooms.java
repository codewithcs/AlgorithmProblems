package LeetCode_30DayNovember;

import java.util.Arrays;

public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length <= 1) return true;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] current = intervals[0];
        for(int i=1 ; i<intervals.length; i++){
            if(current[1] > intervals[i][0]){
                return false;
            }
            current = intervals[i];
        }

        return true;
    }
}
