package LeetCode.Amazon.Others;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
There are 8 prison cells in a row, and each cell is either occupied or vacant.
Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.

(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)

Note:

cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9
 */
public class PrisonCellAfterNDays {
    protected int cellsToBitmap(int[] cells) {
        int stateBitmap = 0x0;
        for (int cell : cells) {
            stateBitmap <<= 1;
            stateBitmap = (stateBitmap | cell);
        }
        return stateBitmap;
    }

    protected int[] nextDay(int[] cells) {
        int[] newCells = new int[cells.length];
        newCells[0] = 0;
        for (int i = 1; i < cells.length - 1; i++) {
            newCells[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
        }
        newCells[cells.length - 1] = 0;
        return newCells;
    }

    public int[] prisonAfterNDays2(int[] cells, int N) {

        HashMap<Integer, Integer> seen = new HashMap<>();
        boolean isFastForwarded = false;

        // step 1). run the simulation with hashmap
        while (N > 0) {
            if (!isFastForwarded) {
                int stateBitmap = this.cellsToBitmap(cells);
                if (seen.containsKey(stateBitmap)) {
                    // the length of the cycle is seen[state_key] - N
                    N %= seen.get(stateBitmap) - N;
                    isFastForwarded = true;
                } else {
                    seen.put(stateBitmap, N);
                }
            }

            // check if there is still some steps remained,
            // with or without the fast-forwarding.
            if (N > 0) {
                N -= 1;
                cells = this.nextDay(cells);
            }

        }

        return cells;
    }

    // Approach 2:
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> seen = new HashSet();
        boolean cycle = false;
        int len = 0;
        for (int i = 0; i < N; i++) {
            int[] next = nextDayState(cells);
            String key = Arrays.toString(next);
            if (seen.contains(key)) {
                cycle = true;
                break;
            }
            seen.add(key);
            len++;
            cells = next;
        }

        if (cycle) {
            return prisonAfterNDays(cells, N % len);
        }
        return cells;
    }

    private int[] nextDayState(int[] cells) {
        int[] next = new int[cells.length];
        for (int i = 1; i < cells.length; i++)
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        return next;
    }

    // Approach 3:
    public int[] prisonAfterNDays3(int[] cells, int N) {
        N = (N - 1) % 14 + 1;
        for (int i = 0; i < N; i++) {
            cells = nextDayState2(cells);
        }
        return cells;
    }

    private int[] nextDayState2(int[] cells) {
        int[] next = new int[cells.length];
        for(int i = 1; i < cells.length - 1; i++) {
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return next;
    }

}
