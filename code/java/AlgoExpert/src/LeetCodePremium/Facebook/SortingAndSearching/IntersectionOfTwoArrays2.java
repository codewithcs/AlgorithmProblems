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

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfTwoArrays2 {
        public int[] intersect(int[] nums1, int[] nums2) {
            List<Integer> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();
            int i=0; int j=0;

            boolean firstSmaller = nums1.length < nums2.length;
            if(firstSmaller){
                traverse(nums2, nums1, result, current, j, i);
            } else {
                traverse(nums1, nums2, result, current, j, i);
            }

            System.out.println("result.size() is : " + result.size());
            int[] answer = new int[result.size()];

            for(int k=0; k< answer.length; k++){
                answer[k] = result.get(k);
            }
            return answer;
        }

        public void traverse(int[] nums1, int[] nums2, List<Integer> result, List<Integer> current, int j, int i){
            boolean firstMatch = false;
            while(i< nums1.length && j< nums2.length){ // nums2 is smaller.
                System.out.println("i is : " + i);
                System.out.println("j is : " + j);
                System.out.println("nums1[i] is : " + nums1[i]);
                System.out.println("nums2[j] is : " + nums2[j]);

                if(nums1[i] == nums2[j]){
                    current.add(nums1[i]);
                    i++; j++; firstMatch = true;
                    //   System.out.println("current is : " + current);
                    if(current.size() > result.size()){
                        result = new ArrayList<>(current);
                        System.out.println("result is : " + result);
                    }
                } else {
                    if(firstMatch){ // reset the pointer in smaller array.
                        j = 0;
                        if(current.size() > result.size()){
                            System.out.println("current is : " + current);
                            result = new ArrayList<>(current);
                            System.out.println("result is : " + result);
                            current = new ArrayList<>();
                            System.out.println("current is : " + current);
                            System.out.println("result is : " + result);
                            System.out.println();
                        }
                        firstMatch = false;
                    } else {
                        i++;
                    }
                }
            }
        }
}
