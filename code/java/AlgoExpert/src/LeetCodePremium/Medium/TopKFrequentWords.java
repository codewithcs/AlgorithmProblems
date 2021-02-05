package LeetCodePremium.Medium;

import java.util.*;

/*
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency,
then the word with the lower alphabetical order comes first.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(String word: words){
            if(!map.containsKey(word)){
                map.put(word, 1);
            } else{
                map.put(word, map.get(word) + 1);
            }
        }

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int diff = map.get(o2) - map.get(o1);
                if(diff == 0){
                    return o1.compareTo(o2);
                } else {
                    return diff;
                }
            }
        });

        // Replacing the for loop with addAll
        // for(String key: map.keySet()){
        //     priorityQueue.add(key);
        // }
        priorityQueue.addAll(map.keySet());

        int size = 0;
        while(size < k){ // Get the topmost k elements from the heap.
            result.add(priorityQueue.poll());
            size++;
        }

        return result;
    }
}
