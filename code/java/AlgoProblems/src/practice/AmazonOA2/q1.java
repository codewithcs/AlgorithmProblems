package practice.AmazonOA2;

import java.util.*;

public class q1 {
    static class PairInt{
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

        public static void main(String[] args) {
            PairInt p = selectPackages2(250, 5, Arrays.asList(100, 180, 40, 120, 10));

            System.out.println(p.first);
            System.out.println(p.second);
        }

        static PairInt selectPackages(int truckSpace, int numPackages, List<Integer> packageSpace){

            Collections.sort(packageSpace);
            int left = 0; int right = numPackages-1;
            PairInt result  = new PairInt(-1, -1);
            int requiredSum = truckSpace-30;

            while (left < right){
                int sum = packageSpace.get(left) + packageSpace.get(right);

                if(sum == requiredSum){
                    return new PairInt(left, right);
                } else if(sum < requiredSum){
                    left++;
                } else {
                    right--;
                }

            }

            return result;
        }

        static PairInt selectPackages2(int truckSpace, int numPackages, List<Integer> packageSpace){
            PairInt result  = new PairInt(-1, -1);

            int target = truckSpace- 30;
            Map<Integer, Integer> map = new HashMap<>();

            for(int i=0 ; i<numPackages ; i++){
                int requiredSum = target - packageSpace.get(i);

                if(map.containsKey(requiredSum)){
                    return new PairInt(map.get(requiredSum), i);
                } else {
                    map.put(packageSpace.get(i), i);
                }

            }
            return result;
        }


    }

}
