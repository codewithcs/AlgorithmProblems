package LeetCodePremium.Questions;

import java.util.*;

/*
You are given an array of integers nums, there is a sliding window of size k which is
moving from the very left of the array to the very right.
You can only see the k numbers in the window.
Each time the sliding window moves right by one position.

Return the max sliding window.

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
    }

    // Time Limit Exceeded.
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for(int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            output[i] = max;
        }
        return output;
    }

    // Time Limit Exceeded
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>( (a, b) -> b-a );
        for(int i=0; i<k; i++){
            priorityQueue.add(nums[i]);
        }

        List<Integer> result = new ArrayList<>();
        result.add(priorityQueue.peek());

        for(int i=k; i< nums.length; i++){
            priorityQueue.remove(nums[i-k]);
            priorityQueue.add(nums[i]);
            result.add(priorityQueue.peek());
        }

        int[] answer = new int[result.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = result.get(i);
        }

        return answer;
    }

    // Approach 3: Using a Deque
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;

    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        // Very  very important to store indices in the deque.
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]){
            deq.removeLast();
        }
    }

    // Best Solution.
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        List<Integer> result = new ArrayList<>();
        this.nums = nums;

        // As we iterate from i=k-1 to the end, each of the i represents new sliding window.
        for (int i = 0; i < nums.length; i++) {
            clean_deque(i, k);
            deq.addLast(i);

            if(i>= k-1){ // Once we have processed k elements, then the front of the deque contains the maximum element.
                result.add(nums[deq.getFirst()]);
            }
        }

        int[] answer = new int[result.size()];
        for(int i=0; i< answer.length; i++){
            answer[i] = result.get(i);
        }
        return answer;
    }

    // Approach 4: DP
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        int [] left = new int[n];
        left[0] = nums[0]; // Important initialization

        int [] right = new int[n];
        right[n - 1] = nums[n - 1]; // Imp initialization

        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) {
                left[i] = nums[i];  // block_start
            }
            else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];  // block_end
            }
            else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            output[i] = Math.max(left[i + k - 1], right[i]);
        }

        return output;
    }
}
