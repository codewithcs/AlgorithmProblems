package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SameBSTs2 {
	public static void main(String[] args) {
		 List<Integer> arrayOne = new ArrayList<>(Arrays.asList(new Integer[] {10, 15, 8, 12, 94, 81, 5, 2, 11})) ;
		 List<Integer> arrayTwo = new ArrayList<>(Arrays.asList(new Integer[] {10, 8, 5, 15, 2, 12, 11, 94, 81})) ;
		 
		 System.out.println(sameBsts(arrayOne, arrayTwo));
	 }
	
	 public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		 return areSameBsts(arrayOne, arrayTwo, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE) ; 
	 }
	 
	 public static boolean areSameBsts(List<Integer> arrayOne, List<Integer> arrayTwo,
			 int rootIdxOne, 
			 int rootIdxTwo, 
			 int minVal, 
			 int maxVal) {
		 
		 if(rootIdxOne == -1 || rootIdxTwo == -1) return rootIdxOne == rootIdxTwo ; // Base Case. When both become -1, it is true. Similar to when the both the arrays become empty in previous method.  
		 
		 if(arrayOne.get(rootIdxOne).intValue() != arrayTwo.get(rootIdxTwo).intValue()) return false; 
		 
		 int leftRootIdxOne = getIdxOfFirstSmaller(arrayOne, rootIdxOne, minVal) ; 
		 int leftRootIdxTwo = getIdxOfFirstSmaller(arrayTwo, rootIdxTwo, minVal) ; 
		 int rightRootIdxOne = getIdxOfFirstBiggerOrEqual(arrayOne, rootIdxOne, maxVal) ; 
		 int rightRootIdxTwo = getIdxOfFirstBiggerOrEqual(arrayTwo, rootIdxTwo, maxVal) ; 

		 int currentValue = arrayOne.get(rootIdxOne) ;
		 boolean leftAreSame = areSameBsts(arrayOne, arrayTwo, leftRootIdxOne, leftRootIdxTwo, minVal, currentValue); 
		 boolean rightAreSame = areSameBsts(arrayOne, arrayTwo, rightRootIdxOne, rightRootIdxTwo, currentValue, maxVal); 
		 
		 return leftAreSame && rightAreSame ; 
	 }
	 
	 public static int getIdxOfFirstSmaller(List<Integer> array, int startingIdx, int minVal) {
		 for(int i= startingIdx+1 ; i<array.size() ; i++) {
			 if(array.get(i).intValue() < array.get(startingIdx).intValue() && array.get(i).intValue() >= minVal) {
				 return i ; 
			 }
		 }
		 return -1 ; 
	 }
	 
	 public static int getIdxOfFirstBiggerOrEqual(List<Integer> array, int startingIdx, int maxVal) {
		 for(int i= startingIdx+1 ; i<array.size() ; i++) {
			 if(array.get(i).intValue() >= array.get(startingIdx).intValue() && array.get(i).intValue() < maxVal) {
				 return i ; 
			 }
		 }
		 return -1 ; 
	 }
	 
}
