package LeetCodePremium.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignUndergroundSystem3 {
    private Map<String, List<Integer>> stationAvg;
    private Map<Integer, List<String>> rider;

    public DesignUndergroundSystem3(){
        this.stationAvg = new HashMap<>();
        this.rider = new HashMap<>();
    }
    public void checkIn(int id, String stationName, int t) {
        List<String> stationDetails = new ArrayList<>();
        stationDetails.add(stationName);
        stationDetails.add(Integer.toString(t));
        rider.put(id, stationDetails);
    }

    public void checkOut(int id, String stationName, int t) {
        List<String> stationDetails = rider.get(id);
        String origin = stationDetails.get(0);
        String stationNames = origin+stationName;
        if (!stationAvg.containsKey(stationNames)) {
            // add and initialize list for station averages
            stationAvg.put(stationNames, new ArrayList<>());
            stationAvg.get(stationNames).add(0);
            stationAvg.get(stationNames).add(0);
        }
        int avg = stationAvg.get(stationNames).get(0) + (t - Integer.valueOf(stationDetails.get(1)));
        int count = stationAvg.get(stationNames).get(1) + 1;

        stationAvg.get(stationNames).set(0, avg);
        stationAvg.get(stationNames).set(1, count);
    }

    public double getAverageTime(String startStation, String endStation) {
        String stationNames = startStation + endStation;

        double ans = (double)stationAvg.get(stationNames).get(0) / (double)stationAvg.get(stationNames).get(1);

        return ans;
    }
}
