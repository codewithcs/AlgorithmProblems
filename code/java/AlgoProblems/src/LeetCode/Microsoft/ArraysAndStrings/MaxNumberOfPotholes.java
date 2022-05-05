package LeetCode.Microsoft.ArraysAndStrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxNumberOfPotholes {
    public static int solution (String s, int B){
        int maxNumberOfPotholes = 0;
        List<Integer> list = new ArrayList<>();

        int currentLength = 0;
        for(int i=0; i< s.length() ; i++){
            if(s.charAt(i) == 'x'){
                currentLength++;
            } else {
                if(currentLength != 0){
                    list.add(currentLength);
                    currentLength = 0;
                }
            }
        }

        if(currentLength != 0){
            list.add(currentLength);
        }

        Collections.sort(list, Collections.reverseOrder());

        int currentCost = 0;

        for(int currentNumberOfItems: list) {
            if(currentNumberOfItems + 1 <= B) { // B is the remaining cost, not the total cost.
                maxNumberOfPotholes = maxNumberOfPotholes + currentNumberOfItems;
                currentCost = currentCost + currentNumberOfItems + 1;
                B = B - (currentNumberOfItems + 1);
            } else {
                maxNumberOfPotholes = maxNumberOfPotholes + B-1; // This B is the remaining cost which gets added.
                currentCost = currentCost + B;
                B = 0;
            }

        }

        return maxNumberOfPotholes;
    }

    public static void main(String[] args) {
        System.out.println(solution("...xxx..x....xxx.", 7)); // 4
        System.out.println(solution("..xxxxx", 4)); // 3
        System.out.println(solution("x.x.xxx...x", 14)); // 6
        System.out.println(solution("..", 5)); // 0
    }
}
