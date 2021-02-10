package LeetCodePremium.Medium;

import java.util.Stack;

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


    // Approach 2: O(n) time and O(n) space.
    public String removeDuplicates2_Optimized(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[sb.length()];
        for(int i=1; i< sb.length(); i++){
            if(i==0 || sb.charAt(i) != sb.charAt(i-1)){
                count[i] = 1;
            } else {
                count[i] = count[i-1] + 1;
                if(count[i] == k){
                    sb.delete(i-k+1, i+1);
                    i = i - k; // this line make the time complexity O(n).

                    /* count = new int[sb.length()]; // No need to re-initialize.
                    if(sb.length() == 0){ // using this logic, time complexity will be O(n^2/k)
                        break;
                    }
                    count[0] = 1;
                    i=0; */  // Important Step as we will need to re-fill the count values.
                }
            }
        }

        return sb.toString();
    }

    // Using a single variable for the counts.
    // O(n^2/k) time complexity, O(1) space.
    // We scan the string no more than n/k times.
    public String removeDuplicates2(String s, int k) {
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

    // Approach 3: Stack, O(n) time and O(n) space.
    // We only care about the current count of current duplicate sequence
    // That is why we can use a stack here or dynamic array here.
    // Optimization for fixed length count array.
    public String removeDuplicates3(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<>();

        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) { // When i=0, stack will be empty.
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }

        return sb.toString();
    }


    class Pair {
        int cnt;
        char ch;
        public Pair(int cnt, char ch) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }

    // Stack With Re-Construction, O(n) time and O(n) space.
    public String removeDuplicates4(String s, int k) {
        Stack<Pair> counts = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (counts.empty() || s.charAt(i) != counts.peek().ch) {
                counts.push(new Pair(1, s.charAt(i)));
            } else {
                //counts.peek().cnt++;
                if (++counts.peek().cnt == k) {
                    counts.pop();
                }
            }
        }

        StringBuilder b = new StringBuilder();

        // Construction of the string from stack.
        while (!counts.empty()) {
            Pair p = counts.pop();
            for (int i = 0; i < p.cnt; i++) {
                b.append(p.ch);
            }
        }

        return b.reverse().toString();
    }


    // 2 Pointer, Slow and Fast pointers, O(n) time and O(n) space.
    public String removeDuplicates5(String s, int k) {
        Stack<Integer> counts = new Stack<>();
        char[] sa = s.toCharArray();
        int j = 0;
        for (int i = 0; i < s.length(); ++i, ++j) {
            sa[j] = sa[i];
            if (j == 0 || sa[j] != sa[j - 1]) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    j = j - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return new String(sa, 0, j);
    }
}
