package LeetCode.Easy.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string array words, return an array of all characters
that show up in all strings within the words (including duplicates).
You may return the answer in any order.

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.

Very Good Question.
 */
public class FindCommonCharacters {
    public static void main(String[] args) {
        System.out.println(String.valueOf((char)('a' + 1)));
    }

    public List<String> commonChars(String[] words) {
        List<String> answer = new ArrayList<>();

        String first = words[0];
        Map<Character, Integer> map = new HashMap<>();

        for(int i=0; i< first.length(); i++){
            if(!map.containsKey(first.charAt(i))){
                map.put(first.charAt(i), 1);
            } else {
                map.put(first.charAt(i), map.get(first.charAt(i)) + 1);
            }
        }

        for(int j=1; j < words.length; j++){
            String word = words[j];

            Map<Character, Integer> currentMap = new HashMap<>();

            for(int i=0; i< word.length(); i++){
                if(!currentMap.containsKey(word.charAt(i))){
                    currentMap.put(word.charAt(i), 1);
                } else {
                    currentMap.put(word.charAt(i), currentMap.get(word.charAt(i)) + 1);
                }
            }

            for(int i=0; i< first.length(); i++){
                if(!currentMap.containsKey(first.charAt(i))){
                    map.put(first.charAt(i), 0); // Do not remove this key but set the count to 0.
                    // Removing the key will lead to exceptions in the else block as we will again iterate over the string
                    // first in next iteration
                } else {
                    map.put(first.charAt(i), Math.min(map.get(first.charAt(i)), currentMap.get(first.charAt(i))  ));
                }
            }

        }

        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            char c = entry.getKey();
            int value = entry.getValue();

            for(int i=1; i<= value; i++){
                answer.add(c+"");
            }
        }

        return answer;
    }

    // Faster Solution.
    // Just count the characters and reduce intersection of words.
    // Then, convert it back to List.
    public List<String> commonChars2(String[] A) {
        int[] last = count(A[0]);

        for (int i = 1; i < A.length; i++) {
            last = intersection(last, count(A[i]));
        }

        List<String> arr = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (last[i] != 0) {
                char a = 'a';
                a += i;
                String s = String.valueOf(a);
                while (last[i] > 0) {
                    arr.add(s);
                    last[i]--;
                }
            }
        }
        return arr;
    }

    int[] intersection(int[] a, int[] b) {
        int[] t = new int[26];
        for (int i = 0; i < 26; i++) {
            t[i] = Math.min(a[i], b[i]);
        }
        return t;
    }

    int[] count(String str) {
        int[] t = new int[26];
        for (char c : str.toCharArray()) t[c - 'a']++;
        return t;
    }


    // Solution 3:
    public int[] getCharCountBucket(String word, int[] charCount) {
        int[] charCountBucket = new int[26];
        for (char ch : word.toCharArray()) {
            if (charCount[ch - 97] > 0) {
                charCount[ch - 97]--;
                charCountBucket[ch - 97]++;
            }
        }
        return charCountBucket;
    }

    public List<String> commonChars3(String[] A) {
        List<String> result = new ArrayList<>();

        if (A.length == 0) {
            return new ArrayList<String>();
        }

        int[] charCount = new int[26];

        for (char ch : A[0].toCharArray()) {
            charCount[ch - 97]++;
        }

        for (int i = 1; i < A.length; i++) {
            charCount = getCharCountBucket(A[i], charCount);
        }

        for (int i = 0; i < charCount.length; i++) {
            while (charCount[i] > 0) {
                result.add("" + (char)(i + 97));
                charCount[i]--;
            }
        }

        return result;
    }
}
