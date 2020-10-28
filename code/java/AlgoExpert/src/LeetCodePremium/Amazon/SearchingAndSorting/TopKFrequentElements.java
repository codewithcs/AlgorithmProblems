package LeetCodePremium.Amazon.SearchingAndSorting;

import java.util.*;

/*
// Given a non-empty array of integers, return the k most frequent elements.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] x = topKFrequent(new int[]{1, 2}, 2);
        // System.out.println(x[0] + "  :  " + x[1]);
    }

    public static int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        System.out.println("Size of map is : " + map.size());

        List<Map.Entry<Integer, Integer>> sortedSet = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            sortedSet.add(entry);
        }
        Collections.sort(sortedSet, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1,
                               Map.Entry<Integer, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }
        });

        List<Integer> list = new ArrayList<>();

        for (Map.Entry entry : sortedSet) {
            if (k <= 0) break;
            list.add((Integer) entry.getKey());
            k--;
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    // Approach 2:
    public static int[] topKFrequent2(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        SortedSet<Map.Entry<Integer, Integer>> sortedSet = new TreeSet<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1,
                               Map.Entry<Integer, Integer> e2) {
                if(e1.getValue() == e2.getValue()){
                    return -1;
                } else {
                    return e2.getValue().compareTo(e1.getValue());
                }
            }
        });


        List<Integer> list = new ArrayList<>();

        for (Map.Entry entry : sortedSet) {
            if (k <= 0) break;
            list.add((Integer) entry.getKey());
            k--;
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
