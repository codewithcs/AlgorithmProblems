package LeetCodePremium.Medium;

/*
Given a string s, a k duplicate removal consists of choosing k adjacent and
equal letters from s and removing them causing the left and the right side
of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on
s until we no longer can.

Return the final string after all such
duplicate removals have been made.

It is guaranteed that the answer is unique.

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
*/
public class RemoveAllAdjacentDuplicatesInString2 {
    public static void main(String[] args) {
        System.out.println( removeDuplicates("deeedbbcccbdaa", 3));
    }
    // Check All substrings of length k ?
    // O(n^2) because of substrings ?

    // Time Complexity ??????
    public static String removeDuplicates(String s, int k) {
        // Ask the interviewer about the case: k > s.length(), I think we should return s.
        StringBuilder sb = new StringBuilder(s);
        boolean foundAtLeastOne = false;

        do{
            foundAtLeastOne = false;
            for(int i=0; i< sb.length()-k+1; i++) {
                boolean found = true;
                char current = sb.charAt(i);
                for(int j = i+1; j<=i+k-1 ; j++){
                    if(current != sb.charAt(j)){
                        found = false;
                        break;
                    }
                }

                if(found){
                    sb = new StringBuilder(sb.substring(0, i) + sb.substring(i+k));
                    foundAtLeastOne = true;
                }
            }
        } while(foundAtLeastOne);

        return sb.toString();
    }


    // Approach 2:
    public String removeDuplicates2(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];
        count[0] = 1;
        for(int i=1; i< sb.length(); i++){
            if(sb.charAt(i) == sb.charAt(i-1)){
                count[i] = count[i-1] + 1;
                if(count[i] == k){
                    sb.delete(i-k+1, i+1);
                    count = new int[sb.length()];
                    if(sb.length() == 0){
                        break;
                    }
                    count[0] = 1;
                    i=0;  // Important Step as we will need to re-fill the count values.
                }
            } else {
                count[i] = 1;
            }
        }

        return sb.toString();
    }

    // Using a single variable for the counts.
    // O(n^2/k) time complexity, O(1) space. 
    public String removeDuplicates2_Optimized(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int length = -1;
        while (length != sb.length()) { // If the length of the string builder did not change in the for loop, then false.
            length = sb.length();
            for (int i = 0, count = 1; i < sb.length(); ++i) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count = 1;
                } else if (++count == k) {
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }
        return sb.toString();
    }


    }
