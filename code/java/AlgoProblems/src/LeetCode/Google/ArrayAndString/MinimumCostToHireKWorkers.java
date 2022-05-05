package LeetCode.Google.ArrayAndString;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.
When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their
quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

Note:
1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.
 */
public class MinimumCostToHireKWorkers {

    // O(N^2 * logN) Time, O(N) Space
    public double mincostToHireWorkers1(int[] quality, int[] wage, int K) {
        int N = quality.length;
        double ans = 1e9;

        for (int captain = 0; captain < N; ++captain) {
            // Must pay at least wage[captain] / quality[captain] per quality.
            double factor = (double) wage[captain] / quality[captain];

            double prices[] = new double[N];
            int t = 0;

            for (int worker = 0; worker < N; ++worker) {
                double price = factor * quality[worker];
                if (price < wage[worker]) {
                    continue;
                }
                prices[t++] = price;
            }

            if (t < K) {
                continue;
            }
            Arrays.sort(prices, 0, t);

            double cand = 0;
            for (int i = 0; i < K; ++i) {
                cand += prices[i];
            }
            ans = Math.min(ans, cand);
        }

        return ans;
    }

    // O(N*logN) time, O(N) space
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers);

        double ans = 1e9; // 10^9.
        int sumq = 0;

        PriorityQueue<Integer> pool = new PriorityQueue();

        for (Worker worker: workers) {
            pool.offer(-worker.quality);
            sumq += worker.quality;
            if (pool.size() > K) {
                sumq += pool.poll();
            }
            if (pool.size() == K) {
                ans = Math.min(ans, sumq * worker.ratio());
            }
        }

        return ans;
    }

    // Using a Max Heap:
    public double mincostToHireWorkers2(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers);

        double ans = 1e9; // 10^9.
        int sumq = 0;

        PriorityQueue<Integer> pool = new PriorityQueue<>( (a, b) -> Integer.compare(b, a));
        for (Worker worker: workers) {
            pool.offer(worker.quality);
            sumq += worker.quality;
            if (pool.size() > K) {
                sumq -= pool.poll();
            }
            if (pool.size() == K) {
                ans = Math.min(ans, sumq * worker.ratio());
            }
        }

        return ans;
    }
}

class Worker implements Comparable<Worker> {
    public int quality, wage;
    public Worker(int q, int w) {
        quality = q;
        wage = w;
    }

    public double ratio() {
        return (double) wage / quality;
    }

    public int compareTo(Worker other) {
        return Double.compare(ratio(), other.ratio());
    }
}