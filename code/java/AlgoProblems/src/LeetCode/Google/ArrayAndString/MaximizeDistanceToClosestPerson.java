package LeetCode.Google.ArrayAndString;

/*
You are given an array representing a row of seats where seats[i] = 1 represents a
person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the
closest person to him is maximized.

Return that maximum distance to the closest person.

Constraints:
2 <= seats.length <= 2 * 104
seats[i] is 0 or 1.
At least one seat is empty.
At least one seat is occupied.
 */
public class MaximizeDistanceToClosestPerson {

    // O(n) time and O(1) space.
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int prev = -1, future = 0;
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                // Check seats[future] == 0 after future < N check.
                while (future < N && seats[future] == 0 || future < i) { // future < i : Important Edge Case: [1, 1, 1, 0]
                    future++;
                }

                int left = prev == -1 ? N : i - prev;
                int right = future == N ? N : future - i;
                ans = Math.max(ans, Math.min(left, right));
            }
        }

        return ans;
    }

    // O(n) time and O(1) space.
    public int maxDistToClosest2(int[] seats) {
        int N = seats.length;
        int K = 0; // Current longest group of empty seats
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                K = 0;
            } else {
                K++;
                ans = Math.max(ans, (K + 1) / 2);
            }
        }

        // If leftmost is 0.
        for (int i = 0; i < N; ++i)  {
            if (seats[i] == 1) {
                ans = Math.max(ans, i);
                break;
            }
        }

        // Handle Case: Rightmost seat is empty.
        for (int i = N-1; i >= 0; --i)  {
            if (seats[i] == 1) {
                ans = Math.max(ans, N - 1 - i);
                break;
            }
        }

        return ans;
    }

    public int maxDistToClosest3(int[] seats) {
        int K =0 ;
        int answer = 0;

        for(int i=0; i<seats.length; i++){
            if(seats[i] == 0){
                K++;
                answer = Math.max(answer, (K+1)/2);
            } else {
                K=0;
            }
        }

        K = 0;
        for(int i=0; i<seats.length; i++){
            if(seats[i] == 0){
                K++;
                answer = Math.max(answer, K);
            } else {
                break;
            }
        }

        K = 0;
        for(int i=seats.length-1; i>=0 ; i--){
            if(seats[i] == 0){
                K++;
                answer = Math.max(answer, K);
            } else {
                break;
            }
        }

        return answer;
    }
}
