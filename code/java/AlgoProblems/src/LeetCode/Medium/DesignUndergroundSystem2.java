package LeetCode.Medium;

import java.util.HashMap;

// Note: A customer can get in a station repeatedly.
// Important to have start station and end station consistent in our code.
public class DesignUndergroundSystem2 {
    // KEY - Start + End;
    // Value arr[0] - number of trips ,  Arr[1]  -- sum of travel times
    HashMap<String, int[]> travelTimes = new HashMap<>();

    // KEY - id
    // Value -- Starting station
    HashMap<Integer, String> idToStation = new HashMap<>();

    // KEY - id
    // Value -- start time
    HashMap<Integer, Integer> idToTime = new HashMap<>();

    public DesignUndergroundSystem2() {
    }

    public void checkIn(int id, String stationName, int t) {
        if (idToStation.containsKey(id)) { // to handle the case of calling checkIn immediately again 2nd time.
            return;
        }

        idToStation.put(id, stationName);
        idToTime.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        String s = idToStation.get(id) + " " + stationName;
        idToStation.remove(id);

        int time = t - idToTime.get(id);
        idToTime.remove(id); // Important to delete the id and then store the time first before a call to getAverageTime().

        if (travelTimes.containsKey(s)) {
            travelTimes.put(s, new int[] { travelTimes.get(s)[0] + 1, travelTimes.get(s)[1] + time});
        } else {
            travelTimes.put(s, new int[] {1, time});
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        String s = startStation + " " + endStation;

        if (!travelTimes.containsKey(s)) {
            return 0.0;
        }

        double time = (double) travelTimes.get(s)[1];
        double amt = (double) travelTimes.get(s)[0];

        return time / amt;
    }
}
