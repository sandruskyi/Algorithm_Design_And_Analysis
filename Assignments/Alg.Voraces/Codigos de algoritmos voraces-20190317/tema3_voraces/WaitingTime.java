package tema3_voraces;

import java.util.HashSet;
import java.util.Set;

public class WaitingTime {

    private static int getBestTask(Set<Integer> candidates, double[] tasks) {
        double bestTimeTask = Integer.MAX_VALUE;
        int bestTask = 0;

        for (int c : candidates) {
            double time = tasks[c];
            if (time < bestTimeTask) {
                bestTimeTask = time;
                bestTask = c;
            }
        }
        return bestTask;
    }

    public static int[] greedyAlgorithmWT(double[] tasks) {
        int n = tasks.length;
        Set<Integer> candidates = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        int[] sol = new int[n];
        int i = 0;
        while (!candidates.isEmpty()) {
            int bestTask = getBestTask(candidates, tasks);
            candidates.remove(bestTask);
            sol[i] = bestTask;
            i++;
        }
        return sol;
    }

    public static void main(String[] args) {
        int n = 10;
        double[] tasks = new double[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = Math.round(Math.random() * 96 + 44);
        }
        int[] sol = greedyAlgorithmWT(tasks);
        double sumWaitingTime = 0;
        for (int t : sol) {
            System.out.print(t+" ");
            sumWaitingTime += tasks[t];
        }
        System.out.println();
        System.out.println("SUM: "+sumWaitingTime);
        System.out.println("AVG: " + sumWaitingTime / n);
    }
}
