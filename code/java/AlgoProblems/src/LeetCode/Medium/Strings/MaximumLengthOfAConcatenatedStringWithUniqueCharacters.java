package LeetCode.Medium.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
You are given an array of strings arr. A string s is formed by the
concatenation of a subsequence of arr that has unique characters.

Return the maximum possible length of s.

A subsequence is an array that can be derived from another array by
deleting some or no elements without changing the order of the remaining elements.

Can there be duplicates in arr ?

 */

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    public int maxLength(List<String> arr) {
        int maxLength = 0;

        List<String> uniqueCharacterStrings = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for(String current : arr){
            if(checkUniqueCharacters(current) && !set.contains(current)){
                uniqueCharacterStrings.add(current);
                maxLength = Math.max(maxLength, current.length());
                set.add(current);
            }
        }

        // Brute Force, 2 for loops. Done.

        return maxLength;
    }


    public boolean checkUniqueCharacters(String str){
        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int bitAtIndex = str.charAt(i) - 'a';

            // if that bit is already set in checker,
            // return false
            if ((checker & (1 << bitAtIndex)) > 0) {
                return false;
            }

            // otherwise update and continue by
            // setting that bit in the checker
            checker = checker | (1 << bitAtIndex);
        }

        // no duplicates encountered, return true
        return true;
    }

    // Approach 2:
    private boolean isUnique(String str) {
        if (str.length() > 26) {
            return false;
        }
        boolean[] used = new  boolean [26]; // because a string contains only lowercase a-z

        char[] arr = str.toCharArray();

        for (char ch : arr) {
            if (used[ch - 'a']){
                return false;
            } else {
                used[ch -'a'] = true;
            }
        }

        return true;
    }

    public int maxLength2(List<String> arr) {
        List<String> res = new ArrayList<>();
        res.add("");

        for (String str : arr) {
            if (!isUnique(str)) {
                continue;
            }

            // This will contain the new unique character strings in this iteration.
            List<String> resList = new ArrayList<>();

            // res contains the unique character strings generated so far from the left side. DP approach.

            for (String candidate : res) {
                String temp = candidate + str;
                if (isUnique(temp)) {
                    resList.add(temp); // We cannot add directly to res here because then it will affect the for loop iteration.
                }
            }

            res.addAll(resList);
        }

        int ans = 0;
        for (String str : res) {
            ans = Math.max(ans, str.length());
        }

        return ans;
    }



}
