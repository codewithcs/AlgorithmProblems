package LeetCode.Google.SortingAndSearching;

/*
Let's call an array arr a mountain if the following properties hold:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... arr[i-1] < arr[i]
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
Given an integer array arr that is guaranteed to be a mountain,
return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].

Constraints:
3 <= arr.length <= 104
0 <= arr[i] <= 106
arr is guaranteed to be a mountain array.

Similar problem on Algo Expert (multiple peaks).
 */
public class PeakIndexInAMountainArray {
    // O(n) time.
    public int peakIndexInMountainArray(int[] arr) {
        int index  = -1;
        for(int i=0 ; i<arr.length-2; i++){
            if(arr[i] < arr[i+1] && arr[i+1] > arr[i+2] ){
                index =  i+1;
            }
        }
        return index;
    }

    // O(logn) time and O(1) space.
    public int peakIndexInMountainArray2(int[] arr) {
        int start = 1; // cannot be 0.
        int end = arr.length-1; // also works for arr.length-2

        int mid = start + (end-start)/2;
        int index = -1;

        while(start <= end && mid>=1 && mid<=arr.length-2){
            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]){
                index = mid;
                break;
            } else if(arr[mid] > arr[mid-1] && arr[mid] < arr[mid+1]){
                start = mid + 1;
            } else if(arr[mid] < arr[mid-1] && arr[mid] > arr[mid+1]){
                end = mid - 1;
            }

            mid = start + (end-start)/2;
        }
        return index;
    }

    /*
    Imp Example:
    If [1, 2] belong at the start of the array,
    Here if we have start as element 1 and end as 2 then mid will be 0 index and mid-1 would be -1, leading to problem.

    If [1, 2] however belong to the end, then mid will never be the last index because integer Mathematics will save us.
    As integer values will round to a lower value.
     */

    // We can binary search over this array of comparisons, to find the largest index i such that A[i] < A[i+1]. Answer would be i+1.
    public int peakIndexInMountainArray3(int[] A) {
        int low = 0, high = A.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (A[mid] < A[mid + 1]) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }

}
