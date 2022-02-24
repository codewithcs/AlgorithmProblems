package AlgoExpert_ExtremelyHard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {

    public static void main(String[] args) {
        int[] array = { 3, 1 , 5, 2, 6, 4, 9 };

        System.out.println(longestIncreasingSubsequence3(array));
    }

    // Solution 1: O(n^2) time | O(n) space
    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        int[] sequences = new int[array.length];
        Arrays.fill(sequences, Integer.MIN_VALUE); // Initialize
        int[] lengths = new int[array.length];
        Arrays.fill(lengths, 1);

        int maxLengthIdx = 0;

        for (int i = 0; i < array.length; i++) {
            int currentNum = array[i];
            for (int j = 0; j < i; j++) {
                int otherNum = array[j];
                if (otherNum < currentNum && lengths[j] + 1 >= lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    sequences[i] = j;
                }
            }
            if (lengths[i] >= lengths[maxLengthIdx]) {
                maxLengthIdx = i;
            }
        }

        return buildSequence(array, sequences, maxLengthIdx);
    }

    public static List<Integer> buildSequence(int[] array, int[] sequences, int currentIdx) {
        List<Integer> sequence = new ArrayList<>();
        while (currentIdx != Integer.MIN_VALUE) {
            sequence.add(0, array[currentIdx]); // add the element at the head so that the sequence is increasing.
            currentIdx = sequences[currentIdx];
        }
        return sequence;
    }

    // Solution 2: O(nlogn) time | O(n) space
    public static List<Integer> longestIncreasingSubsequence2(int[] array) {
        int[] sequences = new int[array.length];
        int[] indices = new int[array.length+1];
        Arrays.fill(indices, Integer.MIN_VALUE);

        int length = 0; // length of current lis.
        for(int i=0; i< array.length; i++){
            int num = array[i];
            int newLength = binarySearch(1, length, indices, array, num);
            sequences[i] = indices[newLength-1];
            indices[newLength] = i;
            length = Math.max(length, newLength);
        }

        System.out.println("Length of lis is : " + length);

        System.out.println("Sequence array is : ");
        for(int sequence: sequences){
            System.out.print(sequence + ", ");
        }
        System.out.println();
        System.out.println("increasingSubsequence array is : ");
        for(int index: indices){
            System.out.print(index + ", ");
        }

        return buildSequence(array, sequences, indices[length]);
    }

    public static int binarySearch(int startIdx, int endIdx, int[] indices, int[] array, int num){
        if(startIdx > endIdx){ // Base Case
            return startIdx;
        }
        int middleIdx = (startIdx + endIdx)/ 2;
        if(array[indices[middleIdx]] < num){ // ensures strictly increasing.
            startIdx = middleIdx + 1;
        } else {
            endIdx = middleIdx -1 ; // will set endIdx when equality or > num
        }
        return binarySearch(startIdx, endIdx, indices, array, num);
    }

    // won't work.
    public static List<Integer> longestIncreasingSubsequence3(int[] array) {
        int[] sequences = new int[array.length];
       // Arrays.fill(sequences, Integer.MIN_VALUE);
        int[] increasingSubsequence = new int[array.length+1]; // final values in this won't be optimal.
        Arrays.fill(increasingSubsequence, Integer.MIN_VALUE);

        int length = 0; // length of longest lis.
        for(int i=0; i< array.length; i++){
            int num = array[i];
            int newLength = binarySearch2(1, length, increasingSubsequence, array, num);
            // difficulty in setting sequences[]

          //  sequences[i] = newLength == 1 ? Integer.MIN_VALUE : what to write here ? ;
            // better to have indices[] as in the solution 2.
            increasingSubsequence[newLength] = num;
            length = Math.max(length, newLength);
        }
        System.out.println("Length of lis is : " + length);

        System.out.println("Sequence array is : ");
        for(int sequence: sequences){
            System.out.print(sequence + ", ");
        }
        System.out.println();
        System.out.println("increasingSubsequence array is : ");
        for(int incSubsequence: increasingSubsequence){
            System.out.print(incSubsequence + ", ");
        }

        return buildSequence(array, sequences, length-1);
    }

    public static int binarySearch2(int startIdx, int endIdx, int[] increasingSubsequence, int[] array, int num){
        if(startIdx > endIdx){
            return startIdx;
        }
        int middleIdx = (startIdx + endIdx)/ 2;
        if(increasingSubsequence[middleIdx] < num){
            startIdx = middleIdx + 1;
        } else {
            endIdx = middleIdx -1 ;
        }
        return binarySearch(startIdx, endIdx, increasingSubsequence, array, num);
    }


}