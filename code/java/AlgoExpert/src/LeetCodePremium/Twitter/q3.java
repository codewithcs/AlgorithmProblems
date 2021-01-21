package LeetCodePremium.Twitter;

import java.util.List;

// Balanced Sums
public class q3 {
    int balancedSum(List<Integer> sales) {
        int n = sales.size();
        int sum = 0;
        for (int s:sales){
            sum+=s;
        }

        int cur = sales.get(0);
        for (int i = 1; i<n; i++) {
            if (cur == sum-cur-sales.get(i)){
                return i;
            }
            cur += sales.get(i);
        }
        return 0;
    }
}
