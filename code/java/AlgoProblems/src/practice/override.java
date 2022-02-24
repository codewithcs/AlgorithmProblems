package practice;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class override {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>(new Comparator<Integer>(){
            @Override
            public  int compare(Integer a, Integer b){
                return -1;
            }
        });

        set.add(1);
        set.add(1);

        System.out.println(set);
    }

}
