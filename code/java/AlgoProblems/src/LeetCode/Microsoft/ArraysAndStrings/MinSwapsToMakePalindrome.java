package LeetCode.Microsoft.ArraysAndStrings;


/*
Given a string s, the task is to find out the
minimum no of adjacent swaps required to make string s palindrome.
If it is not possible, then return -1.
 */

public class MinSwapsToMakePalindrome {

    // O(n^2) time and O(1) space.
    public int countMinSwaps(String str){
        int[] counts = new int[26];
        for(int i=0; i< str.length(); i++){
            counts[str.charAt(i) - 'a']++;
        }

        int numberOfOddCounts = 0;

        for(int i=0; i<26 ; i++){
            if(counts[i] % 2 != 0){
                numberOfOddCounts++;
            }
        }

        if(numberOfOddCounts > 1) { // This means that the palindrome cannot be created.
            return -1;
        }

        int minSwaps = 0;

        char[] charArray = str.toCharArray();

        for( int i=0, j=charArray.length-1 ; i <= charArray.length/2 -1 ; i++, j--){
            int k = j;
            int correctPosition = charArray.length - i - 1;

            while(i < k){
                if(charArray[i] != charArray[k]){
                    k--;
                } else {
                    //if(i == k) return -1; // Add this if we don't use numberOfOddCounts.
                    for (int current = k; current < correctPosition; current++) {
                        swap(charArray, current, current + 1);
                        minSwaps++;
                    }
                    break; // important.
                }
            }
        }

        return minSwaps;
    }

    public void swap(char[] charArray, int firstIndex, int secondIndex){
        char temp = charArray[firstIndex];
        charArray[firstIndex] = charArray[secondIndex];
        charArray[secondIndex] = temp;
    }
}
