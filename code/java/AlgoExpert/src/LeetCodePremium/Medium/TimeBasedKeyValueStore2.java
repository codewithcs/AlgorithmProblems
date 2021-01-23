package LeetCodePremium.Medium;

import javafx.util.Pair;

import java.util.*;

/*
   O(1) for each set operation, and O(logN) for each get operation, where N is the number
   of entries in the map,
   O(N) space complexity.
 */
public class TimeBasedKeyValueStore2 {
    // Timestamps are strictly increasing.
    // So the array list pointed to by a key will be sorted in ascending order by timestamps.

    HashMap<String, ArrayList<Node>> map;

    public TimeBasedKeyValueStore2() {
        map = new HashMap<>();
    } // Hashmap with key and array of nodes sorted with value and time stamp

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new Node(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        ArrayList<Node> list = map.get(key);

        // Edge Case for binary search.
        if (timestamp >= list.get(list.size()-1).timeStamp) {
            return list.get(list.size()-1).value;
        }

        int low = 0;
        int high = list.size()-1;

        while (low <= high){
            int mid = low + (high-low)/2;

            if (list.get(mid).timeStamp <= timestamp && list.get(mid+1).timeStamp > timestamp) { // 2nd check is very important
                return list.get(mid).value;
            }
            else if (list.get(mid).timeStamp > timestamp) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }

        return "";
        //5, 10, 15, 20
    }

    class Node {
        String value;
        int timeStamp;

        public Node(String v, int ts){
            value = v;
            timeStamp = ts;
        }
    }
}
