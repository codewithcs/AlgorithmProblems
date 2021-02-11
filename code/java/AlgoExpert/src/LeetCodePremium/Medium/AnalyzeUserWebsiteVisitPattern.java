package LeetCodePremium.Medium;

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
public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, Integer> count = new HashMap<>(); // count of users for a website.
        Map<String, Set<String>> map = new HashMap<>();

        for(int i=0; i< username.length; i++){
            if(!count.containsKey(website[i])){
                count.put(website[i], 1);
                if(!map.containsKey(username[i])){
                    map.put(username[i], new HashSet<>());
                }
                map.get(username[i]).add(website[i]);
            } else {
                if(!map.containsKey(username[i])){ // this website is visited by a different user.
                    count.put(website[i], count.get(website[i]) + 1);
                    map.put(username[i], new HashSet<>());
                    map.get(username[i]).add(website[i]);
                }
            }
        }

        // Now we have distinct mapping of website --> distinct users count.

        // Form the triplets.
        int max = Integer.MIN_VALUE;
        List<List<String>> list = new ArrayList<>();
        for(int i=0; i< website.length-2;i++){
            String first = website[i];
            for(int j=i+1; j< website.length-1; j++){
                String second = website[j];
                for(int k=j+1; k< website.length; k++){
                    String third = website[k];

                        // All 3 must be unique strings.
                        int min = Math.min(Math.min(count.get(first), count.get(second)), count.get(third));
                        if(min >= max){
                            max = min;
                            List<String> l = new ArrayList<>();
                            l.add(website[i]); l.add(website[j]); l.add(website[k]);
                            list.add(l);
                        }
                }
            }
        }

        // Find the lexicographically smallest in List<List<String>>
        int index = 0;
        for(int i=1; i< list.size(); i++){
            List<String> first = list.get(index);
            List<String> second = list.get(i);

            for(int j=0; j<3; j++){
                if(second.get(j).compareTo(first.get(j)) < 0){
                    index = i;
                    break;
                }
            }
        }

        return list.get(index);
    }
}
