package LeetCode.Facebook.SortingAndSearching;

import java.util.*;

/*
Given two arrays, write a function to compute their intersection.

Note:
Each element in the result must be unique.
The result can be in any order.
 */
public class IntersectionOfTwoArrays {
    // O(nlogn + mlogn) time and O(1) space.
    // If the arrays are sorted then O(n+m) time and O(1) space.
    // Not considering the space used by return array.
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1); Arrays.sort(nums2);
        int i=0; int j=0;

        while( i < nums1.length && j < nums2.length ) {
            if(nums1[i] == nums2[j]){
                set.add(nums1[i]);
                i++; j++;
            } else if(nums1[i] < nums2[j]){
                i++;
            } else {
                j++;
            }
        }
        int[] result = new int[set.size()];
        List<Integer> list = new ArrayList<>(set);
        for(int k=0; k<result.length;k++){
            result[k] = list.get(k);
        }
        return result;
    }

    // O(n+m) time and space.
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int num: nums1){
            set1.add(num);
        }

        for(int num: nums2){
            if(set1.contains(num)){
                set2.add(num);
            }
        }

        int[] result = new int[set2.size()];
        int i=0;
        for(int num: set2){
            result[i] = num; i++;
        }
        return result;
    }
}
