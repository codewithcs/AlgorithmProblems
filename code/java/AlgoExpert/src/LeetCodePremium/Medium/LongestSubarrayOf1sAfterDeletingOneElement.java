package LeetCodePremium.Medium;

/*
Given a binary array nums, you should delete one element from it.
Return the size of the longest non-empty subarray containing only 1's in the resulting array.
Return 0 if there is no such subarray.

Constraints:
1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
 */
public class LongestSubarrayOf1sAfterDeletingOneElement {

    // Breaking the problem into 2 parts.
    // First generate chains without deleting anything.
    // Then try to delete the 0 which is between two 1s.
    public int longestSubarray(int[] nums) {
        int[] lengths = new int[nums.length];
        // lengths[i]: Length of chain ending at index i.
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] == 1) {
                    lengths[i] = 1;
                }
            } else {
                if (nums[i] == 1) {
                    if (nums[i - 1] == 1) {
                        lengths[i] = 1 + lengths[i - 1];
                    } else {
                        lengths[i] = 1;
                    }
                }
            }
            maxLength = Math.max(maxLength, lengths[i]);
        }

        // Have to delete at least 1 element
        if (maxLength == nums.length) {
            return maxLength - 1;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                if (nums[i + 1] == 1 && nums[i - 1] == 1) {
                    int currentLength = 1 + lengths[i - 1]; // have to delete the 0, that is why we add just 1 and not 2.
                    for (int j = i + 2; j < nums.length; j++) {
                        if (nums[j] != 1) {
                            break;
                        } else {
                            currentLength++; // continuous chain
                        }
                    }

                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }

        return maxLength;
    }

    // O(n) time and O(1) space solution, Clean code.
    // We need prevCnt for the situation [1, 1, 0, 1]
    // If we have [1, 0, 0, 1] then previous count will be 0 for last 1.
    public int longestSubarray2(int[] nums) {
        int prevCnt = 0, cnt = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cnt++;
            } else {
                res = Math.max(res, cnt + prevCnt); // global maximum
                prevCnt = cnt; // Setting these variables for the next iteration.
                cnt = 0;
            }
        }

        res = Math.max(res, cnt + prevCnt);
        return res == nums.length ? res - 1 : res;  //if all ones, must remove one;
    }

    // Sliding Window Solution: See Max Consecutive Ones III problem.
    // Here we have to delete a 0 and not replace.
    public int longestSubarray3(int[] A) {
        int left = 0;
        int k = 1; // What if k > 1 ? 
        int res = 0;

        for (int right = 0; right < A.length; ++right) {
            if (A[right] == 0) {
                k--;
            }
            while (k < 0) { // k<0 means that we have deleted all the ks.
                if (A[left] == 0) {
                    k++;
                }
                left++;
            }
            res = Math.max(res, right - left);
        }

        return res;
    }
}