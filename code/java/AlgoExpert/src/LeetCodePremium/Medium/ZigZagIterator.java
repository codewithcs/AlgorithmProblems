package LeetCodePremium.Medium;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
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

    // 2 pointer approach. Like iterating over 2d matrix in a certain way.
     class ZigzagIterator {
        private List<List<Integer>> vectors = new ArrayList<>();
        // pointer to vector, and pointer to element
        private Integer pVec = 0, pElem = 0; // We are using these 2 variables for the whole matrix.
        private Integer totalNum = 0, outputCount = 0;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.vectors.add(v1);
            this.vectors.add(v2);
            for (List<Integer> vec : this.vectors) {
                this.totalNum += vec.size();
            }
        }

        public int next() {
            Integer iterNum = 0,  // another variable to traverse over the rows.
                    ret = null;

            while (iterNum < this.vectors.size()) {
                List<Integer> currVec = this.vectors.get(this.pVec);
                if (this.pElem < currVec.size()) {
                    ret = currVec.get(this.pElem);
                    this.outputCount += 1;
                }

                iterNum += 1;
                this.pVec = (this.pVec + 1) % this.vectors.size(); // Increment the row

                // increment the element pointer once iterating all vectors
                if (this.pVec == 0) { // Increment the column.
                    this.pElem += 1;
                }

                if (ret != null) {
                    return ret;
                }
            }

            // One should raise an Exception here.
            return 0;
        }

        public boolean hasNext() {
            return this.outputCount < this.totalNum;
        }
    }

    // Nice Approach, O(1) time for both functions and O(k) space.
    public class ZigzagIterator2 {
        private List<List<Integer>> vectors = new ArrayList<>();

        // Here we have separate vector number and element number for each of the vector.
        private LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();

        public ZigzagIterator2(List<Integer> v1, List<Integer> v2) {
            this.vectors.add(v1);
            this.vectors.add(v2);
            int index = 0;
            for (List<Integer> vec : this.vectors) {
                if (vec.size() > 0) { /// Very Important Check

                    // <index_to_vec, index_to_element_within_vec>
                    this.queue.add(new Pair<Integer, Integer>(index, 0));
                }
                index++;
            }
        }

        public int next() {
            // if (this.queue.size() == 0)
            // throw new Exception("Out of elements!");

            // <index_to_vec, index_to_element_within_vec>
            Pair<Integer, Integer> pointer = this.queue.removeFirst();
            Integer vecIndex = pointer.getKey();
            Integer elemIndex = pointer.getValue();
            Integer nextElemIndex = elemIndex + 1;
            // append the pointer for the next round
            // if there are some elements left.
            if (nextElemIndex < this.vectors.get(vecIndex).size()) {
                this.queue.addLast(new Pair<>(vecIndex, nextElemIndex));
            }

            return this.vectors.get(vecIndex).get(elemIndex);
        }

        public boolean hasNext() {
            return this.queue.size() > 0;
        }
    }
}
