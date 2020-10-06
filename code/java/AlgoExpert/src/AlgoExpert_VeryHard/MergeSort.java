package AlgoExpert_VeryHard;

import java.util.Arrays;

public class MergeSort {

    // AlgoExpert solution 1
    public static int[] mergeSort(int[] array){

        if(array.length <=1){ // Base Case
            return array;
        }

        int middleIdx = array.length / 2;
        int[] leftHalf = Arrays.copyOfRange(array, 0, middleIdx);
        int[] rightHalf = Arrays.copyOfRange(array, middleIdx, array.length);

        return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    public static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf){
        int[] sortedArray = new int[leftHalf.length + rightHalf.length];
        int k=0;
        int i=0;
        int j=0;

        while ( i< leftHalf.length && j < rightHalf.length){
            if(leftHalf[i] <= rightHalf[j]){
                sortedArray[k++] = leftHalf[i++];
            } else {
                sortedArray[k++] = rightHalf[j++];
            }
        }

        while(i < leftHalf.length){
            sortedArray[k++] = leftHalf[i++];
        }

        while( j< rightHalf.length){
            sortedArray[k++] = rightHalf[j++];
        }

        return sortedArray;
    }

    // ALgoExpert solution 2:
    /*
    Passing in the pointers in the recursive and merge step.
     */
    public static int[] mergeSort2(int[] array){
        if(array.length <= 1){
            return array;
        }
        int[] auxiliaryArray = array.clone();
        mergeSort2(array, 0, array.length-1, auxiliaryArray);
        return array;
    }

    public static void mergeSort2(int[] mainArray, int startIdx, int endIdx, int[] auxiliaryArray){
        if(startIdx == endIdx){ // Base Case.
            return ;
        }
        int middleIdx = (startIdx + endIdx)/ 2;
        // Pass in the main array as an auxiliary array and auxiliary array as a main array.
        mergeSort2(auxiliaryArray, startIdx, middleIdx, mainArray);
        mergeSort2(auxiliaryArray, middleIdx+1, endIdx, mainArray);
        doMerge(mainArray, startIdx, middleIdx, endIdx, auxiliaryArray);
    }

    public static void doMerge(int[] mainArray, int startIdx, int middleIdx, int endIdx, int[] auxiliaryArray){
        int k = startIdx;
        int i= startIdx;
        int j=middleIdx +1;

        while(i <= middleIdx && j<= endIdx){
            if(auxiliaryArray[i] <= auxiliaryArray[j]){
                mainArray[k++] = auxiliaryArray[i++];
            } else {
                mainArray[k++] = auxiliaryArray[j++];
            }
        }

        while( i<= middleIdx){
            mainArray[k++] = auxiliaryArray[i++];
        }

        while(j <=  endIdx){
            mainArray[k++] = auxiliaryArray[j++];
        }
    }

}
