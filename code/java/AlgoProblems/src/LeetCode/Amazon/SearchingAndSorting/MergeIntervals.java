package LeetCode.Amazon.SearchingAndSorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a collection of intervals, merge all overlapping intervals.
intervals[i][0] <= intervals[i][1]

Sorting takes O(n log(n)) and merging the intervals takes O(n).
So, the resulting algorithm takes O(n log(n)).
O(1) space as
Generally we do not consider space required by output or input, when we analyse space requirement.
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();

        int[] newInterval = intervals[0];
        result.add(newInterval);

        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) { // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
                // Writing to newInterval here also writes to the list element as newInterval can get a reference to it when
                // element is added to the list.
            } else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }


}
