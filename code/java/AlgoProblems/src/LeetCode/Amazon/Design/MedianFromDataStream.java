package LeetCode.Amazon.Design;
/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */

import java.util.PriorityQueue;

public class MedianFromDataStream {
    PriorityQueue<Integer> minHeap ;
    PriorityQueue<Integer> maxHeap ;

    /** initialize your data structure here. */
    public MedianFromDataStream() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>((a, b) -> Integer.compare(b, a));
    }

    public void addNum(int num) {
        Integer min = minHeap.peek();
        Integer max = maxHeap.peek();

        if(min != null && num <= min){
            maxHeap.add(num);
        } else if(max != null && num >= max){
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }

        if(Math.abs(minHeap.size()-maxHeap.size()) <= 1) return ;

        // Or, there could be an imbalance.
        if(minHeap.size() > maxHeap.size()){
            Integer a = minHeap.poll();
            maxHeap.add(a);
        } else {
            Integer a = maxHeap.poll();
            minHeap.add(a);
        }

    }

    public double findMedian() {
        if(minHeap.peek() == null && maxHeap.peek() == null) {
            return 0.0;
        }

        if(minHeap.size() == maxHeap.size()){
            double min = (double)minHeap.peek();
            double max = (double)maxHeap.peek();
            return (min + max)/2;
        } else if(minHeap.size() > maxHeap.size()){
            return (double)minHeap.peek();
        } else if(maxHeap.size() > minHeap.size()){
            return (double)maxHeap.peek();
        } else {
            return 0.0;
        }
    }
}
