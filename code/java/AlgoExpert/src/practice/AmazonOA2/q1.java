package practice.AmazonOA2;

import java.util.Collections;
import java.util.List;

public class q1 {
    class PairInt{
        int first, second;
        PairInt(){ }
        PairInt(int first, int second){
            this.first = first;
            this.second = second;
        }

        PairInt positionOfTargetValue(int row, int column, List<List<Integer>> matrix, int target){
            int currentRow = 0;
            int currentCol = column-1;

            while(currentRow < row && currentCol >=0 ){
                if(matrix.get(currentRow).get(currentCol) > target){
                    currentCol--;
                } else if(matrix.get(currentRow).get(currentCol) < target){
                    currentRow++;
                } else{
                    return new PairInt(currentRow, currentCol);
                }
            }

            return new PairInt(-1, -1);
        }
    }

}
