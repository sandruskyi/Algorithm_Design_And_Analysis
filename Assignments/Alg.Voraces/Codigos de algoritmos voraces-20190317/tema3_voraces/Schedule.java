package tema3_voraces;

import java.util.*;

public class Schedule {
    public static final int FREE_DAY = -1;

    public static class Data {
        int[] deadline;
        double[] profit;
    }

    private static int getBestItem(Data data, Set<Integer> cand) {
        double bestProfit = 0;
        int bestItem = 0;
        for (int i : cand) {
            double profit = data.profit[i];
            if (profit > bestProfit) {
                bestProfit = profit;
                bestItem = i;
            }
        }
        return bestItem;
    }

    private static boolean isFeasible(int[] schedule, int nextDate) {
        return schedule[nextDate] == FREE_DAY;
    }

    public static int[] greedyAlgorithmSC(Data data) {
        int n = data.profit.length;
        Set<Integer> candidates = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        int lastDate = 0;
        for (int i = 0; i < data.profit.length; i++) {
            lastDate = Math.max(lastDate, data.deadline[i]);
        }

        int schedule[] = new int[lastDate + 1];
        Arrays.fill(schedule, FREE_DAY);

        while (!candidates.isEmpty()) {
            int bestItem = getBestItem(data, candidates);
            candidates.remove(bestItem);
            for (int i = data.deadline[bestItem]; i >= 0; i--) {
                if (isFeasible(schedule, i)) {
                    schedule[i] = bestItem;
                    break;
                }
            }

        }
        return schedule;
    }

    public static void printSol(Data data, int[] sol) {
        System.out.println("Task\tProfit\tDate\tDeadline");
        double totalProfit = 0;
        for (int i = 0; i < sol.length; i++) {
            int task = sol[i];
            if (task != FREE_DAY) {
                System.out.println(task + "\t\t" + data.profit[task] + "\t" + i + "\t\t" + data.deadline[task]);
                totalProfit += data.profit[task];
            }
        }
        System.out.println("PROFIT: "+totalProfit);
    }

    public static void printData(Data data) {
        System.out.println("Task\tProfit\tDeadline");
        int n = data.profit.length;
        for (int i = 0; i < n; i++) {
            System.out.println(i+"\t\t"+data.profit[i]+"\t"+data.deadline[i]);
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random(0);
        int n = 10;
        Data data = new Data();
        data.profit = new double[n];
        data.deadline = new int[n];

        for (int i = 0; i < n; i++) {
            data.profit[i] = Math.round(rnd.nextDouble() * 96 + 44);
            data.deadline[i] = (int) Math.round(rnd.nextDouble() * 0.7f * n);

        }
        System.out.println("DATA:");
        printData(data);
        int[] sol = greedyAlgorithmSC(data);
        System.out.println("SOLUTION:");
        printSol(data, sol);
    }
}
