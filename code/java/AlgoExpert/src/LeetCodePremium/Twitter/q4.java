package LeetCodePremium.Twitter;

import java.util.List;

// Restocking the Warehouse.
public class q4 {
    public static int restock(List<Integer> itemCount, int target){

        int total = 0;
        for(int count: itemCount){
            total += count ;
            if(total >= target) break;
        }

        return Math.abs(total-target);
    }
}
