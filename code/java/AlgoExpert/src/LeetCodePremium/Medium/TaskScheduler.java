package LeetCodePremium.Medium;

import java.util.*;

/*
Given a characters array tasks, representing the tasks a CPU needs to do,
where each letter represents a different task. Tasks could be done in any order.
Each task is done in one unit of time. For each unit of time, the CPU could
complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period
between two same tasks (the same letter in the array), that is that there must be at
least n units of time between any two same tasks.

Return the least number of units of times that the
CPU will take to finish all the given tasks.

Constraints:
1 <= tasks.length <= 10^4
tasks[i] is upper-case English letter.
The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];
        for (char t : tasks) {
            frequencies[t - 'A']++;
        }

        Arrays.sort(frequencies);

        // max frequency
        int f_max = frequencies[25];
        int idle_time = (f_max - 1) * n;

        for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
            idle_time -= Math.min(f_max - 1, frequencies[i]);
        }
        idle_time = Math.max(0, idle_time);

        return idle_time + tasks.length;
    }

    public int leastInterval2(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        Map<Character, Integer> taskToCount = new HashMap<>();
        for (char c : tasks) {
            taskToCount.put(c, taskToCount.getOrDefault(c, 0) + 1);
        }

        Queue<Integer> queue = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (char c : taskToCount.keySet()) queue.offer(taskToCount.get(c));

        Map<Integer, Integer> coolDown = new HashMap<>();
        int currentTime = 0;

        while (!queue.isEmpty() || !coolDown.isEmpty()) {
            if (coolDown.containsKey(currentTime - n - 1)) {
                queue.offer(coolDown.remove(currentTime - n - 1));
            }
            if (!queue.isEmpty()) {
                int left = queue.poll() - 1; 
                if (left != 0) {
                    coolDown.put(currentTime, left);
                }
            }
            currentTime++;
        }

        return currentTime;
    }

}
