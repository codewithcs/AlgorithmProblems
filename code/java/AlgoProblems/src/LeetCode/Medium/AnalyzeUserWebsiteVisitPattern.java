package LeetCode.Medium;

import java.util.*;

/*
We are given some website visits: the user with name username[i]
visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in
ascending order by the time of their visits.
(The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users.
If there is more than one solution, return the
lexicographically smallest such 3-sequence.

Note:
1. 3 <= N = username.length = timestamp.length = website.length <= 50
2. 1 <= username[i].length <= 10
3. 0 <= timestamp[i] <= 10^9
4. 1 <= website[i].length <= 10
5. Both username[i] and website[i] contain only lowercase characters.
6. It is guaranteed that there is at least one user who visited at least 3 websites.
7. No user visits two websites at the same time.
 */

/*
["dowg","dowg","dowg"]
[158931262,562600350,148438945]
["y","l","y"]
 */
class Pair {
    int time;
    String web;
    public Pair(int time, String web) {
        this.time = time;
        this.web = web;
    }
}

public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();
        int n = username.length;

        // Collect the website info for every user, key: username, value: (timestamp, website)
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }

        // Count map to record every 3 combination occurring time for the different user.
        Map<String, Integer> count = new HashMap<>();
        String res = ""; // Final Answer.

        for (String key : map.keySet()) {
            Set<String> set = new HashSet<>();
            // this set is to avoid visit the same 3-seq in one user
            List<Pair> list = map.get(key);
            list.sort(Comparator.comparingInt(a -> a.time)); /// Need to sort as the given input may not be sorted in timestamps.

            // Brute force O(N ^ 3)
            for (int i = 0; i < list.size()-2; i++) {
                for (int j = i + 1; j < list.size()-1; j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        String str = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                        if (!set.contains(str)) {
                            count.put(str, count.getOrDefault(str, 0) + 1);
                            set.add(str);
                        }

                        // If the count is equal, then see lexicographic order.
                        if (res.equals("") || count.get(res) < count.get(str) || (count.get(res).equals(count.get(str)) && res.compareTo(str) > 0)) {
                            res = str;
                        }
                    }
                }
            }
        }

        // grab the right answer
        String[] r = res.split(" ");
        return new ArrayList<>(Arrays.asList(r));
    }
}
