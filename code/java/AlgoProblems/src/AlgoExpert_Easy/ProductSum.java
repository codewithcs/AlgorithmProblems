package AlgoExpert_Easy;

import java.util.ArrayList;
import java.util.List;

public class ProductSum {

    // O(n) time | O(d) space, where n is the total number of elements in the array,
    // including sub-elements, and d is the greatest depth of "special" arrays in the array.
    public static int productSum(List<Object> array) {
        return productSumHelper(array, 1);
    }

    public static int productSumHelper(List<Object> array, int multiplier){
        int sum = 0;

        for(Object el: array){
            if(el instanceof ArrayList){
                @SuppressWarnings("unchecked")
                ArrayList<Object> ls = (ArrayList<Object>) el;
                sum += productSumHelper(ls, multiplier +1);
            } else {
                sum += (int) el;
            }
        }

        return sum * multiplier;

    }

}


