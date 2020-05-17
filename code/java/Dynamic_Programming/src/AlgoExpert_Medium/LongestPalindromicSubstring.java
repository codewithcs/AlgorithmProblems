package AlgoExpert_Medium;


public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		
		int[] a = {1,2 } ; 
		
		//System.out.println(longestPalindromicSubstring("abcdefghfedcbaa"));
		//longestPalindromicSubstring2("#a#b#a#b#a#b#a#".toCharArray()); 
		
	//	System.out.println(manachersAlgorithm("babad", 5));
		
		System.out.println(longestPalindrome(""));
	}
	
	
	// O(n^2) time. 
	public static String longestPalindromicSubstring(String str) {

		int maxLength = 1; int mid = -1; 
		
		for(int i=0 ; i<str.length()-1 ; i++){
			
			int oddLength = 0; 
			
			if(i>0) /// imp edge case 
				oddLength = findPalindromeLength(str, i, i-1, i+1) + 1; 
			
			int evenLength = findPalindromeLength(str, i, i, i+1); 
			
			int currentLength = Math.max(oddLength, evenLength); 
			
			if(currentLength > maxLength){
				mid = i; 
				maxLength = currentLength ; 
			}
			
		}
	
		boolean isOdd = (maxLength % 2 ) == 1 ; // mid is always >= maxLength/ 2
		
		int maxLeft = isOdd ? mid - maxLength/2 : mid - maxLength/2 + 1 ;
		
		int maxRight = mid+maxLength/2; 

		if(maxLength == 1) { //
			return str ; 
		}
		
		return str.substring(maxLeft, maxRight+1); 
		
	}
	
	public static int findPalindromeLength (String str, int middle, int left, int right) {
		int length = 0; 
				
		while(left >=0 && right <= str.length()-1) {
		
			if( str.charAt(left) == str.charAt(right) ) {
				left-- ; 
				right++ ;		
				length = length + 2;
			} else {
				break; 
			}
		
		}
		
		return length ; 
		
	}
	
	
	// O(n): Manacher's Algorithm 
	public static void longestPalindromicSubstring2(char[] str) {
		
		System.out.println("Length of str[] is : " + str.length  );		
		
		int[] P = new int[str.length]; 
		int C=0, R=0; int maxLength = -1 ; 
		
		for(int i=1; i<str.length-1 ; i++ ) {
			
			int mirror = 2*C - i; 
			
			if(i < R) {
				P[i] = Math.min(R-i, P[mirror]) ; 
				System.out.println(P[i]);
			}
			
			
			if( (i + (1 + P[i])) < str.length && (i - (1+ P[i] ) ) >= 0 ) {
			
				while( str[i + (1 + P[i])] == str[ i - ( 1 + P[i] )] ) {  	
					P[i]++ ;
					
					if( (i + (1 + P[i])) > str.length-1 || (i - (1+ P[i] ) ) < 0 ) {
						break ; 
					}
					
				}
			}
			
				if( i + P[i] > R ) {
					C = i; 
					R = i + P[i] ; 
				}
				
				System.out.println("P[" + i + "] is :  " + P[i]);
				
				if(P[i] > maxLength){
                    maxLength = P[i] ;
                }
				
				
			}
		
			System.out.println("maxLength is : " + maxLength );
			System.out.println("Centre is: " + C);
			
			String a = "abc" ; ; 
			
//			  String a = str.substring(C-maxLength, C+maxLength+1) ; 
//		        
//		       a.replaceAll("#", "") ;
//		        
//		        return a ;
		}

	static int manachersAlgorithm(String s, int N) {
		
	    String str = getModifiedString(s, N);
	    int len = (2 * N) + 1;
	    int[] P = new int[len];
	    int c = 0; //stores the center of the longest palindromic substring until now
	    int r = 0; //stores the right boundary of the longest palindromic substring until now
	    int maxLen = 0;
	    int maxC = -1; 
	    
	    for(int i = 0; i < len; i++) {
	        //get mirror index of i
	        int mirror = (2 * c) - i;
	        
	        //see if the mirror of i is expanding beyond the left boundary of current longest palindrome at center c
	        //if it is, then take r - i as P[i]
	        //else take P[mirror] as P[i]
	        if(i < r) {
	            P[i] = Math.min(r - i, P[mirror]);
	        }
	        
	        //expand at i
	        int a = i + (1 + P[i]);
	        int b = i - (1 + P[i]);
	        while(a < len && b >= 0 && str.charAt(a) == str.charAt(b)) {
	            P[i]++;
	            a++;
	            b--;
	        }
	        
	        //check if the expanded palindrome at i is expanding beyond the right boundary of current longest palindrome at center c
	        //if it is, the new center is i
	        System.out.println();
	        System.out.println("i is : " + i );
	        System.out.println("P[" + i + "] is :  " + P[i]);
			System.out.println("i + P[i] is : " + ( i + P[i] ) );
	
			System.out.println("right is : " + r );
			System.out.println();

	        if(i + P[i] > r) {
	            c = i;
	            r = i + P[i];
	            
	            if(P[i] > maxLen) { //update maxlen
	                maxLen = P[i];
	                maxC  = c ; 
	            }
	        }
	        

	        
	    }
	    
	    System.out.println("maxLen is : " + maxLen);
	    System.out.println("Max Centre is : " +  maxC);
	    return maxLen;
	}

		static String getModifiedString(String s, int N){
		    StringBuilder sb = new StringBuilder();
		    for(int i = 0; i < N; i++){
		        sb.append("#");
		        sb.append(s.charAt(i));
		    }
		    sb.append("#");
		    return sb.toString();
		}
		
		
		public static String longestPalindrome(String s) {
	        
	         String str = "#" ;         
	        for(char c: s.toCharArray()) {
	            str = str + c ; 
	            str = str + '#' ;
	        }
	        
	        int[] P = new int[str.length()]; 
			int C=0, R=0;  int maxLength = -1; int maxCentre = -1;
			
			for(int i=1; i<str.length()-1 ; i++ ) {
				
				int mirror = 2*C - i; 
				
				if(i < R) {
					P[i] = Math.min(R-i, P[mirror]) ; 
				}
				
				
				if( (i + (1 + P[i])) < str.length() && (i - (1+ P[i] ) ) >= 0 ) {
				
					while( str.charAt(i + (1 + P[i])) == str.charAt( i - ( 1 + P[i] )) ) {  	
						P[i]++ ;
						
						if( (i + (1 + P[i])) > str.length()-1 || (i - (1+ P[i] ) ) < 0 ) {
							break ; 
						}
						
					}
				}	
					if( i + P[i] > R ) {
						C = i; 
						R = i + P[i] ; 
					}
	            
	                if(P[i] > maxLength){
	                    maxLength = P[i] ;
	                    maxCentre = C; 
	                }
					
				}

	        
	        String a = str.substring(maxCentre-maxLength, maxCentre+maxLength+1) ; 
	        
	       // a.replace("#", "") ;
	      
	        String result = "" ;
	        
	        for(char c1 : a.toCharArray()) {
	            if( c1 != '#') {
	                result = result + c1 ;
	            }         
	        }
	        
	        return result ;
	    }
		
	}
	
	
	
	
	

