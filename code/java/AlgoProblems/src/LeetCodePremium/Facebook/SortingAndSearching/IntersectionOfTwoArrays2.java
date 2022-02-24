package LeetCodePremium.Facebook.SortingAndSearching;

/*
Given two arrays, write a function to compute their intersection.

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you
cannot load all elements into the memory at once ?
 */

import java.util.*;

public class IntersectionOfTwoArrays2 {
        // O(n+m) time and space.
        public int[] intersect(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map1 = new HashMap<>();
            Map<Integer, Integer> map2 = new HashMap<>();

            List<Integer> result = new ArrayList<>();
            for(int num: nums1){
                if(map1.containsKey(num)){
                    map1.put(num, map1.get(num) + 1);
                } else{
                    map1.put(num, 1);
                }
            }

            for(int num: nums2){
                if(map2.containsKey(num)){
                    map2.put(num, map2.get(num) + 1);
                } else{
                    map2.put(num, 1);
                }
                if(map1.containsKey(num) && map2.get(num) <= map1.get(num)){
                    result.add(num);
                }
            }

            int[] answer = new int[result.size()];

            for(int i=0; i< answer.length; i++){
                answer[i] = result.get(i);
            }

            return answer;
        }

        // Can get away with using only 1 hash map. Works. Best solution.
        // O(min(m, n)) space complexity and O(m + n) time.
        public int[] intersect2(int[] nums1, int[] nums2){
            Map<Integer, Integer> map1 = new HashMap<>();
            if(nums2.length < nums1.length){
                int[] temp = nums1;
                nums1 = nums2;
                nums2 = temp;
            }

            // Assume nums1 is the smaller array.
            List<Integer> result = new ArrayList<>();
            for(int num: nums1){
                if(map1.containsKey(num)){
                    map1.put(num, map1.get(num) + 1);
                } else{
                    map1.put(num, 1);
                }
            }

            for(int num: nums2){
                if(map1.containsKey(num) && map1.get(num) > 0 ){
                    result.add(num);
                    map1.put(num, map1.get(num) - 1);
                }
            }

            int[] answer = new int[result.size()];

            for(int i=0; i< answer.length; i++){
                answer[i] = result.get(i);
            }

            return answer;
        }

        // Assume the arrays are sorted.
        public int[] intersect3(int[] nums1, int[] nums2) {
            List<Integer> list = new ArrayList<>(); // Using a List instead of a Set.
            Arrays.sort(nums1); Arrays.sort(nums2);
            int i=0; int j=0;

            while( i < nums1.length && j < nums2.length ) {
                if(nums1[i] == nums2[j]){
                    list.add(nums1[i]);
                    i++; j++;
                } else if(nums1[i] < nums2[j]){
                    i++;
                } else {
                    j++;
                }
            }

            int[] result = new int[list.size()];
            List<Integer> list1 = new ArrayList<>(list);
            for(int k=0; k<result.length;k++){
                result[k] = list1.get(k);
            }
            return result;
        }
}
