package AlgoExpert_Easy;

public class BubbleSort {

    // Best: O(n) time | O(1) space, does n iterations when the array is sorted.
    // Average: O(n^2) time | O(1) space
    // Worst: O(n^2) time | O(1) space

    // Moving the max-element to the right edge.
    public static int[] bubbleSort(int[] array) {
        // Write your code here.
        if(array.length == 0){
            return new int[] {};
        }

        boolean isSorted = false;
        int counter = 0;

        while(!isSorted){
            isSorted = true;

            for(int i=0 ; i< array.length-1-counter; i++){
                if(array[i] > array[i+1]){
                    swap(i, i+1, array);
                    isSorted = false;
                }
            }
            counter++;
        }

        return array;
    }
    // can have 2 for-loops as well.

    public static void swap(int i, int j, int[] array){
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    void bubbleSort2(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

}
