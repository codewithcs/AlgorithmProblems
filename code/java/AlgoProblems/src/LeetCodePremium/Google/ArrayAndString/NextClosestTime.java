package LeetCodePremium.Google.ArrayAndString;

import java.util.*;

/*
Given a time represented in the format "HH:MM", form the next closest
time by reusing the current digits.
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid.
For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 */
public class NextClosestTime {
    public static void main(String[] args) {
        System.out.println(nextClosestTime2("11:11"));
    }

    // Failed Strategy.
    public static String nextClosestTime(String time) {
        String[] times = time.split("\\:");

        String hour = times[0];
        String minute = times[1];

        int h = Integer.parseInt(hour);
        int m = Integer.parseInt(minute);

       // Set<Integer> set = new HashSet<>();
        int hourFirst = h/10; int hourSecond = h%10;
        int minuteFirst = m/10; int minuteSecond = m%10;
        //set.add(hourFirst); set.add(hourSecond); set.add(minuteFirst); set.add(minuteSecond);

        int[] digits = { hourFirst, hourSecond, minuteFirst, minuteSecond };
        int[] sortedDigits = { hourFirst, hourSecond, minuteFirst, minuteSecond };

        Map<Integer, Integer> nextGreaterDigit = new HashMap<>();
        Arrays.sort(sortedDigits);
        for(int i=0; i<sortedDigits.length-1; i++){
            nextGreaterDigit.put(sortedDigits[i], sortedDigits[i+1]);
        }
        nextGreaterDigit.put(sortedDigits[sortedDigits.length-1], Integer.MAX_VALUE );
        int[] limitOne = {2, 9, 5, 9};
        int[] limitTwo = {2, 3, 5, 9};

        int[] limit = digits[0] == 2 ? limitTwo : limitOne;
        boolean found = false;

        for(int i=digits.length-1; i>=0 ; i--){
            int currentDigit = digits[i];
            int nextDigit = nextGreaterDigit.get(currentDigit);
            if(nextGreaterDigit.get(currentDigit) <= limit[i]){
                digits[i] = nextGreaterDigit.get(currentDigit);
                found = true;
                break;
            }
        }

        if(!found){
            for(int i=0; i<=3; i++){
                digits[i] = sortedDigits[0];
            }
        }

        return digits[0] + "" + digits[1] + ":" + digits[2] + "" + digits[3];
    }

    // O(1) space and time
    public static String nextClosestTime2(String time) {
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
        minutes += Integer.parseInt(time.substring(3));

        Set<Integer> digits = new HashSet<>();
        for(char current: time.toCharArray() ){
            digits.add(current-'0');
        }

        while(true){ // Can use an infinite loop as we are assuming that the input will always be valid, that is we will reach a case when
                     // isValid will be set to true;
            minutes = (minutes + 1) % (24 * 60);
            int[] nextTime = { minutes / 60 / 10, minutes / 60 % 10, minutes % 60 / 10, minutes % 60 % 10 };

            boolean isValid = true ;
            for(int digit: nextTime){
                if(!digits.contains(digit)){
                    isValid = false;
                    break;
                }
            }

            if(isValid){
                return String.format("%02d:%02d", minutes/60, minutes%60);
            }
        }
    }

    // Build From Allowed Digits.
    public String nextClosestTime3(String time) {
        int start = 60 * Integer.parseInt(time.substring(0, 2));
        start += Integer.parseInt(time.substring(3));
        int ans = start;
        int elapsed = 24 * 60;
        Set<Integer> allowed = new HashSet();
        for (char c: time.toCharArray()){
            if (c != ':') {
                allowed.add(c - '0');
            }
        }

        for (int h1: allowed) {
            for (int h2: allowed) {
                if (h1 * 10 + h2 < 24) {
                    for (int m1: allowed) {
                        for (int m2: allowed) {
                            if (m1 * 10 + m2 < 60) {
                                int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                                int candElapsed = Math.floorMod(cur - start, 24 * 60);
                                if (0 < candElapsed && candElapsed < elapsed) {
                                    ans = cur;
                                    elapsed = candElapsed;
                                }
                            }
                        }
                    }
                }
            }
        }

        return String.format("%02d:%02d", ans / 60, ans % 60);
    }

    // Using TreeSet.
    public String nextClosestTime4(String time) {
        int n=time.length();
        char[] res = time.toCharArray();
        TreeSet<Integer> set=new TreeSet<>();
        for(char c:res){
            if(c!=':'){
                set.add(c-'0');
            }
        }
        for(int i=n-1;i>=0;i--){
            char c=res[i];
            int v=c-'0';
            if(c==':'){
                continue;
            }
            Integer ceil=set.ceiling(v+1);
            if(ceil==null||(i==3&&ceil>5)||(i==0&&ceil>2)||(res[0]=='2'&&i==1&&ceil>4)){
                res[i]=(char)(set.first()+'0');
            }

            else{
                res[i]=(char)(ceil+'0');
                return String.valueOf(res);
            }
        }
        return String.valueOf(res);
    }

    // Using Binary Search.
    public String nextClosestTime5(String time) {
        char[] result = time.toCharArray();
        char[] digits = new char[]{result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);

        // find next digit for HH:M_
        // e.g. 23:43 -> 23:44
        // no upperLimit for this digit, i.e. 0-9
        result[4] = findNext(result[4], (char) ('9' + 1), digits);
        if (result[4] > time.charAt(4)) return String.valueOf(result);

        // find next digit for HH:_M
        // e.g. 14:29 -> 14:41
        result[3] = findNext(result[3], '5', digits);
        if (result[3] > time.charAt(3)) return String.valueOf(result);

        // find next digit for H_:MM
        // e.g. 02:37 -> 03:00
        result[1] = result[0] == '2' ? findNext(result[1], '3', digits) : findNext(result[1], (char) ('9' + 1), digits);
        if (result[1] > time.charAt(1)) return String.valueOf(result);

        // find next digit for _H:MM
        // e.g. 19:59 -> 11:11
        result[0] = findNext(result[0], '2', digits);
        return String.valueOf(result);
    }

    private char findNext(char current, char upperLimit, char[] digits) {
        if (current == upperLimit) {
            return digits[0];
        }

        int pos = Arrays.binarySearch(digits, current) + 1;
        // traverse one by one to find next greater digit
        while (pos < 4 && (digits[pos] > upperLimit || digits[pos] == current)) {
            pos++;
        }
        return pos == 4 ? digits[0] : digits[pos];
    }
}
