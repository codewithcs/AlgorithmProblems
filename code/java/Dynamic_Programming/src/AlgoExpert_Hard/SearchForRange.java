package AlgoExpert_Hard;

public class SearchForRange {
	public static void main(String[] args) {
		
		
	}
	
	 public static int[] searchForRange(int[] array, int target) {

		 int left = 0; 
		 int right = array.length-1; 
		 int middle = left+(right-left)/2;
		 
		 int rightIdx = -1; 
		 int leftIdx = -1; 
		 
		 // Find left extreme
		 while(left<=right) {
			 
			 if(array[middle] == target) {
				 leftIdx = middle; 
				 
				 if(middle >0 && array[middle-1] == target) {
					 leftIdx = middle-1; 
					 right=middle-2; 
				 } else {
					 break; 
				 }
				 
			 }
			 
			 else {
				 if(target > array[middle]) {
					 left = middle+1; 
				 } else {
					 right = middle-1; 
				 }
			 }
			 
			 middle = left + (right-left)/2;
		 }

		 left = 0; right = array.length-1; middle = left + (right-left)/2;
		 
		 while(left<=right) {
			 
			 if(array[middle] == target) {
				 rightIdx = middle; 
				 
				 if(middle <array.length-1 && array[middle+1] == target) {
					 rightIdx = middle+1; 
					 left=middle+2; 
				 } else {
					 break; 
				 }
				 
			 }
			 
			 else {
				 
				 if(target > array[middle]) {
					 left = middle+1; 
				 } else {
					 right = middle-1; 
				 }
				 
			 }
			 
			 middle = left + (right-left)/2;
		 }
		
		return new int[] {left, right} ; 
		 
	 }
	 
	 public static int[] searchForRangeIterative() {
		 int[] array = null ; int target = 23; 
		 
		 int leftIdx = searchForRangeIterative(array, target, 0, array.length-1, false); 
		 int rightIdx = searchForRangeIterative(array, target, 0, array.length-1, true); 
		 
		 return new int[] {leftIdx, rightIdx}; 
	 }
	 
	 // Cleaner Code, Merge the logic into one while loop.
	 public static int searchForRangeIterative(int[] array, int target, int left, int right, boolean goRight) {
		 
		 int middle = left + (right-left)/2;
		 int leftIdx = -1 ; 
		 int rightIdx = -1; 
		 
		 while(left<=right) {
			 
			 if(array[middle] == target) {
				 
				 if(goRight) {
					 rightIdx = middle; 
					 
					 if(middle <array.length-1 && array[middle+1] == target) {
						 rightIdx = middle+1; 
						 left=middle+2; 
					 } else {
						 break; 
					 }
				 }
				 else { // go left. 
					 leftIdx = middle; 
					 
					 if(middle >0 && array[middle-1] == target) { // can apply some other logic also. 
						 leftIdx = middle-1; 
						 right=middle-2; 
					 } else {
						 break; 
					 }
				 }
				 
			 }
			 
			 else {
				 
				 if(target > array[middle]) {
					 left = middle+1; 
				 } else {
					 right = middle-1; 
				 }
				 
			 }
			 
			 middle = left + (right-left)/2;
		 }
		
		if(goRight) {
			return rightIdx;
		} else {
			return leftIdx; 
		}
				 
	 }

	 // Recursive Solution
	 public static int[] searchForRangeRecursive() {
		 int[] array = null ; int target = 23; 
		 
		 int leftIdx = searchForRangeRecursive(array, target, 0, array.length-1, -1, -1, false); 
		 int rightIdx = searchForRangeRecursive(array, target, 0, array.length-1, -1, -1, true); 
		 
		 return new int[] {leftIdx, rightIdx}; 
	 }
	 
	 public static int searchForRangeRecursive(int[] array, int target, int left, int right, int leftIdx, int rightIdx, boolean goRight) {
		 
		 if(left>right) { // Base Case 
			 if(goRight) {
				 return rightIdx;
			 } else {
				 return leftIdx;
			 }
		 }
		 
		 int middle = left + (right-left)/2;

		 if(array[middle] == target) {
				 
				 if(goRight) {
					 rightIdx = middle; 
					 
					 if(middle <array.length-1 && array[middle+1] == target) {
						 rightIdx = middle+1; 
						 return searchForRangeRecursive(array, target, middle+2, right, leftIdx, rightIdx, goRight);
					 } else { 
						 return rightIdx; 
					 }
				 }
				 else { // go left. 
					 leftIdx = middle; 
					 
					 if(middle >0 && array[middle-1] == target) {
						 leftIdx = middle-1; 
						 return searchForRangeRecursive(array, target, left, middle-2, leftIdx, rightIdx, goRight); 
					 } else {
						 return leftIdx; 
					 }
				 }
				 
			 }
			 
			 else {
				 
				 if(target > array[middle]) {
					 return searchForRangeRecursive(array, target, middle+1, right, leftIdx, rightIdx, goRight);
				 } else {
					 return searchForRangeRecursive(array, target, left, middle-1, leftIdx, rightIdx, goRight);
				 }
				 
			 }

	 }

}
