package DP;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermuteStringDuplicate {
	
	public static void main(String[] args) {
		
		char[] c = { 'A', 'A', 'B', 'C' } ;
		
		Map<Character, Integer> map = new HashMap<>() ; 
		
		int[] count = new int[3]; /// How to set this size ? 
		
		for(int i=0 ; i<count.length; i++) {
			
			if(map.containsKey(c[i])) {
				count[map.get(c[i])]++;  
			} else {
				map.put(c[i], i);
				count[map.get(c[i])] = 1; 
			}
			
		}
		
		String result = ""; 
		permute(map, result) ; 
		
        permute("AABC".toCharArray()).forEach(s -> System.out.println(s));
			
	}
	
	public static void permute ( Map map, String result) {
	
	}
	
	 public static List<String> permute(char input[]) {
		 
	        Map<Character, Integer> countMap = new TreeMap<>();
	        
	        for (char ch : input) {
	            countMap.compute(ch, (key, val) -> { // amazing technique 
	                if (val == null) {
	                    return 1;
	                } else {
	                    return val + 1;
	                }
	            });           
	        }
	        
	        char str[] = new char[countMap.size()]; // array with distinct characters. 
	        int count[] = new int[countMap.size()]; // count array. 
	        int index = 0;
	        
	        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
	            str[index] = entry.getKey();
	            count[index] = entry.getValue();
	            index++;
	        }
	        
	        List<String> resultList = new ArrayList<>();
	        char result[] = new char[input.length];
	        
	        permuteUtil(str, count, result, 0, resultList);
	        
	        return resultList;
	        
	    }

	    public static void permuteUtil(char str[], int count[], char result[], int level, List<String> resultList) {
	    	
	        if (level == result.length) { // Base Case 
	            resultList.add(new String(result));
	            return;
	        }

	        for(int i = 0; i < str.length; i++) {
	        	
	            if(count[i] == 0) { // Constraint 
	                continue;
	            }
	            result[level] = str[i];
	            
	            count[i]--;
	            
	            permuteUtil(str, count, result, level + 1, resultList); // Choice 
	            
	            count[i]++; // Backtrack and increase the count again. 
	        }
	        
	    }

	    private static void printArray(char input[]) {
	        for(char ch : input) {
	            System.out.print(ch);
	        }
	        System.out.println();
	    }
	
	
}
