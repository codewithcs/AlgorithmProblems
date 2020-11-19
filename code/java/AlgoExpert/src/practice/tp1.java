package practice;

import java.util.HashMap;
import java.util.Map;

public class tp1 {

    String[] colors = { "Orange", "Yellow", "Green", "Blue", "Purple", "Red" };
    Map<String, Integer> map = new HashMap<>();
    public void init(){
        map.put("Orange", 0);
        map.put("Yellow", 1);
        map.put("Green", 2);
        map.put("Blue", 3);
        map.put("Purple", 4);
        map.put("Red", 5);
    }

    String describePair(String color1, String color2){
        init();

        if(color1.equals(color2)){
            return "Same" ;
        }

        int firstIdx = map.get(color1);
        int secondIdx  = (firstIdx + 1 ) % map.size();
        int sIdx = (-1 + firstIdx + map.size()) % map.size();

        if(map.get(color2) == secondIdx || map.get(color2) == sIdx ){
            return "Adjacent";
        }

        if(Math.abs(map.get(color1) - map.get(color2) )== 3) {
            return "Complementary";
        }

        return "None";
    }


    int findWinner(int N, int[] scores){
        int rounds  = scores.length/N;

        int[] cache = new int[N];

        for(int j=0; j<N ; j++) {
            for (int i = 0; i < scores.length; i++) {
                cache[j] += scores[i];
                i = i + N;
            }
        }

        int maxIdx = cache.length-1 ;
        for(int i=cache.length-1; i>=0 ; i-- ){
            if(cache[i] >= cache[maxIdx]){
                maxIdx = i;
            }
        }


        return maxIdx;
    }

    long coinCount(long N){
        int countA = 0;
        int countB = 0;

        int round = 0;

        while(N > 0){
            round = round%4;
            if(round == 0){

                if(N>0) {
                    countB++; N--;
                }
                if(N>0) {
                    countA++; N--;
                }

            } else if(round == 1){
                if(N>0) {
                    countB++; N--;
                }
            } else if(round ==2 ){
                if(N>1){
                    countA += 2; N-=2;
                } else {
                    countA += N;
                    N-=N;
                }

            } else if (round == 3) {
                if(N>0) {
                    countB++;
                    N--;
                }
                if(N>2) {
                    countA += 3; N-=3;
                } else {
                    countA += N;
                    N -=N;
                }
            }

            round++;
        }

        return countA ;
    }


}
