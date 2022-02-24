package LeetCodePremium.Google.SortingAndSearching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]) );

        List<int[]> list = new ArrayList<>();
        if(intervals.length == 0){
            return list.toArray(new int[list.size()][2]);
        }

        list.add(intervals[0]);

        for(int i=1 ; i<intervals.length; i++){
            int[] current = list.get(list.size()-1);
            int[] interval = intervals[i];

            if(interval[0] > current[1]) {
                list.add(interval);
            } else {
                int[] newInterval = {current[0], Math.max(current[1], interval[1])};
                list.set(list.size()-1, newInterval);
            }
        }

        return list.toArray(new int[list.size()][2]);
    }
}
