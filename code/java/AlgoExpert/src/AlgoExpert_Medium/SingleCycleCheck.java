package AlgoExpert_Medium;

public class SingleCycleCheck {

	public static void main(String[] args) {
		
		int[] array = {1, 2, 3, 4, -2, 3, 7, 8, -26} ; 
		
		System.out.println( hasSingleCycle(array) );
	} 
	
	public static boolean hasSingleCycle(int[] array) {

		boolean[] visited = new boolean[array.length] ; 
		
		int count = 0; 
		int length = array.length; 
		int index = 0; // starting index, can also take any other index as the starting index. 
		
		for( int i=0 ; i<array.length ; i++ ) {
			
		    index =  index + array[index]   ; 
		    
			if( index >= 0 ) {
				index = index % length ; 
			} else {
				index =  (index%length + length) % length; 
			}
			
			System.out.println("index is : " + index);
			
			if( !visited[index] ) {
				count++ ; 
				visited[index] = true; 
			} else {
				return false; 
			}		
		}
		
		System.out.println("Final index is: " + index );
		System.out.println("Final count is : " +count);
		
		if(index == 0 && count == array.length) {
			return true ; 
		} else {
			return false; 
		}
		
	 }
	
}