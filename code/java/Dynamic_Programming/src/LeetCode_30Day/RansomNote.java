package LeetCode_30Day;

import java.util.Arrays;

public class RansomNote {

	public static void main(String[] args) {
		String ransomNote = "aa" ;
		String magazine = "aab";
		
		System.out.println(canConstruct(ransomNote, magazine));
		
	}
	
	// Microsoft Question. 
	
	public static boolean canConstruct(String ransomNote, String magazine) {
        
        int i= 0 ; int j = 0;
        
         if(ransomNote.length()==0 && magazine.length()==0) return true ;
        
        if(ransomNote.length()==0) return true ;
        
        if(magazine.length()==0) return false; 
        
        char [] chars = ransomNote.toCharArray()  ;
        Arrays.sort(chars);
        String sortedRansomNote = new String( chars) ;
        
        char [] chars2 = ransomNote.toCharArray()  ;
        Arrays.sort(chars2);
        String sortedMagazine = new String(chars2) ;
        
        while(i<sortedRansomNote.length() && j < sortedMagazine.length()  ) {
            
        	System.out.println(i);
        	System.out.println(j);
        	
            if( sortedRansomNote.charAt(i) == sortedMagazine.charAt(j)) {
                i++ ;
                j++ ;
                System.out.println("i is : " + i );
            } 
            else {
            j++ ;
            }
            
            if(i==sortedRansomNote.length()) {
                return true;
            }
            
        }
        
        System.out.println("i is : " + i );
        System.out.println("Length of ransom note is : " + ransomNote.length());
        
        System.out.println("j is : " + j);
        System.out.println("Length of magazine is : " + magazine.length());
       
        return false; 
        
    }
	
	// Can also solve this by using a HashMap. 
	public static boolean canConstruct1(String ransomNote, String magazine) {
		
		return false ;
	}

}
