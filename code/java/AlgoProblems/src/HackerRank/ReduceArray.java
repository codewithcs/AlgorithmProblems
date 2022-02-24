package HackerRank;

import java.util.PriorityQueue;

public class ReduceArray {
    public static int min_cost(int[] arr)
    {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //adding all the elements to the queue
        for(Integer i : arr){
            queue.offer(i);
        }
        int temp = 0;
        int cost = 0;
        while(queue.size() > 1) {
            // Removing the first two minimum elements from the queue
            int first = queue.poll();
            int second = queue.poll();
            temp = first + second;
            cost += temp;

            //Adding back the sum of the minimum elements back to the queue
            queue.offer(temp);
        }

        return cost;
    }
}
