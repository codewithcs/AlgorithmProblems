package LeetCode.Easy;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Given an integer array arr, return the mean of the remaining integers
after removing the smallest 5% and the largest 5% of the elements.

Answers within 10^-5 of the actual answer will be considered accepted.

Constraints:
20 <= arr.length <= 1000
arr.length is a multiple of 20.
0 <= arr[i] <= 10^5
 */
public class MeanOfArrayAfterRemovingSomeElements {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int number =  (int)Math.ceil(0.05 * arr.length);

        double avg = 0;
        for(int i= number; i<= arr.length-1-number ; i++){
            avg += arr[i];
        }
        return avg/(arr.length-2*number);
    }

    public double trimMean2(int[] arr) {
        Arrays.sort(arr);

        int n = arr.length;
        double sum = 0;

        for(int i = n / 20; i < n - n / 20; i++) {
            sum += arr[i];
        }

        return sum / (n - n / 10);
    }

    // Using Heap, not better than sorting.
    public double trimMean3(int[] arr) {
        int l = arr.length, trim = l / 20, sum = 0;
        double div = l - 2 * trim;
        PriorityQueue<Integer> hq = new PriorityQueue<>((a, b) -> a - b);
        PriorityQueue<Integer> lq = new PriorityQueue<>((a, b) -> b - a);
        for (int i : arr) {
            hq.offer(i);
            if (hq.size() > trim) {
                lq.offer(hq.poll());
                if (lq.size() > trim) sum += lq.poll();
            }
        }
        return sum / div;
    }
}
