package AlgoExpert_Hard;

public class ShiftedBinarySearch {

	public static void main(String[] args) {
		int[] array = { 45, 61, 71, 72, 73, 0, 1, 21, 33, 45 };
		int target = 33; 
		System.out.println(shiftedBinarySearch(array, target));
	}
	
	 public static int shiftedBinarySearch(int[] array, int target) {
		 int left = 0 ; 
		 int right = array.length -1 ; 
		 int middle = left + (right-left)/2; 
		 
		 while(left<=right) { // Important check
			 
			 if(array[middle] == target) {
				 return middle; 
			 }
			 
			 if(array[left] <= array[middle]) { // left half is sorted. 
				 
				 if(target>= array[left] && target<array[middle]) { // explore left
					 right = middle-1; 
				 } else { // explore right. 
					 left = middle+1;
				 }
			 } 
			 
			 else { // left half is not sorted. 
				 
				 if(target>array[middle] && target<=array[right]) {
					 left = middle +1;
				 } else {
					 right = middle-1;
				 }
				 
			 }
			 
			 middle = left + (right-left)/2 ;
			 
		 }
	 
		 return -1;
	 }
	 
	 // Recursive: O(logn) time and O(logn) space 
	 public static int shiftedBinarySearchRecursive(int[] array, int target) {
		 return shiftedBinarySearchRecursiveHelper(array, target, 0, array.length-1); 
	 }
	 
	 public static int shiftedBinarySearchRecursiveHelper(int[] array, int target, int left, int right) {
		 if(left > right) {
			 return -1 ; 
		 }
		 
		 int middle = (left+right)/2 ; 
		 int potentialMatch = array[middle]; 
		 int leftNum = array[left]; 
		 int rightNum = array[right]; 
		 
		 if(target == potentialMatch) {
			 return middle ; 
		 } else if( leftNum<= potentialMatch ) { // left subarray is sorted
		 
			 if(target < potentialMatch && target >= leftNum ) {
				 return shiftedBinarySearchRecursiveHelper(array, target, left, middle-1);
			 } else {
				 return shiftedBinarySearchRecursiveHelper(array, target, middle+1, right);
			 }
			 
		 } 
		 
		 else {
			 if(target > potentialMatch && target <= rightNum ) {
				 return shiftedBinarySearchRecursiveHelper(array, target, middle+1, right);
			 } else {
				 return shiftedBinarySearchRecursiveHelper(array, target, left, middle-1);
			 }
		 }
		 
	 }

	 // Iterative : O(logn) time and O(1) space
	 public static int shiftedBinarySearchIterative2(int[] array, int target) {
		 return shiftedBinarySearchIterative2Helper(array, target, 0, array.length-1);
	 }
	 
	 public static int shiftedBinarySearchIterative2Helper(int[] array, int target, int left, int right) {
		 
		 while(left <= right ) {
			 int middle = (left+right)/2;
			 int potentialMatch = array[middle]; 
			 int leftNum = array[left]; 
			 int rightNum = array[right]; 
			 
			 if(target == potentialMatch) {
				 return middle; 
			 } else if(leftNum <= potentialMatch) {
				 
				 if(target < potentialMatch && target >= leftNum ) {
					 right = middle -1; 
				 } else {
					 left = middle +1; 
				 }
				 
			 } else {
				 if(target > potentialMatch && target <= rightNum ) {
					 left = middle + 1; 
				 } else {
					 right = middle -1 ; 
				 }
				 
			 }
		 }
		
		 return -1; 
	 }
	 
	 // O(n) time
	 public static int shiftedBinarySearchModulusApproach(int[] array, int target) {
		 return -1; 
	 }

}

