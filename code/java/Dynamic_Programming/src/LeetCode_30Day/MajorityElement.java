package LeetCode_30Day;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

	public static void main(String[] args) {
		System.out.println(majorityElement(new int[] {3, 2, 3} ) );
	}
	
	public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); 
              
        int length = nums.length/2; 
        
        for(int i=0 ; i<nums.length ; i++ ) {
            map.merge(nums[i], 1, Integer::sum ) ;
        }
        
        int max = -1;
        System.out.println(length);
                
        for(Map.Entry<Integer, Integer> object: map.entrySet()){
              	if(map.get(object.getKey()) > length) {
              		max = object.getKey() ;
              	}
        }
        
        return max; 
    }

}


/*
The majority element is the element that appears more than n/2  times.
You may assume that the array is non-empty and the majority element always exist in the array.
*/