package AlgoExpert_Hard;

public class InterweavingStringsBottomUp {
 
	public static void main(String[] args) {
		
	}
	
// Going forward in bottom up. cache[i][j]: Can the strings ending at indices i and j be interwoven into string 3 ending at index i+j-1.
	
	 public boolean isInterleaved(String str1, String str2, String str3){
	        boolean T[][] = new boolean[str1.length() +1][str2.length() +1]; // All fields are false by default. 
	        
	        if(str1.length() + str2.length() != str3.length()){
	            return false;
	        }
	        
	        for(int i=0; i < T.length; i++){
	            for(int j=0; j < T[i].length; j++){
	            	
	                int l = i + j -1;
	                
	                if(i == 0 && j == 0){
	                    T[i][j] = true;
	                }
	                
	                else if(i == 0){
	                    if(str3.charAt(l) == str2.charAt(j-1)){
	                        T[i][j] = T[i][j-1];
	                    }
	                }
	                
	                else if(j == 0){
	                    if(str1.charAt(i-1) == str3.charAt(l)){
	                        T[i][j] = T[i-1][j];
	                    }
	                }
	                
	                else{
	                    T[i][j] = (str1.charAt(i-1) == str3.charAt(l) ? T[i-1][j] : false) || (str2.charAt(j-1) == str3.charAt(l) ? T[i][j-1] : false);
	                }
	            }
	        }
	        return T[str1.length()][str2.length()];
	    }
	
	 // Going in the same path as the recursion subproblems get solved. 
	 // T[i][j]: Can the strings starting at indices i and j be interwoven into string starting at index i+j+1. 
	 
	 public boolean isInterleaved2(String str1, String str2, String str3){
	        boolean T[][] = new boolean[str1.length() +1][str2.length() +1]; // All fields are false by default. 
	        
	        if(str1.length() + str2.length() != str3.length()){
	            return false;
	        }
	        
	        for(int i=str1.length(); i >=0; i--){
	            for(int j=str2.length(); j >=0 ; j--){
	            	
	                int l = i + j ;
	                
	                if(i == str1.length() && j == str2.length()){
	                    T[i][j] = true;
	                }
	                
	                else if(i == str1.length()){
	                    if(str3.charAt(l) == str2.charAt(j)){
	                        T[i][j] = T[i][j+1];
	                    }
	                }
	                
	                else if(j == str2.length()){
	                    if(str1.charAt(i) == str3.charAt(l)){
	                        T[i][j] = T[i+1][j];
	                    }
	                }
	                
	                else{
	                    T[i][j] = (str1.charAt(i) == str3.charAt(l) ? T[i+1][j] : false) || (str2.charAt(j) == str3.charAt(l) ? T[i][j+1] : false);
	                }
	            }
	        }
	        return T[0][0];
	    }
	 
}
