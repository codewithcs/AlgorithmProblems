package AlgoExpert_Easy;

public class SelectionSort {

    // Best: O(n^2) time | O(1) space, does n^2 iterations when the array is sorted.
    // Average: O(n^2) time | O(1) space
    // Worst: O(n^2) time | O(1) space
    public static int[] selectionSort(int[] array){
        if(array.length == 0){
            return new int[] {};
        }

        int startIdx = 0;

        // Moving the smallest element to the leftmost side.

        while(startIdx < array.length -1){
            int smallestIdx = startIdx;

            for(int i = startIdx+1 ; i< array.length; i++){
                if(array[smallestIdx] > array[i]){
                    smallestIdx = i;
                }
            }
            swap(startIdx, smallestIdx, array);
            startIdx++;
        }

        return array;
    }

    public static void swap(int i, int j, int[] array){
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    void selectionSort2(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}
