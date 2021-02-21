package LeetCodePremium.Medium;

import java.util.*;

/*
Given an array of integers nums and an integer limit, return the size of the
longest non-empty subarray such that the absolute difference between any two
elements of this subarray is less than or equal to limit.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    // Brute Force: O(n^4)
    public int longestSubarray1(int[] nums, int limit) {
        int maxSize = 1;

        for(int i=0 ; i< nums.length-1; i++){
            for(int j= i+1; j< nums.length ; j++){
                // Check every pair of [i, j].
                boolean found = true;

                for(int x = i; x < j; x++){
                    for(int y = i+1; y<= j; y++){
                        if(Math.abs(nums[x] - nums[y]) > limit){
                            found = false;
                            break;
                        }
                    }

                    if(!found) {
                        break;
                    }
                }

                if(found){
                    if(maxSize < j - i +1){
                        maxSize = j - i +1;
                    }
                }

            }
        }

        return maxSize;
    }

    // O(n^2) time in the worst case and O(1) space.
    public int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length, left = 0, min = nums[0], max = nums[0], res = 0;

        for (int i = 0; i < len; i++) {
            int diff = Math.max(Math.abs(nums[i] - min), Math.abs(max - nums[i]));
            if (diff <= limit) {
                min = Math.min(nums[i], min);
                max = Math.max(nums[i], max);
                res = Math.max(res, i - left + 1);
            } else {
                int right = i - 1;
                min = max = nums[i]; // Now we will lose track of left pointer, so find the min and max in [L, R] satisfying the constraint.
                while (left < right && Math.abs(nums[i] - nums[right]) <= limit) {
                    min = Math.min(nums[right], min);
                    max = Math.max(nums[right], max);
                    right--;
                }
                left = right + 1;
            }
        }

        return res;
    }



    public int longestSubarray2(int[] nums, int limit) {
        int left = 0;
        int right = 0;
        int res = 1;

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);

        while (left <= right && right < nums.length) {
            minQ.offer(nums[right]);
            maxQ.offer(nums[right]);
            int minNum = minQ.peek();
            int maxNum = maxQ.peek();
            if (maxNum - minNum <= limit) {
                right++;
                res = Math.max(res, right - left);
            } else {
                minQ.remove(nums[left]);
                maxQ.remove(nums[left]);
                left++;
                right++; // When ">limit" you also need to change your right, if you do not do so, you will push the same number twice.
                // Also we need to find maximum sliding window, so we increment both pointers.
            }
        }

        return res;
    }

    // O(nlogn) time and  O(n) space.
    public int longestSubarray3(int[] A, int limit) {
        int i = 0, j;

        TreeMap<Integer, Integer> m = new TreeMap<>();

        for (j = 0; j < A.length; j++) {
            m.put(A[j], 1 + m.getOrDefault(A[j], 0));

            // Violation.
            if (m.lastEntry().getKey() - m.firstEntry().getKey() > limit) {
                m.put(A[i], m.get(A[i]) - 1);
                if (m.get(A[i]) == 0) {
                    m.remove(A[i]);
                }
                i++; // When we are incrementing i, we are incrementing j as well, that is why j-i is max in the end.
            }
        }

        return j - i;
    }


    public static void main(String[] args) {
        longestSubarray4(new int[]{10, 1, 2, 4, 7, 2}, 5);
    }
    // Using 2 Deques, O(n) time and O(n) space.
    // Each element push and pop atmost once.
    // limit >= 0, A[i] >= 1 and A.length >= 1
    public static int longestSubarray4(int[] A, int limit) {
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();
        int i = 0, j;

        for (j = 0; j < A.length; ++j) {
            while (!maxd.isEmpty() && A[j] > maxd.peekLast()){
                maxd.pollLast();
            }
            while (!mind.isEmpty() && A[j] < mind.peekLast()){
                mind.pollLast();
            }

            maxd.add(A[j]); // adds to the tail of the queue (tail).
            mind.add(A[j]);
            System.out.println("i is : " + i);
            System.out.println("j is : " + j);
            System.out.println("max deque is : " + maxd);
            System.out.println("min deque is : " + mind);

            // If there is a violation then we need to increment i
            if (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == A[i]){ // One of these ifs is guaranteed to be true if there is a violation.
                    maxd.poll();
                } else if (mind.peek() == A[i]){
                    mind.poll();
                }
                ++i;
            }

            // we increment j always.
        }

        return j - i;
    }
}
