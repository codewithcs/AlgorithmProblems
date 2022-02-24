package practice;

import java.util.ArrayList;
import java.util.List;

public class List_List_ {
    public static void main(String[] args) {
        List<List<String>> result = new ArrayList<>();
        traverse(result);
        System.out.println("result is : " + result);
    }

    public static void traverse(List<List<String>> result){
        List<String> list = new ArrayList<>();

        list.add("Abc") ; list.add("def");
        result.add(list);
    }
}
