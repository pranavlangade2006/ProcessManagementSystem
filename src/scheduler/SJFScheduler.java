package scheduler;

import java.util.ArrayList;
import java.util.List;

public class SJFScheduler {

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

    public static List<Result> calculate(
            List<String> processIds,
            List<Integer> burstTimes
    ) {

        List<Result> results = new ArrayList<>();

        List<String> ids = new ArrayList<>(processIds);
        List<Integer> bursts = new ArrayList<>(burstTimes);

        // Sort processes by shortest burst time
        for (int i = 0; i < bursts.size() - 1; i++) {

            for (int j = i + 1; j < bursts.size(); j++) {

                if (bursts.get(j) < bursts.get(i)) {

                    int tempBurst = bursts.get(i);
                    bursts.set(i, bursts.get(j));
                    bursts.set(j, tempBurst);

                    String tempId = ids.get(i);
                    ids.set(i, ids.get(j));
                    ids.set(j, tempId);
                }
            }
        }

        int currentTime = 0;

        for (int i = 0; i < ids.size(); i++) {

            String pid = ids.get(i);
            int burst = bursts.get(i);

            int waitingTime = currentTime;

            currentTime = currentTime + burst;

            int completionTime = currentTime;

            int turnaroundTime = completionTime;

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

    public static double averageWaitingTime(List<Result> results) {

        if (results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Result result : results) {
            total += result.waitingTime;
        }

        return (double) total / results.size();
    }

    public static double averageTurnaroundTime(List<Result> results) {

        if (results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Result result : results) {
            total += result.turnaroundTime;
        }

        return (double) total / results.size();
    }
}