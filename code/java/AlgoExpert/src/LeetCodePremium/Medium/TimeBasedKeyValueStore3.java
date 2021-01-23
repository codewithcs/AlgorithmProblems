package LeetCodePremium.Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore3 {
    // TreeMap.floorKey(timestamp) finds the largest timestamp smaller than the given timestamp.
    // O(1) for each set operation and O(logN) for each get operation where N is the number of entries in
    // the map.

    // O(N) space.
    Map<String, TreeMap<Integer, String>> map;

    public TimeBasedKeyValueStore3() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap());
        }

        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)){
            return "";
        }

        TreeMap<Integer, String> tree = map.get(key);
        Integer t = tree.floorKey(timestamp);
        return t != null ? tree.get(t) : "";
    }
}
