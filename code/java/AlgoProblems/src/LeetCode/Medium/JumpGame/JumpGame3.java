package LeetCode.Medium.JumpGame;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an array of non-negative integers arr, you are initially
positioned at start index of the array.
When you are at index i, you can jump to i + arr[i] or i - arr[i],
check if you can reach to any index with value 0.

Notice that you can not jump
outside of the array at any time.

Constraints:
1 <= arr.length <= 5 * 10^4
0 <= arr[i] < arr.length
0 <= start < arr.length
 */

// We jump a fixed amount of length.

public class JumpGame3 {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];

        if(arr[start] == 0) return true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int currentIndex = queue.poll();

            if(visited[currentIndex]){
                continue;
            }

            visited[currentIndex] = true;

            int left = currentIndex - arr[currentIndex];
            int right = currentIndex + arr[currentIndex];

            if(left >= 0){
                if(arr[left] == 0) {
                    return true;
                }

                queue.add(left);
            }

            if(right <= arr.length -1){
                if(arr[right] == 0) {
                    return true;
                }

                queue.add(right);
            }
        }

        return false;
    }

    // Only add a particular index in the queue only once.
    public boolean canReach2(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];

        if(arr[start] == 0){
            return true;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int currentIndex = queue.poll();

            visited[currentIndex] = true;

            int left = currentIndex - arr[currentIndex];
            int right = currentIndex + arr[currentIndex];

            if(left >= 0){
                if(arr[left] == 0) {
                    return true;
                }

                if(!visited[left]){
                    queue.add(left);
                }
            }

            if(right <= arr.length -1){
                if(arr[right] == 0) {
                    return true;
                }

                if(!visited[right]){
                    queue.add(right);
                }

            }

        }

        return false;
    }


    // Slightly Cleaner Code
    public boolean canReach3(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int currentIndex = queue.poll();

            if(arr[currentIndex] == 0) {
                return true;
            }

            visited[currentIndex] = true;

            int left = currentIndex - arr[currentIndex];
            int right = currentIndex + arr[currentIndex];

            if(left >= 0 && !visited[left]){
                queue.add(left);
            }

            if(right <= arr.length -1 && !visited[right]){
                queue.add(right);
            }
        }

        return false;
    }

    // Mutating the array in order to keep track of visited indices.
    // Since the numbers are non-negative, we can mark
    // an index as -1 to make it visited.
    // O(n) time and space.
    public boolean canReach4(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int currentIndex = queue.poll();

            if(arr[currentIndex] == 0) {
                return true;
            }

            int left = currentIndex - arr[currentIndex];
            int right = currentIndex + arr[currentIndex];

            arr[currentIndex] = -1; // visited.
            // Do not place it before assigning left and right.

            if(left >= 0 &&  arr[left] != -1){
                queue.add(left);
            }

            if(right <= arr.length -1 && arr[right] != -1){
                queue.add(right);
            }
        }

        return false;
    }
}
