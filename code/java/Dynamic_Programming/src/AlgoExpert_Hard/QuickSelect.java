package AlgoExpert_Hard;

public class QuickSelect {
	public static void main(String[] args) {
		int[] array = {8, 5, 2, 9 , 7, 6, 3 };
		int k = 3; 
	}
	
	public static int quickselect(int[] array, int k) {
		partition(array, 0, array.length-1, k);
		return array[k-1]; 
	}

	public static int partition(int[] array, int start, int end, int k){
		if(start>end){
			return -1; 
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
		
		if(right == k-1) {
			return pivot ; 
		} else if (right > k-1) {
			return partition(array, start, right-1, k);
		} else {
			return partition(array, right+1, end, k);
		}
		
	}
	
}
