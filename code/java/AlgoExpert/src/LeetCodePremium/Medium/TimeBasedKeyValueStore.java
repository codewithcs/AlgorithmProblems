package LeetCodePremium.Medium;

import java.util.*;

/*
Create a time based key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)
    Stores the key and value, along with the given timestamp.

2. get(string key, int timestamp)
    1. Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
    2. If there are multiple such values, it returns the one with the largest timestamp_prev.
    3. If there are no values, it returns the empty string ("").

Note:
1. All key/value strings are lowercase.
2. All key/value strings have length in the range [1, 100]
3. The timestamps for all TimeMap.set operations are strictly increasing.
4. 1 <= timestamp <= 10^7
5. TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore {
    TreeMap<Integer, List<String>> map;

    public TimeBasedKeyValueStore() {
        map = new TreeMap<>(Comparator.reverseOrder());
    }

    public void set(String key, String value, int timestamp) {
        List<String> list = new ArrayList<>();
        list.add(key);
        list.add(value);
        map.put(timestamp, list);
    }

    public String get(String key, int timestamp) {
        String result = "";
        for(int mapKey: map.keySet()){
            if(mapKey <= timestamp){
                if(map.get(mapKey).get(0).equals(key)){
                    result = map.get(mapKey).get(1);
                    break;
                }
            }
        }

        return result;
    }
}
