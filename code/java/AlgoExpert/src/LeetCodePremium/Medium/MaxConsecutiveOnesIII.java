package LeetCodePremium.Medium;

/*
Given an array A of 0s and 1s, we may change
up to K values from 0 to 1.

Return the length of the longest (contiguous)
subarray that contains only 1s.

Note:
1. 1 <= A.length <= 20000
2. 0 <= K <= A.length
3. A[i] is 0 or 1
 */

public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0;
        while ( right < A.length) {
            // If we included a zero in the window we reduce the value of K.
            // Since K is the maximum zeros allowed in a window.
            if (A[right] == 0) {
                K--;
            }
            // A negative K denotes we have consumed all allowed flips and window has
            // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
            if (K < 0) {
                // If the left element to be thrown out is zero we increase K.
                if (A[left] == 0) {
                    K++;
                }
                left++;
            }

            right++;
        }
        
        return right - left;
    }
}
