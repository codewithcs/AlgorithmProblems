package LeetCodePremium.Medium;

import java.util.*;

/*
Given an array of integers arr of even length,
return true if and only if
it is possible to reorder it
such that arr[2 * i + 1] = 2 * arr[2 * i]
for every 0 <= i < len(arr) / 2.

Constraints:
0 <= arr.length <= 3 * 10^4
arr.length is even.
-10^5 <= arr[i] <= 10^5
 */
public class ArrayOfDoubledPairs {

    // Initial thinking
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();

        for(int element: arr){
            list.add(element);
        }

        for(int i=0; i< list.size() && !list.isEmpty(); ){
            int index = binarySearch(i+1, list.size()-1, 2*list.get(i), list);
            if(index != -1){
                list.remove(list.get(i));
                list.remove(list.get(index-1)); i=0;
            } else{
                i++;
            }
        }

        System.out.println("Entering 2nd for loop");

        // For Negative Values.
        for(int i=list.size()-1; i>=0 && !list.isEmpty() ; ){
            int index = binarySearch(0, i-1, 2*list.get(i), list);
            if(index != -1){
                list.remove(list.get(i));
                list.remove(list.get(index)); i= list.size()-1;
            } else {
                i--;
            }
        }

        return list.isEmpty();
    }

    public int binarySearch(int low, int high, int element, List<Integer> list){
        int mid = low + (high-low)/2;

        while(low <= high){
            if(element == list.get(mid) ){
                return  mid;
            } else if(element < list.get(mid)){
                high = mid-1;
            } else {
                low = mid+1;
            }
            mid = low + (high-low)/2;
        }

        return -1;
    }

    public boolean canReorderDoubled2(int[] arr) {
        Arrays.sort(arr); // O(n*log n) time and O(log n) space so that we can form maximum number of double pairs.
        Map<Integer, Integer> map = new HashMap<>();
        int pairs = 0;

        // O(n)
        for(int i=0; i< arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], 1);
            } else {
                map.put(arr[i], map.get(arr[i]) + 1);
            }
        }

        // O(n)
        for(int i=0; i< arr.length; i++){
            if(map.containsKey(2*arr[i])){
                if(map.get(2*arr[i]) > 0 && map.get(arr[i]) > 0){
                    pairs++;
                    map.put(arr[i], map.get(arr[i])-1);
                    map.put(2*arr[i],  map.get(2*arr[i])-1);
                }
            }

        }

        return pairs == arr.length/2 ;
    }

    // O(n) solution,
    // The thought is that for each item in the map, we
    // trace down to the smallest (abs value) element and remove the pairs in a bottom-up style.
    public boolean canReorderDoubled3(int[] A) {
        Map<Integer, Integer> count = new HashMap<>();
        int zeros = 0;
        for (int num : A) {
            if (num == 0) {
                ++zeros;
                continue;
            }
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        if (zeros % 2 != 0) {
            return false;
        }
        for (int num : A) {
            if (!count.containsKey(num)) {
                continue;
            }
            while (count.containsKey(num)) {
                int temp = num;
                // Trace down
                while (temp % 2 == 0 && count.containsKey(temp / 2)) {
                    temp /= 2;
                }
                if (temp == num) {
                    break;
                }
                int pairCountM = count.get(temp);
                int pairCountN = count.get(temp * 2);
                count.remove(temp);
                if (pairCountN < pairCountM) {
                    return false;
                } else if (pairCountN == pairCountM) {
                    count.remove(temp * 2);
                } else {
                    count.put(temp * 2, pairCountN - pairCountM);
                }
            }
        }

        return count.size() == 0;
    }
}
