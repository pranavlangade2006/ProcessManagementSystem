package scheduler;

import java.util.ArrayList;
import java.util.List;

public class PriorityScheduler {

    public static class Result {

        public String pid;
        public int burstTime;
        public int priority;
        public int completionTime;
        public int waitingTime;
        public int turnaroundTime;

        public Result(
                String pid,
                int burstTime,
                int priority,
                int completionTime,
                int waitingTime,
                int turnaroundTime
        ) {
            this.pid = pid;
            this.burstTime = burstTime;
            this.priority = priority;
            this.completionTime = completionTime;
            this.waitingTime = waitingTime;
            this.turnaroundTime = turnaroundTime;
        }
    }

    public static List<Result> calculate(
            List<String> processIds,
            List<Integer> burstTimes,
            List<Integer> priorities
    ) {

        List<Result> results = new ArrayList<>();

        List<String> ids = new ArrayList<>(processIds);
        List<Integer> bursts = new ArrayList<>(burstTimes);
        List<Integer> priorityList = new ArrayList<>(priorities);

        // Smaller priority number = higher priority
        for (int i = 0; i < priorityList.size() - 1; i++) {

            for (int j = i + 1; j < priorityList.size(); j++) {

                if (priorityList.get(j) < priorityList.get(i)) {

                    int tempPriority = priorityList.get(i);
                    priorityList.set(i, priorityList.get(j));
                    priorityList.set(j, tempPriority);

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
            int priority = priorityList.get(i);

            int waitingTime = currentTime;

            currentTime = currentTime + burst;

            int completionTime = currentTime;

            int turnaroundTime = completionTime;

            results.add(
                    new Result(
                            pid,
                            burst,
                            priority,
                            completionTime,
                            waitingTime,
                            turnaroundTime
                    )
            );
        }

        return results;
    }

    public static double averageWaitingTime(
            List<Result> results
    ) {

        if (results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Result result : results) {
            total += result.waitingTime;
        }

        return (double) total / results.size();
    }

    public static double averageTurnaroundTime(
            List<Result> results
    ) {

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