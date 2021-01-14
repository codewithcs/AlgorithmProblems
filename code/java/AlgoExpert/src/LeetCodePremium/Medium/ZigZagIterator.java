package LeetCodePremium.Medium;

import java.util.ArrayList;
import java.util.List;
/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Follow up:
What if you are given k 1d vectors? How well can your code be extended to such cases?
Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic"
 */
public class ZigZagIterator {
    int first = 0;
    int second = 0;
    boolean getElementFromFirst = true;

    List<Integer> v1_ ;
    List<Integer> v2_ ;
    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        v1_ = new ArrayList<Integer>(v1);
        v2_ = new ArrayList<Integer>(v2);
    }

    // Assuming call to next() will be valid.
    public int next() {
      //  int element = 0;

        if(getElementFromFirst){
            getElementFromFirst = false;
            if(first < v1_.size()){
                return v1_.get(first++);
            }
        } else {
            getElementFromFirst = true;
            if(second < v2_.size()){
                return v2_.get(second++);
            }
        }

        // If one of the lists has been completely processed.
        if(first < v1_.size()){
            return v1_.get(first++);
        }

      //  if(second < v2_.size()){
            return v2_.get(second++);
       // }

        //return element;
    }

    public boolean hasNext() {
        return first < v1_.size() || second < v2_.size();
    }
}
