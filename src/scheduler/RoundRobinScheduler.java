package scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobinScheduler {

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

    private static class ProcessData {

        String pid;
        int burstTime;
        int remainingTime;
        int completionTime;

        ProcessData(String pid, int burstTime) {
            this.pid = pid;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
        }
    }

    public static List<Result> calculate(
            List<String> processIds,
            List<Integer> burstTimes,
            int timeQuantum
    ) {

        List<Result> results = new ArrayList<>();

        if (processIds.isEmpty()) {
            return results;
        }

        if (timeQuantum <= 0) {
            return results;
        }

        List<ProcessData> processes = new ArrayList<>();

        for (int i = 0; i < processIds.size(); i++) {

            processes.add(
                    new ProcessData(
                            processIds.get(i),
                            burstTimes.get(i)
                    )
            );
        }

        Queue<ProcessData> queue = new LinkedList<>();

        for (ProcessData process : processes) {
            queue.add(process);
        }

        int currentTime = 0;

        while (!queue.isEmpty()) {

            ProcessData currentProcess = queue.poll();

            int executionTime = Math.min(
                    timeQuantum,
                    currentProcess.remainingTime
            );

            currentProcess.remainingTime =
                    currentProcess.remainingTime - executionTime;

            currentTime =
                    currentTime + executionTime;

            if (currentProcess.remainingTime > 0) {

                queue.add(currentProcess);

            } else {

                currentProcess.completionTime = currentTime;
            }
        }

        for (ProcessData process : processes) {

            int completionTime =
                    process.completionTime;

            int turnaroundTime =
                    completionTime;

            int waitingTime =
                    turnaroundTime - process.burstTime;

            results.add(
                    new Result(
                            process.pid,
                            process.burstTime,
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