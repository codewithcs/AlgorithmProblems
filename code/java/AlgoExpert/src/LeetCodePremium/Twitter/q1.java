package LeetCodePremium.Twitter;

public class q1 {
    public String canReach(int x1, int y1, int x2, int y2) {
        while (x2 >= x1 && y2 >= y1) {
            if (x2 == y2) break;
            if (x2 > y2) {
                if (y2 > y1){
                    x2 %= y2;
                }
                else {
                    return (x2 - x1) % y2 == 0 ? "Yes" : "No";
                }
            } else {
                if (x2 > x1) {
                    y2 %= x2;
                }
                else {
                    return (y2 - y1) % x2 == 0 ? "Yes" : "No";
                }
            }
        }

        return x2 == x1 && y2 == y1 ? "Yes" : "No";
    }
}
