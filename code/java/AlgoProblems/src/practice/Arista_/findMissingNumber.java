package practice.Arista_;

public class findMissingNumber {

    /*
    The concept behind this solution is that the elements appearing
    before the missing element will have ar[i] – i = 1
    and those appearing after the missing element will have ar[i] – i = 2.
     */

    public static void main(String[] args) {
        int ar[] = { 1, 2, 3, 4, 6, 7, 8 };
        int size = ar.length;
        int n = ar.length + 1; // [1, n]
        int missingNumber = binarySearch(ar, 0, ar.length-1, n);

        System.out.println("missing number is : " + missingNumber);
    }
/*
Another approach: Iterate through the array, For the element when array[i] != i+1
the missing number will be i+1.

n(n+1)/2 - sum
 */

    public static int binarySearch(int[] array, int low, int high, int n ){
        int mid = 0;

        while(high - low > 1){
            mid = low + (high-low)/2;

            if(array[low] - low != array[mid] - mid){
                high = mid;
            } else if(array[high] - high != array[mid] - mid){
                low = mid;
            }
        }

        return array[low] + 1;
    }

}
