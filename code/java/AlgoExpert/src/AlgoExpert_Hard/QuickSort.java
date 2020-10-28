package AlgoExpert_Hard;

public class QuickSort {
	public static void main(String[] args) {
		int[] array = { 8, 5, 2, 9, 5, 6, 3 } ;
		System.out.println(quickSort(array));
		
		for(int a : array) {
			System.out.print(a + " : ");
		}
	}
	
	public static int[] quickSort(int[] array) {
		partition(array, 0, array.length-1);
		return array;
	}

	public static void partition(int[] array, int start, int end){
		if(start>=end){ // Recursion Base Case.
			return; 
		}
		int pivot = array[start] ; 
		int left = start+1; 
		int right = end;
	
		while(left<=right){
				if(array[left]>pivot && array[right]<pivot){
					int temp = array[left]; 
					array[left] = array[right];
					array[right] = temp; 
					left++; right--; 
				} else if(array[left] <= pivot){ // Equality is important 
					left++;
				} else if(array[right] >= pivot){
					right--; 
					}
		}
	
		// swap pivot with right pointer
		int temp = array[right];
		array[right] = pivot ; 
		array[start] = temp; 
	
		// Call the recursive function on smaller subarray first. 
		
		boolean leftSubArrayIsSmaller = right-1-start < end-right-1 ;
		if(leftSubArrayIsSmaller) {
			partition(array, start, right-1);
			partition(array, right+1, end);
		} else {
			partition(array, right+1, end);
			partition(array, start, right-1);
		}
		
	}
}
