package AlgoExpert_Easy;

public class InsertionSort {

    // Best: O(n) time | O(1) space, does n iterations when the array is sorted.
    // Average: O(n^2) time | O(1) space
    // Worst: O(n^2) time | O(1) space

    public static int[] insertionSort(int[] array){
        if(array.length == 0){
            return new int[] {};
        }

        for(int i=1; i< array.length ; i++){
            int j= i;

            while( j> 0 && array[j] < array[j-1] ) {
                swap(j, j-1, array);
                j = j-1;
            }
        }
        return array;
    }

    public static void swap(int i, int j, int[] array){
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    void insertionSort2(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
