package LeetCodePremium.Google.SortingAndSearching;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // init data
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<int[]>();

        // add all intervals starting before newInterval
        while (idx < n && newStart > intervals[idx][0]) {
            output.add(intervals[idx++]);
        }

        // add newInterval
        int[] interval = new int[2];
        // if there is no overlap, just add the interval
        if (output.isEmpty() || output.getLast()[1] < newStart) {
            output.add(newInterval);
        }
            // if there is an overlap, merge with the last interval
        else {
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        // add next intervals, merge with newInterval if needed
        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            // if there is no overlap, just add an interval
            if (output.getLast()[1] < start) {
                output.add(interval);
            }
                // if there is an overlap, merge with the last interval
            else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }

        return output.toArray(new int[output.size()][2]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int index  = 0;
        List<int[]> output = new ArrayList<>();

        while(index < intervals.length && intervals[index][0] < newInterval[0]){
            output.add(intervals[index]);
            index++ ;
        }

        if(output.isEmpty() || output.get(output.size()-1)[1] < newInterval[0] ){
            output.add(newInterval);
        } else {
            int[] interval = { output.get(output.size()-1)[0], Math.max(output.get(output.size()-1)[1], newInterval[1]) };
            output.set(output.size()-1, interval); // When merging we have to an overwrite
        }


        while(index < intervals.length){
            int[] current = output.get(output.size()-1);
            if(intervals[index][0] > current[1]){
                output.add(intervals[index]);
            } else {
                int[] interval = { current[0], Math.max(current[1], intervals[index][1]) };
                output.set(output.size()-1, interval); // // When merging we have to an overwrite
            }
            index++;
        }

        return output.toArray(new int[output.size()][2]);
    }

}
