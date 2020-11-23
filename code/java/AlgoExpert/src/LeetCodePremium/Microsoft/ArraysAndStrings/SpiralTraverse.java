package LeetCodePremium.Microsoft.ArraysAndStrings;

import java.util.ArrayList;
import java.util.List;

public class SpiralTraverse {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println(spiralOrder(matrix));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length == 0){
            return new ArrayList<Integer>();
        }

        int row = 0; int column = 0 ;
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        System.out.println();
        System.out.println("rowCount is : " + rowCount);
        System.out.println("column count is : " + columnCount);
        System.out.println();
        List<Integer> answer = new ArrayList<>();

        traverse(answer, 0, 0, row, column, rowCount, columnCount, matrix);

        return answer;

    }

    public static void traverse(List<Integer> answer, int visitedNodes, int currentDirection, int row, int column, int rowBound, int columnBound, int[][] matrix){

    }

}
