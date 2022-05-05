package LeetCode.Google.Recursion;

import java.util.*;

/*
A strobogrammatic number is a number that looks the same when
rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.
 */
public class StrobogrammaticNumber2 {
    public List<String> findStrobogrammatic(int n) {
        Deque<String> q = new ArrayDeque();
        if (n % 2 == 0) { // even
            q.add("");
        } else {          // odd
            q.add("0");
            q.add("1");
            q.add("8");
        }

        List<String> ans = new ArrayList();
        while(!q.isEmpty()){
            String curr = q.poll();
            if(curr.length() == n){
                ans.add(curr);
                continue;
            }

            if(curr.length() < n-2){  // append '0' to all internal letters - except outermost letters. e.g. 010 is not a valid answer for n=3
                q.add("0" + curr + "0");
            }

            q.add("1" + curr + "1");
            q.add("8" + curr + "8");
            q.add("6" + curr + "9");
            q.add("9" + curr + "6");
        }

        return ans;
    }

    // Approach 2:
    Map<String, String> map = new HashMap<String, String>() {{
        put( "0", "0" );
        put( "1", "1" );
        put( "8", "8" );
        put( "6", "9" );
        put( "9", "6" );
    }};

    public List<String> findStrobogrammatic2(int n) {
        if (n == 1) {
            return Arrays.asList("0", "1", "8");
        }

        List<String> result = new LinkedList<>();

        if ((n & 1) != 0) { // if odd, middle numbers can only be 0, 1 or 8.
            for (String middle : new String[] {"0", "1", "8"}) {
                generate(middle, n, result);
            }
        } else {
            generate("", n, result);
        }

        return result;
    }

    public void generate(String middle, int n, List<String> result) {
        if (middle.length() == n) {
            result.add(middle);
            return;
        }

        for(String key : map.keySet()) {
            if (n - 2 == middle.length() && key == "0") { // skip zeros on both ends
                continue;
            }
            generate(key + middle + map.get(key), n, result); // imp that we append key and map.get(key).
        }
    }


    // Approach 3: Fastest Approach
    char[] pair = new char[10];
    char[] num = new char[] { '0', '1', '6', '8', '9' };

    public List<String> findStrobogrammatic3(int n) {
        List<String> list = new ArrayList<>();
        if(n == 1) {
            return Arrays.asList("0", "1", "8");
        }
        pair[0] = '0';
        pair[1] = '1';
        pair[6] = '9';
        pair[8] = '8';
        pair[9] = '6';
        recur(0, n - 1, new char[n], list);
        return list;
    }

    public void recur(int i, int n, char[] arr, List<String> list) {
        if(i >= n) {
            if(i == n) {
                // for odd number of digits
                char[] special = new char[]{'0', '8', '1'};
                for(char x: special) {
                    arr[i] = x;
                    list.add(new String(arr));
                }
            } else {
                list.add(new String(arr));
            }
            return;
        }


        for(int k = 0; k < num.length; k++) {
            if(i == 0 && k == 0) continue;
            arr[i] = num[k];
            arr[n] = pair[num[k] - '0'];
            recur(i + 1, n - 1, arr, list);
        }
    }

    // Approach 4:
    public List<String> findStrobogrammatic4(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        List<String> result = new ArrayList<>();
        helper(n, 0, map, result, new char[n]);
        return result;
    }

    private void helper(int n, int i, Map<Character, Character> options, List<String> result, char[] subresult) {
        if (n == 0) {
            return;
        }

        if (i == (n + 1) / 2) {
            result.add(new String(subresult));
            return;
        }

        for (char c: options.keySet()) {
            if (c == '0' && i == 0 && n > 1) continue;

            if ((c == '6' || c == '9') & i == n / 2) continue;

            subresult[i] = c;
            subresult[n - i - 1] = options.get(c);
            helper(n, i + 1, options, result, subresult);
        }

    }
}
