package reports;

import java.util.List;

public class ReportGenerator {

    // ==============================
    // Process Result
    // ==============================

    public static class ProcessResult {

        public String pid;
        public int burstTime;
        public int completionTime;
        public int waitingTime;
        public int turnaroundTime;

        public ProcessResult(
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


    // ==============================
    // Average Waiting Time
    // ==============================

    public static double calculateAverageWaitingTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (ProcessResult result : results) {
            total += result.waitingTime;
        }

        return (double) total / results.size();
    }


    // ==============================
    // Average Turnaround Time
    // ==============================

    public static double calculateAverageTurnaroundTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (ProcessResult result : results) {
            total += result.turnaroundTime;
        }

        return (double) total / results.size();
    }


    // ==============================
    // Total Burst Time
    // ==============================

    public static int calculateTotalBurstTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (ProcessResult result : results) {
            total += result.burstTime;
        }

        return total;
    }


    // ==============================
    // Total Execution Time
    // ==============================

    public static int calculateTotalExecutionTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int maximumCompletionTime = 0;

        for (ProcessResult result : results) {

            if (result.completionTime
                    > maximumCompletionTime) {

                maximumCompletionTime =
                        result.completionTime;
            }
        }

        return maximumCompletionTime;
    }


    // ==============================
    // CPU Utilization
    // ==============================

    public static double calculateCPUUtilization(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int totalBurstTime =
                calculateTotalBurstTime(results);

        int totalExecutionTime =
                calculateTotalExecutionTime(results);

        if (totalExecutionTime == 0) {
            return 0;
        }

        return
                (totalBurstTime * 100.0)
                        / totalExecutionTime;
    }


    // ==============================
    // Maximum Completion Time
    // ==============================

    public static int getMaximumCompletionTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int maximum = 0;

        for (ProcessResult result : results) {

            maximum =
                    Math.max(
                            maximum,
                            result.completionTime
                    );
        }

        return maximum;
    }


    // ==============================
    // Minimum Waiting Time
    // ==============================

    public static int getMinimumWaitingTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int minimum =
                results.get(0).waitingTime;

        for (ProcessResult result : results) {

            minimum =
                    Math.min(
                            minimum,
                            result.waitingTime
                    );
        }

        return minimum;
    }


    // ==============================
    // Maximum Waiting Time
    // ==============================

    public static int getMaximumWaitingTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int maximum = 0;

        for (ProcessResult result : results) {

            maximum =
                    Math.max(
                            maximum,
                            result.waitingTime
                    );
        }

        return maximum;
    }


    // ==============================
    // Minimum Turnaround Time
    // ==============================

    public static int getMinimumTurnaroundTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int minimum =
                results.get(0).turnaroundTime;

        for (ProcessResult result : results) {

            minimum =
                    Math.min(
                            minimum,
                            result.turnaroundTime
                    );
        }

        return minimum;
    }


    // ==============================
    // Maximum Turnaround Time
    // ==============================

    public static int getMaximumTurnaroundTime(
            List<ProcessResult> results
    ) {

        if (results == null || results.isEmpty()) {
            return 0;
        }

        int maximum = 0;

        for (ProcessResult result : results) {

            maximum =
                    Math.max(
                            maximum,
                            result.turnaroundTime
                    );
        }

        return maximum;
    }


    // ==============================
    // Print Report
    // ==============================

    public static void printReport(
            String algorithm,
            List<ProcessResult> results
    ) {

        System.out.println();
        System.out.println(
                "======================================"
        );

        System.out.println(
                "        PROCESS SCHEDULING REPORT"
        );

        System.out.println(
                "======================================"
        );

        System.out.println(
                "Algorithm : " + algorithm
        );

        System.out.println();

        System.out.printf(
                "%-10s %-10s %-12s %-10s %-12s%n",
                "PID",
                "Burst",
                "Completion",
                "Waiting",
                "Turnaround"
        );

        System.out.println(
                "--------------------------------------"
        );

        for (ProcessResult result : results) {

            System.out.printf(
                    "%-10s %-10d %-12d %-10d %-12d%n",
                    result.pid,
                    result.burstTime,
                    result.completionTime,
                    result.waitingTime,
                    result.turnaroundTime
            );
        }

        System.out.println();

        System.out.printf(
                "Average Waiting Time    : %.2f%n",
                calculateAverageWaitingTime(results)
        );

        System.out.printf(
                "Average Turnaround Time  : %.2f%n",
                calculateAverageTurnaroundTime(results)
        );

        System.out.printf(
                "CPU Utilization          : %.2f%%%n",
                calculateCPUUtilization(results)
        );

        System.out.println(
                "======================================"
        );
    }
}