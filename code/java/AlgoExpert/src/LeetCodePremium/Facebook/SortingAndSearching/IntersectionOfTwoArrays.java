package LeetCodePremium.Facebook.SortingAndSearching;

import java.util.*;

/*
Given two arrays, write a function to compute their intersection.

Note:
Each element in the result must be unique.
The result can be in any order.
 */
public class IntersectionOfTwoArrays {
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
}
