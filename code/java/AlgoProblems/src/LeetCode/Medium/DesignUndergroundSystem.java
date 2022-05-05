package LeetCode.Medium;

import java.util.*;

/*
Implement the UndergroundSystem class:
void checkIn(int id, string stationName, int t)
A customer with a card id equal to id, gets in the station stationName at time t.
A customer can only be checked into one place at a time.
void checkOut(int id, string stationName, int t)
A customer with a card id equal to id, gets out from the station stationName at time t.
double getAverageTime(string startStation, string endStation)
Returns the average time to travel between the startStation and the endStation.
The average time is computed from all the previous traveling from startStation to endStation that happened directly.

Call to getAverageTime is always valid.

You can assume all calls to checkIn and checkOut methods are consistent.
If a customer gets in at time t1 at some station,
they get out at time t2 with t2 > t1. All events happen in chronological order.

Constraints:

There will be at most 20000 operations.
1 <= id, t <= 10^6
All strings consist of uppercase and lowercase English letters, and digits.
1 <= stationName.length <= 10
Answers within 10^-5 of the actual value will be accepted as correct.
 */

public class DesignUndergroundSystem {

    // Failed Approach: Start and end station are not consistent here.
    // A customer can get in a station repeatedly.
    static Map<Integer, Map<String, int[]>> map = new HashMap<>();
    public DesignUndergroundSystem() {
    }

    public static void main(String[] args) {
        checkIn(1, "A", 1);
        checkOut(1, "B", 5);
        checkIn(1, "A", 10);
        checkOut(1, "B", 20);
        System.out.println(getAverageTime("A", "B"));
    }
    public static void checkIn(int id, String stationName, int t) {
        if(map.containsKey(id)){
            return;
        } else {
            Map<String, int[]> map2 = new HashMap<>();
            map2.put(stationName, new  int[]{ t, 0 });
            map.put(id, map2);
        }
    }

    public static void checkOut(int id, String stationName, int t) {
        Map<String, int[]> map2 = map.get(id);
        if(map2.containsKey(stationName)){
            map2.get(stationName)[1] += t;
        } else {
            map2.put(stationName, new int[]{0, t});
        }
        map.put(id, map2);
    }

    public static double getAverageTime(String startStation, String endStation) {
        int count = 0;
        int additions = 0;
        int subtractions = 0;

        for(Integer id: map.keySet()){
            if(!map.get(id).containsKey(startStation) || !map.get(id).containsKey(endStation)){
                continue;
            }
            count++;
            for(String station: map.get(id).keySet()){
                if(station.equals(startStation)){
                    subtractions += map.get(id).get(station)[0];
                } else if(station.equals(endStation)){
                    additions += map.get(id).get(station)[1];
                }
            }
        }

        return  (double)(additions-subtractions)/(double)count;
    }
}
