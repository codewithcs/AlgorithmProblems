package LeetCode.Google.TreesAndGraphs;

import java.util.*;

/*
There is a box protected by a password.
The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.

While entering a password, the last n digits entered
will automatically be matched against the correct password.

For example, assuming the correct password is "345", if you type "012345",
the box will open because the correct password matches the suffix of the entered password.

Return any password of minimum length that is
guaranteed to open the box at some point of entering it.

Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.
*/
public class CrackingTheSafe {
    static Set<String> seen;
    static StringBuilder ans;

    public static void main(String[] args) {
        System.out.println(crackSafe3(2, 1));
    }

    // Hierholzer's Algorithm
    public static String crackSafe(int n, int k) {
        if (n == 1 && k == 1) return "0";
        seen = new HashSet<String>();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; ++i) {
            sb.append("0");
        }
        String start = sb.toString();

        dfs(start, k);
        ans.append(start);
        return new String(ans);
    }

    public static void dfs(String node, int k) {
        for (int x = 0; x < k; ++x) {
            String neighbor = node + x;
            System.out.println("neighbor is : " + neighbor);
            if (!seen.contains(neighbor)) {
                seen.add(neighbor);
                dfs(neighbor.substring(1), k);
                ans.append(x);
            }
        }
    }

    // Inverse Burrows Wheeler Transform
    public static String crackSafe2(int n, int k) {
        int M = (int) Math.pow(k, n - 1);

        int[] P = new int[M * k];

        for (int i = 0; i < k; ++i) {
            for (int q = 0; q < M; ++q) {
                P[i * M + q] = q * k + i;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < M * k; ++i) {
            int j = i;
            while (P[j] >= 0) {
                ans.append(String.valueOf(j / M));
                int v = P[j];
                P[j] = -1;
                j = v;
            }
        }

        for (int i = 0; i < n - 1; ++i) {
            ans.append("0");
        }

        return new String(ans);
    }

    public static String crackSafe3(int n, int k) {
        if (n == 1 && k == 1) return "0";

        char[] chars = new char[k];
        for (int i = 0; i < k; i++) {
            chars[i] = (char) (i + '0');
        }

        List<String> strings = new ArrayList<>();

        // length n and smaller than n but divisible by n.
        for (int length = 1; length <= n; length++) {
            if (n % length == 0) {

                List<Integer> list = new ArrayList<>(); // w
                list.add(-1);

                while (list.size() > 0) {
                    list.set(list.size() - 1, list.get(list.size() - 1) + 1); // Replace

                    int m = list.size(); // current Length

                    if (m == length) {
                        String str = "";
                        for (int i = 0; i < list.size(); i++) {
                            str += chars[list.get(i)];
                        }
                        if(!strings.contains(str)){
                            strings.add(str);
                        }

                    }

                    while (list.size() < length) {
                        list.add(list.size(), list.get(list.size() - m));
                    }

                    while (list.size() > 0 && list.get(list.size() - 1) == k - 1) {
                        list.remove(list.size() - 1);
                    }
                }

            }
        }

        Collections.sort(strings);
        StringBuilder sb = new StringBuilder();
        for(String s: strings){
            sb.append(s);
        }

        for (int i = 0; i < n-1; ++i){
            sb.append("0");
        }

        return sb.toString() ;
    }
}







































