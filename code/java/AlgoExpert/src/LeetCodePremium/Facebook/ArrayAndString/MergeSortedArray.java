package LeetCodePremium.Facebook.ArrayAndString;

/*
Given two sorted integer arrays nums1 and nums2,
merge nums2 into nums1 as one sorted array.

Note:
The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is equal to m + n)
to hold additional elements from nums2.

Constraints:
-10^9 <= nums1[i], nums2[i] <= 10^9
nums1.length == m + n
nums2.length == n
 */
public class MergeSortedArray {

    // O(n+m) time and O(m) space.
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Clone = new int[nums1.length];
        int i=0; int j=0; int k=0;

        while(i<m && j<n){
            if(nums1[i] <= nums2[j]){
                nums1Clone[k] = nums1[i];
                i++; k++;
            } else {
                nums1Clone[k] = nums2[j]; k++; j++;
            }
        }

        while(i<m){
            nums1Clone[k] = nums1[i];
            i++; k++;
        }

        while(j<n){
            nums1Clone[k] = nums2[j];
            j++; k++;
        }

        for(int current=0; current<nums1.length; current++){
            nums1[current] = nums1Clone[current];
        }
    }

    // O(n+m) time and O(1) space.
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        // while there are still elements to compare
        while ((p1 >= 0) && (p2 >= 0))
            // compare two elements from nums1 and nums2
            // and add the largest one in nums1
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];

        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }
}
