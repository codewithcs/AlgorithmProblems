package LeetCode.Facebook.ArrayAndString;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtmostKDistinctCharacters {

    // O(n) time and O(k) space.
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k==0) return 0;

        if(s.length() == 0){
            return 0;
        } else if(s.length() == 1){
            return 1;
        }

        int left=0; int right=0; int maxLeft=0; int maxRight=0;
        Map<Character, Integer> map = new HashMap<>();

        while( right < s.length() ){    // eceba
            if(map.containsKey(s.charAt(right))){ // Important first check.
                map.put(s.charAt(right), map.get(s.charAt(right)) + 1);
                if(maxRight - maxLeft < right - left){
                    maxLeft = left;
                    maxRight = right;
                }
                right++;
            }
            else if(map.size() < k){
                map.put(s.charAt(right), 1);
                if(maxRight - maxLeft < right - left){ // Do the maximum check at every condition where right is incremented.
                    maxLeft = left;
                    maxRight = right;
                }
                right++;
            }
            else if(map.size() == k){ // Here we only decrease the window size, so we don't need the maximum check.
                map.put(s.charAt(left), map.get(s.charAt(left))-1 );
                if(map.get(s.charAt(left)) == 0){
                    map.remove(s.charAt(left));
                }

                left++;
            }
        }


        return maxRight-maxLeft+1 ;
    }
}
