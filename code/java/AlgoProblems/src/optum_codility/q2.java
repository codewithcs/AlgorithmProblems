package optum_codility;

import java.util.HashMap;
import java.util.Map;

public class q2 {

    public String solution(String S, int K){
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        Map<String, Integer> map = new HashMap<>();
        map.put("Mon", 0);
        map.put("Tue", 1);
        map.put("Wed", 2);
        map.put("Thu", 3);
        map.put("Fri", 4);
        map.put("Sat", 5);
        map.put("Sun", 6);

        int length = days.length ;
        int index = map.get(S);
        return days[(K + index)%length];
    }
}
