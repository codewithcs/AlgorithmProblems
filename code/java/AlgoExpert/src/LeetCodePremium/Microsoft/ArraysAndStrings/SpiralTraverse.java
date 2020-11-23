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

        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        List<Integer> answer = new ArrayList<>();

        int rowStart = 0;
        int rowEnd = rowCount-1;
        int columnStart = 0;
        int columnEnd = columnCount-1;

        while(rowStart >=0 && rowStart<= rowEnd && columnStart >=0 && columnStart <= columnEnd){

            for(int i= columnStart; i<=columnEnd ;i++){
                if(matrix[rowStart][i] != -200){
                    answer.add(matrix[rowStart][i]);
                    matrix[rowStart][i] = -200;
                }
            }
            for(int i=rowStart ; i<=rowEnd; i++){
                if(matrix[i][columnEnd] != -200){
                    answer.add(matrix[i][columnEnd]);
                    matrix[i][columnEnd] = -200;
                }
            }
            for(int i=columnEnd-1; i>=0; i--){
                if(matrix[rowEnd][i] != -200){
                    answer.add(matrix[rowEnd][i]);
                    matrix[rowEnd][i] = -200;
                }
            }
            for(int i=rowEnd-1; i>=0; i--){
                if(matrix[i][columnStart] != -200){
                    answer.add(matrix[i][columnStart]);
                    matrix[i][columnStart] = -200;
                }
            }

            rowStart = rowStart+1; rowEnd = rowEnd-1;
            columnStart = columnStart+1;  columnEnd = columnEnd-1;
        }

        return answer;
    }
}
