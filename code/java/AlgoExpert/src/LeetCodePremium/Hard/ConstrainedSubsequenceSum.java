package LeetCodePremium.Hard;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
Given an integer array nums and an integer k, return the maximum sum of a
non-empty subsequence of that array such that for every two consecutive integers
in the subsequence, nums[i] and nums[j], where i < j,
the condition j - i <= k is satisfied.

A subsequence of an array is obtained by
deleting some number of elements
(can be zero) from the array, leaving the remaining
elements in their original order.

Constraints:
1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 */
public class ConstrainedSubsequenceSum {

    // dp[i] is the max sum we can have from A[:i] when A[i] has been chosen.
    // Time: O(N*K), Space: O(N)
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int maxSum = 0;

            // We are re-computing the maxSum in dp[:j] for j < i.
            // This re-computation is optimized using a Deque.
            for (int j = Math.max(i - k, 0); j < i; j++) { // choose the max element in latest k elements, it's in range [i-k, i-1]
                maxSum = Math.max(maxSum, dp[j]);
            }

            dp[i] = nums[i] + maxSum;
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    // Keep k elements in the deque.
    public static int constrainedSubsetSum2(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        Deque<Integer> deque = new ArrayDeque<>();

        // sum[i] means local max sum till i
        int[] dp =  new int[nums.length];
        int res = nums[0];

        for(int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
            if(!deque.isEmpty()) { // Assuming the queue only has indices for positive dp[i]'s.
                dp[i] += dp[deque.peek()];
            }
            res = Math.max(res, dp[i]);

            if(!deque.isEmpty() && deque.peek() < (i - k + 1)){
                deque.poll();
            }

            while(!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }

            if(dp[i] > 0) { // Only add index for positive dp[i]
                deque.add(i); // add to the tail.
            }
        }

        return res;
    }


    // More intuitive approaches.
    // More intuitive, O(n) time and O(k) space.
    // All elements are pushed and popped atmost once.
    /// k elements at max in the deque.
    public static int constrainedSubsetSum3(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int max = Math.max(0, deque.isEmpty() ? 0 : dp[deque.peekFirst()]);
            dp[i] = arr[i] + max;
            ans = Math.max(ans, dp[i]);
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) { // If dp[i] >= deque.peekLast() -> Can discard the tail since it's useless
                deque.pollLast();
            }
            deque.addLast(i);
            if (!deque.isEmpty() && deque.peek() < i- k +1 ) { // remove the last element of range k
                deque.removeFirst();
            }
        }
        return ans;
    }

    public static int constrainedSubsetSum3_2(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int ans = Integer.MIN_VALUE;

        int[] dp = new int[nums.length];

        for(int i=0; i< nums.length; i++){
            dp[i] = nums[i];

            if(!queue.isEmpty()){
                dp[i] = Math.max(dp[i], dp[i] + dp[queue.peek()]);
            }
            ans = Math.max(ans, dp[i]);

            while(!queue.isEmpty() && dp[queue.peekLast()] <= dp[i]){ // can also have < in place of <=
                queue.pollLast();
            }

            queue.addLast(i); // will come after while

            if(!queue.isEmpty() && queue.peek() < i- k +1 ){ // if and while are independent. 
                queue.poll();
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] nums = { 100, -10, -10, -10, -2, -2, -10, -100, 15, -5, -10, 10, 2, -10, 5, 20 };
        int k= 2;
        System.out.println(constrainedSubsetSum4(nums, k));
    }

    // Using constant space by mutating the input array.
    // O(n) time and O(1) space.
    // Important test case to convince that we need to keep deque non-increasing,
    // Allow duplicates in the deque.
    // [100,-10,-10,-10,-2,-2,-10,-100,15,-5,-10,10,2,-10,5,20]
    // 2
    public static int constrainedSubsetSum4(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            int maxSum = 0;

            if(!deque.isEmpty()){
                maxSum = Math.max(0, deque.peek());
            }

            nums[i] += maxSum;

            res = Math.max(res, nums[i]);

            // We have done the Calculation for this iteration in previous lines.
            // Now we need to maintain the constraint.
            if(i>= k && !deque.isEmpty() && deque.peek() == nums[i-k]){
                deque.poll();
            }

            // And maintain the deque in non increasing order if we are storing dp[i]'s in the deque.
            while(!deque.isEmpty() && deque.peekLast() < nums[i]) { // important to use < and not <=
                deque.pollLast();
            }

            System.out.println("i is : " + i);
            System.out.println("nums[i] is : " + nums[i]);

            deque.add(nums[i]);
            System.out.println("queue is : " + deque);
            System.out.println();
        }

        return res;
    }

    // Storing indices in deque.
    public static int constrainedSubsetSum5(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;

        // Using nums[] itself as the cache.
        for(int i = 0; i < nums.length; i++) {
            int maxSum = 0;

            if(!deque.isEmpty()){
                maxSum = Math.max(0, nums[deque.peek()]);
            }

            nums[i] += maxSum;

            res = Math.max(res, nums[i]);

            //if( !deque.isEmpty() && deque.peek() == i-k){ // this also works. no need to add i>=k.
            //    deque.poll();
            //}

            if(!deque.isEmpty() && deque.peek() < i-k+1){
                deque.poll();
            }

            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            deque.add(i); // this will come after the while loop, as we have to first free space for i before adding it.
        }

        return res;
    }

}
