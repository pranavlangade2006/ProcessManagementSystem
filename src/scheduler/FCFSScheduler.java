
package scheduler;

import java.util.ArrayList;
import java.util.List;

public class FCFSScheduler {

    // Process information for FCFS calculation
    public static class Result {

        public String pid;
        public int burstTime;
        public int completionTime;
        public int waitingTime;
        public int turnaroundTime;

        public Result(
                String pid,
                int burstTime,
                int completionTime,
                int waitingTime,
                int turnaroundTime
        ) {
            this.pid = pid;
            this.burstTime = burstTime;
            this.completionTime = completionTime;
            this.waitingTime = waitingTime;
            this.turnaroundTime = turnaroundTime;
        }
    }

    // =========================================================
    // FCFS SCHEDULING
    // =========================================================

    public static List<Result> calculate(
            List<String> processIds,
            List<Integer> burstTimes
    ) {

        List<Result> results =
                new ArrayList<>();

        int currentTime = 0;

        for (int i = 0; i < processIds.size(); i++) {

            String pid =
                    processIds.get(i);

            int burst =
                    burstTimes.get(i);

            // Completion Time
            currentTime =
                    currentTime + burst;

            int completionTime =
                    currentTime;

            // Waiting Time
            int waitingTime =
                    completionTime - burst;

            // Turnaround Time
            int turnaroundTime =
                    completionTime;

            results.add(
                    new Result(
                            pid,
                            burst,
                            completionTime,
                            waitingTime,
                            turnaroundTime
                    )
            );
        }

        return results;
    }

    // =========================================================
    // AVERAGE WAITING TIME
    // =========================================================

    public static double averageWaitingTime(
            List<Result> results
    ) {

        if (results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Result result : results) {

            total +=
                    result.waitingTime;
        }

        return (double) total
                / results.size();
    }

    // =========================================================
    // AVERAGE TURNAROUND TIME
    // =========================================================

    public static double averageTurnaroundTime(
            List<Result> results
    ) {

        if (results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Result result : results) {

            total +=
                    result.turnaroundTime;
        }

        return (double) total
                / results.size();
    }
}