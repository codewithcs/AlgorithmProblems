package practice.CitiBank;
import java.util.*;


public class q1 {

    public static void main(String[] args) {
        System.out.println("Hello World");
        int[] A = new int[]{100, 100, 100, -10};
        String[] D = new String[]{"2020-12-31", "2020-12-22", "2020-12-03", "2020-12-29"};

        System.out.println("final cost is : " + solution(A, D));
    }
    // [100, 100, 100, -10], ['2020-12-31', '2020-12-22', '2020-12-03', '2020-12-29']

    // 230
    public static int solution(int[] A, String[] D) {
        // write your code in Java SE 8
        int N  = A.length ;
        List<String> dateList = Arrays.asList(D);
        Map<String, Integer> map = new HashMap<>();

        for(int i=0 ; i< A.length ; i++){
            map.put(D[i], A[i]);
        }

        int finalBalance = 0;
        Collections.sort(dateList, new Comparator<String>(){
            @Override
            public int compare(String object1, String object2 ){
                return object1.compareTo(object2);
            }
        });

        System.out.println("sorted list is : " + dateList);

        String startDate = dateList.get(0);
        int startMonth = Integer.parseInt(startDate.substring(5, 7));

        int cardPaymentCount = 0;
        int cardMonthCost = 0;
        if(map.get(startDate) < 0){
            cardPaymentCount += 1;
            cardMonthCost += map.get(startDate);
        }

        finalBalance += map.get(startDate);

        for(int i=1; i< dateList.size(); i++){
            int amount = map.get(dateList.get(i));
            String currentDate = dateList.get(i);
            int currentMonth = Integer.parseInt(currentDate.substring(5, 7));

            if(currentMonth > startMonth){ // new month
                if(cardPaymentCount < 3 && cardMonthCost < 100){
                    finalBalance -= 5*currentMonth;
                }

                cardPaymentCount = 0 ;  // set for the new month.
                cardMonthCost = 0;
            } else { // We are in the same month.
                if(map.get(currentDate) < 0){
                    cardPaymentCount++;
                    cardMonthCost += amount;
                }
                finalBalance += amount;

                if(i == N-1){
                    if(cardPaymentCount < 3 && cardMonthCost < 100){
                        finalBalance -= 5*currentMonth;
                    }
                }

            }
        }

        return finalBalance;
    }
}
