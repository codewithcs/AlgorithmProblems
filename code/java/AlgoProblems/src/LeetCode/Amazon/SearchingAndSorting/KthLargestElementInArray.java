package LeetCode.Amazon.SearchingAndSorting;

import java.util.PriorityQueue;
import java.util.Random;

/*
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {
    // O(N) is the average case, O(N^2) in the worst case, O(1) space complexity.
    // Taking first element as the pivot element.
    public int findKthLargest(int[] nums, int k) {
        int start = 0; int end = nums.length-1;
        k = nums.length - k + 1; // converting to kth smallest.
        return partition(start, end, nums, k);
    }

    public int partition(int start, int end, int[] nums, int k){
        int pivot = nums[start];
        int left = start+1; int right = end;

        while(left<= right){
            if(nums[left] > pivot && nums[right] < pivot){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
            else if(nums[left] <= pivot){
                left++;
            } else if(nums[right] >= pivot){
                right--;
            }
        }

        int temp = nums[right];
        nums[right] = pivot;
        nums[start] = temp;

        // Edge Case.
        if(right == k-1){
            return nums[right];
        } else if(right > k-1){
            return partition(start, right-1, nums, k);
        } else {
            return partition(right +1, end, nums, k);
        }

    }

    // Taking last element as the pivot element.
    public int findKthLargest5(int[] nums, int k) {
        int start = 0; int end = nums.length-1;
        k = nums.length - k + 1; // converting to kth smallest.
        return partition2(start, end, nums, k);
    }

    public int partition2(int start, int end, int[] nums, int k){
        // Base Case.
        if(start>= end && start == k-1) return nums[start];

        int pivot = nums[end];

        int left = start; int right = end-1;

        while(left<= right){
            if(nums[left] > pivot && nums[right] < pivot){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
            else if(nums[left] <= pivot){
                left++;
            } else if(nums[right] >= pivot){
                right--;
            }
        }

        int temp = nums[left];
        nums[left] = pivot;
        nums[end] = temp;

        // Edge Case.
        if(left == k-1){
            return nums[left];
        } else if(left > k-1){
            return partition(start, left-1, nums, k);
        } else {
            return partition(left +1, end, nums, k);
        }
    }

    // Other techniques to find the pivot, median of 3, etc.

    // O(N*logk) time and O(k) space to store the heap elements. Tricky Solution
    public int findKthLargest2(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // output
        return heap.poll();
    }

    // Naive: Sort the array: O(n*logn) time and O(1) space.

    // Linear Time Selection Algorithm:
    // The answer is quite simple, we can randomize the input, so that even when the worst case input
    // would be provided the algorithm wouldn't be affected.
    // So all what it is needed to be done is to shuffle the input.
    // Blum-Floyd-Pratt-Rivest-Tarjan algorithm
    public int findKthLargest3(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private void shuffle(int a[]) {
        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
    }

    // Hoare Partitioning Scheme.
    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    // Choosing pivot element using Median of 3 strategy.
    public int findKthLargest4(int[] nums, int k) {
        return select(nums, k-1);
    }

    // Quick Select
    private int select(int[] nums, int k) {
        int left = 0, right = nums.length-1;
        while(true) {
            if(left == right)
                return nums[left];
            int pivotIndex = medianOf3(nums, left, right);
            pivotIndex = partition(nums, left, right, pivotIndex);
            if(pivotIndex == k) {
                return nums[k];
            }
            else if(pivotIndex > k) {
                right = pivotIndex - 1;
            }
            else {
                left = pivotIndex + 1;
            }
        }
    }

    // Use median-of-three strategy to choose pivot
    private int medianOf3(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if(nums[right] > nums[left]) {
            swap(nums, left, right);
        }
        if(nums[right] > nums[mid]) {
            swap(nums, right, mid);
        }
        if(nums[mid] > nums[left]) {
            swap(nums, left, mid);
        }
        return mid;
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int index = left;
        for(int i = left; i < right; ++i) {
            if(nums[i] > pivotValue) {
                swap(nums, index, i);
                ++index;
            }
        }
        swap(nums, right, index);
        return index;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
