package LeetCode.Google.TreesAndGraphs;

import java.util.HashMap;
import java.util.Map;

public class FindTheTownJudge {

    // Overall: O(E) time and O(N) space.
    public int findJudge(int N, int[][] trust) {
        if(N == 1 && trust.length ==0 ){ // Very Important Case.
            return 1;
        }

        if(trust.length == 0){
            return -1;
        }

        Map<Integer, Integer[]> map = new HashMap<>();

        // O(E)
        for(int i=0; i<trust.length; i++){
            int first = trust[i][0];
            int second = trust[i][1];
            if(!map.containsKey(first)){
                map.put(first, new Integer[]{0, 0} );
            }
            if(!map.containsKey(second)){
                map.put(second, new Integer[]{0, 0});
            }
            map.get(first)[1]++ ;// [1] outdegree
            map.get(second)[0]++; // [0] indegree
        }

        // O(N), A map does not store values by default, that is why we need a check at line 10.
        for(int key: map.keySet()){
            if(map.get(key)[0] == N-1  && map.get(key)[1] == 0){
                return key;
            }
        }
        return -1;
    }

    // Approach 2: Two Arrays
    public int findJudge2(int N, int[][] trust) {

        if (trust.length < N - 1) {
            return -1;
        }

        int[] indegrees = new int[N + 1];
        int[] outdegrees = new int[N + 1];

        for (int[] relation : trust) {
            outdegrees[relation[0]]++;
            indegrees[relation[1]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (indegrees[i] == N - 1 && outdegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    // Approach 3: One Array.
    public int findJudge3(int N, int[][] trust) {
        if (trust.length < N - 1) { // Very interesting check.
            return -1;
        }

        int[] trustScores = new int[N + 1]; // When N=1, makes use of default 0 values.

        for (int[] relation : trust) {
            trustScores[relation[0]]--;
            trustScores[relation[1]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (trustScores[i] == N - 1) {
                return i;
            }
        }
        return -1;
    }

}
