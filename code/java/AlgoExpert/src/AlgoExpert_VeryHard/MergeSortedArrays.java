package AlgoExpert_VeryHard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortedArrays {

    // O(nk) time | O(n+k) space
    // where n: total number of array elements and k is the number of arrays
    public static List<Integer> mergeSortedArraypos(List<List<Integer>> arrays){

        List<Integer> sortedList = new ArrayList<>();
        List<Integer> elementIdxs = new ArrayList<>(Collections.nCopies(arrays.size(), 0));

        // We need to store arrayIdx in Item object as we set the values in elementIdx according to arrayIdx.

        while(true){

            List<Item> smallestItems = new ArrayList<>();
            for(int arrayIdx =0; arrayIdx < arrays.size(); arrayIdx++){
                List<Integer> relevantArray = arrays.get(arrayIdx);
                int elementIdx = elementIdxs.get(arrayIdx);
                if(elementIdx == relevantArray.size()){
                    continue;
                }
                smallestItems.add(new Item(arrayIdx, relevantArray.get(elementIdx)));
            }

            // We are done if there is nothing to add in smallestItems list.
            if(smallestItems.size() == 0){
                break ;
            }

            Item nextItem = getMinValue(smallestItems);
            sortedList.add(nextItem.num);
            elementIdxs.set(nextItem.arrrayIdx, elementIdxs.get(nextItem.arrrayIdx) + 1);
        }

        return sortedList;
    }


    public static Item getMinValue (List<Item> items){
        int minValueIdx = 0;
        for(int i=1; i < items.size(); i++){
            if(items.get(i).num < items.get(minValueIdx).num){
                minValueIdx = i;
            }
        }
        return items.get(minValueIdx);
    }

    static class Item {
        public int arrrayIdx;
        public int num ;
        public Item(int arrrayIdx, int num){
            this.arrrayIdx = arrrayIdx;
            this.num = num;
        }
    }
}
