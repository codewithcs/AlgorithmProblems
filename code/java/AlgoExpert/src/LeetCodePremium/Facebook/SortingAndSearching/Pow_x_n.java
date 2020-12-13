package LeetCodePremium.Facebook.SortingAndSearching;

/*
Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
Constraints:
-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-104 <= x^n <= 104
 */

public class Pow_x_n {

    // TLE.
    public double myPow(double x, int n) {
        if(x == 0){
            return 0.0;
        }
        double result = 1.0;

        if(n==0){
            return 1.0;
        } else if(n>0){
            for(int i=1; i<=n; i++){
                result *= x;
            }
        } else {
            for(int i=1; i<=-n; i++){
                result *= x;
            }
        }
        return n > 0 ? result : 1/result ;
    }

    // Competitive Programming Trick.
    // Fast Power Algorithm Recursive: O(logn) time and space.
    // O(logn) space because of Recursion.
    private double fastPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
    public double myPow2(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);
    }

    // Fast Power Algorithm Iterative.
    public double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }

        return ans;
    }
}
