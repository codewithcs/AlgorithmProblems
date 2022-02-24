package LeetCodePremium.Facebook.Others;

import java.util.*;

public class FindAllAnagramsInAString {

    /// Time Limit Exceeded: 1 Test Case failing.
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();

        for(char current: p.toCharArray()){
            if(map.containsKey(current)){
                map.put(current, map.get(current)+1);
            } else{
                map.put(current, 1);
            }
        }

        Map<Character, Integer> copy = new HashMap<>(map);
        List<Integer> list = new ArrayList<>();

        for(int i=0; i< s.length(); i++){
            int j = i;
            while(copy.size() > 0 && j < s.length()){
                char current = s.charAt(j);
                if(copy.containsKey(current)){
                    copy.put(current, copy.get(current)-1);
                    if(copy.get(current) == 0){
                        copy.remove(current);
                    }
                    j++;
                } else {
                    break;
                }
            }
            if(copy.size() == 0) list.add(i);
            copy = new HashMap<>(map);
        }

        return list;
    }

    // O(Ns + Np) since it's one pass along both strings.
    // O(1) space because pMap and sMap do not contain more than 26 characters.
    public List<Integer> findAnagrams2(String s, String p) {
        if(s.length() < p.length()) return new ArrayList<Integer>();
        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        for (char current : p.toCharArray()) {
            if (pMap.containsKey(current)) {
                pMap.put(current, pMap.get(current) + 1);
            } else {
                pMap.put(current, 1);
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int right = 0; right < s.length(); right++){
            char current = s.charAt(right);
            if(sMap.containsKey(current)){
                sMap.put(current, sMap.get(current)+1);
            } else{
                sMap.put(current, 1);
            }

            // Considering all sliding windows of length p.
            // Now we can increment left (Decrementing the counter for left in the hash table is same as incrementing
            // left pointer)
            if(right >= p.length()){
                int left = right - p.length();
                if(sMap.containsKey(s.charAt(left))){
                    if(sMap.get(s.charAt(left)) == 1){
                        sMap.remove(s.charAt(left));
                    } else{
                        sMap.put(s.charAt(left), sMap.get(s.charAt(left))-1);
                    }
                } else {
                    left++;
                }
            }
            if(sMap.equals(pMap)){
                list.add(right-p.length()+1);
            }
        }
        return list;
    }

    // O(Ns + Np) since it's one pass along both strings.
    // O(1) space because pMap and sMap do not contain more than 26 characters.
    public List<Integer> findAnagrams3(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList();

        int [] pCount = new int[26];
        int [] sCount = new int[26];
        // build reference array using string p
        for (char ch : p.toCharArray()) {
            pCount[(ch - 'a')]++;
        }

        List<Integer> output = new ArrayList();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter
            // on the right side of the window
            sCount[(s.charAt(i) - 'a')]++;
            // remove one letter
            // from the left side of the window
            if (i >= np) {
                sCount[(s.charAt(i - np) - 'a')]--;
            }
            // compare array in the sliding window
            // with the reference array
            if (Arrays.equals(pCount, sCount)) {
                output.add(i - np + 1);
            }
        }
        return output;
    }
}
