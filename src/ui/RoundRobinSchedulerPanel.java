
package ui;

import scheduler.RoundRobinScheduler;
import scheduler.RoundRobinScheduler.Result;
import reports.ReportGenerator;
import reports.ReportGenerator.ProcessResult;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoundRobinSchedulerPanel extends JFrame {

    // ---------------------------------------------------------
    // COLORS
    // ---------------------------------------------------------
    private final Color BACKGROUND = new Color(15, 30, 48);
    private final Color CARD = new Color(25, 48, 72);
    private final Color BLUE = new Color(20, 140, 240);
    private final Color GREEN = new Color(30, 190, 90);
    private final Color WHITE = Color.WHITE;
    private final Color LIGHT_TEXT = new Color(180, 200, 220);

    // ---------------------------------------------------------
    // INPUT FIELDS
    // ---------------------------------------------------------
    private JTextField processField;
    private JTextField burstField;
    private JTextField quantumField;

    private JTextArea resultArea;
    private JPanel ganttPanel;

    // ---------------------------------------------------------
    // PROCESS DATA
    // ---------------------------------------------------------
    private final List<String> processIds =
            new ArrayList<>();

    private final List<Integer> burstTimes =
            new ArrayList<>();

    // ---------------------------------------------------------
    // CONSTRUCTOR
    // ---------------------------------------------------------
    public RoundRobinSchedulerPanel() {

        setTitle("CPU Scheduling - Round Robin");
        setSize(1100, 700);
        setMinimumSize(
                new Dimension(950, 600)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();
    }

    // ---------------------------------------------------------
    // CREATE UI
    // ---------------------------------------------------------
    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        JPanel header =
                new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBackground(
                BACKGROUND
        );

        JLabel title =
                new JLabel(
                        "CPU Scheduling"
                );

        title.setForeground(
                WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Round Robin Scheduling"
                );

        subtitle.setForeground(
                LIGHT_TEXT
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        header.add(title);

        header.add(
                Box.createVerticalStrut(5)
        );

        header.add(subtitle);

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // CENTER
        // -----------------------------------------------------

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setBackground(
                BACKGROUND
        );

        center.add(
                createInputPanel(),
                BorderLayout.NORTH
        );

        center.add(
                createResultPanel(),
                BorderLayout.CENTER
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // ---------------------------------------------------------
    // INPUT PANEL
    // ---------------------------------------------------------
    private JPanel createInputPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        8,
                        5,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // -----------------------------------------------------
        // PROCESS ID
        // -----------------------------------------------------

        JLabel processLabel =
                new JLabel(
                        "Process ID"
                );

        processLabel.setForeground(
                LIGHT_TEXT
        );

        processField =
                new JTextField(
                        "P101"
                );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panel.add(
                processLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                processField,
                gbc
        );

        // -----------------------------------------------------
        // BURST TIME
        // -----------------------------------------------------

        JLabel burstLabel =
                new JLabel(
                        "Burst Time"
                );

        burstLabel.setForeground(
                LIGHT_TEXT
        );

        burstField =
                new JTextField(
                        "5"
                );

        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(
                burstLabel,
                gbc
        );

        gbc.gridx = 3;
        gbc.weightx = 1;

        panel.add(
                burstField,
                gbc
        );

        // -----------------------------------------------------
        // TIME QUANTUM
        // -----------------------------------------------------

        JLabel quantumLabel =
                new JLabel(
                        "Time Quantum"
                );

        quantumLabel.setForeground(
                LIGHT_TEXT
        );

        quantumField =
                new JTextField(
                        "2"
                );

        gbc.gridx = 4;
        gbc.weightx = 0;

        panel.add(
                quantumLabel,
                gbc
        );

        gbc.gridx = 5;
        gbc.weightx = 1;

        panel.add(
                quantumField,
                gbc
        );

        // -----------------------------------------------------
        // ADD BUTTON
        // -----------------------------------------------------

        JButton addButton =
                new JButton(
                        "+ Add Process"
                );

        addButton.setBackground(
                BLUE
        );

        addButton.setForeground(
                WHITE
        );

        addButton.setFocusPainted(
                false
        );

        addButton.setBorderPainted(
                false
        );

        addButton.addActionListener(
                e -> addProcess()
        );

        gbc.gridx = 6;
        gbc.weightx = 0;

        panel.add(
                addButton,
                gbc
        );

        // -----------------------------------------------------
        // RUN BUTTON
        // -----------------------------------------------------

        JButton runButton =
                new JButton(
                        "Run Round Robin"
                );

        runButton.setBackground(
                GREEN
        );

        runButton.setForeground(
                WHITE
        );

        runButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        runButton.setFocusPainted(
                false
        );

        runButton.setBorderPainted(
                false
        );

        runButton.addActionListener(
                e -> runRoundRobin()
        );

        gbc.gridx = 7;

        panel.add(
                runButton,
                gbc
        );

        return panel;
    }

    // ---------------------------------------------------------
    // ADD PROCESS
    // ---------------------------------------------------------
    private void addProcess() {

        String pid =
                processField
                        .getText()
                        .trim();

        String burstText =
                burstField
                        .getText()
                        .trim();

        if (
                pid.isEmpty()
                        ||
                burstText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Process ID and Burst Time."
            );

            return;
        }

        // Prevent duplicate process IDs
        if (processIds.contains(pid)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Process ID already exists."
            );

            return;
        }

        try {

            int burst =
                    Integer.parseInt(
                            burstText
                    );

            if (burst <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Burst time must be greater than 0."
                );

                return;
            }

            processIds.add(pid);
            burstTimes.add(burst);

            processField.setText("");
            burstField.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    pid + " added successfully."
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Burst Time must be a number."
            );
        }
    }

    // ---------------------------------------------------------
    // RESULT PANEL
    // ---------------------------------------------------------
    private JPanel createResultPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                BACKGROUND
        );

        // -----------------------------------------------------
        // GANTT CONTAINER
        // -----------------------------------------------------

        JPanel ganttContainer =
                new JPanel(
                        new BorderLayout()
                );

        ganttContainer.setBackground(
                CARD
        );

        ganttContainer.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JLabel ganttTitle =
                new JLabel(
                        "Gantt Chart"
                );

        ganttTitle.setForeground(
                WHITE
        );

        ganttTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        ganttContainer.add(
                ganttTitle,
                BorderLayout.NORTH
        );

        ganttPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                20
                        )
                );

        ganttPanel.setBackground(
                CARD
        );

        JScrollPane ganttScroll =
                new JScrollPane(
                        ganttPanel
                );

        ganttScroll.setPreferredSize(
                new Dimension(
                        900,
                        140
                )
        );

        ganttScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        ganttContainer.add(
                ganttScroll,
                BorderLayout.CENTER
        );

        panel.add(
                ganttContainer,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // RESULT AREA
        // -----------------------------------------------------

        resultArea =
                new JTextArea();

        resultArea.setEditable(
                false
        );

        resultArea.setBackground(
                new Color(
                        20,
                        42,
                        63
                )
        );

        resultArea.setForeground(
                WHITE
        );

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        resultArea.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JScrollPane resultScroll =
                new JScrollPane(
                        resultArea
                );

        resultScroll.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        50,
                                        80,
                                        105
                                )
                        ),
                        "Scheduling Results"
                )
        );

        panel.add(
                resultScroll,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ---------------------------------------------------------
    // RUN ROUND ROBIN
    // ---------------------------------------------------------
    private void runRoundRobin() {

        if (processIds.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one process."
            );

            return;
        }

        String quantumText =
                quantumField
                        .getText()
                        .trim();

        if (quantumText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Time Quantum."
            );

            return;
        }

        int timeQuantum;

        try {

            timeQuantum =
                    Integer.parseInt(
                            quantumText
                    );

            if (timeQuantum <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Time Quantum must be greater than 0."
                );

                return;
            }

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Time Quantum must be a number."
            );

            return;
        }

        // -----------------------------------------------------
        // RUN BACKEND
        // -----------------------------------------------------

        List<Result> results =
                RoundRobinScheduler.calculate(
                        processIds,
                        burstTimes,
                        timeQuantum
                );

        // -----------------------------------------------------
        // OUTPUT
        // -----------------------------------------------------

        StringBuilder output =
                new StringBuilder();

        output.append(
                "Round Robin Scheduling Results\n"
        );

        output.append(
                "====================================\n\n"
        );

        output.append(
                "Time Quantum : "
                        + timeQuantum
                        + "\n\n"
        );

        output.append(
                String.format(
                        "%-8s %-8s %-12s %-10s %-12s%n",
                        "PID",
                        "Burst",
                        "Completion",
                        "Waiting",
                        "Turnaround"
                )
        );

        output.append(
                "------------------------------------------------\n"
        );

        for (Result result : results) {

            output.append(
                    String.format(
                            "%-8s %-8d %-12d %-10d %-12d%n",
                            result.pid,
                            result.burstTime,
                            result.completionTime,
                            result.waitingTime,
                            result.turnaroundTime
                    )
            );
        }

        // -----------------------------------------------------
        // AVERAGES
        // -----------------------------------------------------

        double avgWaiting =
                RoundRobinScheduler
                        .averageWaitingTime(
                                results
                        );

        double avgTurnaround =
                RoundRobinScheduler
                        .averageTurnaroundTime(
                                results
                        );

        // -----------------------------------------------------
        // CPU UTILIZATION
        // -----------------------------------------------------

        int totalBurstTime = 0;
        int maximumCompletionTime = 0;

        for (Result result : results) {

            totalBurstTime +=
                    result.burstTime;

            if (
                    result.completionTime
                            >
                    maximumCompletionTime
            ) {

                maximumCompletionTime =
                        result.completionTime;
            }
        }

        double cpuUtilization = 0;

        if (maximumCompletionTime > 0) {

            cpuUtilization =
                    (
                            totalBurstTime
                                    * 100.0
                    )
                            /
                            maximumCompletionTime;
        }

        output.append("\n");

        output.append(
                String.format(
                        "Average Waiting Time    : %.2f%n",
                        avgWaiting
                )
        );

        output.append(
                String.format(
                        "Average Turnaround Time : %.2f%n",
                        avgTurnaround
                )
        );

        output.append(
                String.format(
                        "CPU Utilization         : %.2f%%%n",
                        cpuUtilization
                )
        );

        output.append(
                "Total Execution Time     : "
                        + maximumCompletionTime
                        + "\n"
        );

        resultArea.setText(
                output.toString()
        );

        // -----------------------------------------------------
        // GANTT CHART
        // -----------------------------------------------------

        createGanttChart(
                results,
                timeQuantum
        );

        // -----------------------------------------------------
        // SEND RESULTS TO REPORTS
        // -----------------------------------------------------

        sendResultsToReports(
                results
        );
    }

    // ---------------------------------------------------------
    // SEND ROUND ROBIN RESULTS TO REPORTS PANEL
    // ---------------------------------------------------------
    private void sendResultsToReports(
            List<Result> schedulerResults
    ) {

        List<ProcessResult> reportResults =
                new ArrayList<>();

        for (
                Result result :
                schedulerResults
        ) {

            reportResults.add(
                    new ProcessResult(
                            result.pid,
                            result.burstTime,
                            result.completionTime,
                            result.waitingTime,
                            result.turnaroundTime
                    )
            );
        }

        ReportsPanel.setRoundRobinResults(
                reportResults
        );
    }

    // ---------------------------------------------------------
    // GANTT CHART
    // ---------------------------------------------------------
    private void createGanttChart(
            List<Result> results,
            int timeQuantum
    ) {

        ganttPanel.removeAll();

        /*
         * The current Result class stores the final
         * completion time of each process.
         *
         * Therefore this chart displays the
         * processes in scheduling order.
         */

        int startTime = 0;

        for (Result result : results) {

            JPanel processBox =
                    new JPanel(
                            new BorderLayout()
                    );

            int width =
                    Math.max(
                            80,
                            result.burstTime * 35
                    );

            processBox.setPreferredSize(
                    new Dimension(
                            width,
                            55
                    )
            );

            processBox.setBackground(
                    BLUE
            );

            processBox.setBorder(
                    BorderFactory.createLineBorder(
                            WHITE
                    )
            );

            JLabel pidLabel =
                    new JLabel(
                            result.pid,
                            SwingConstants.CENTER
                    );

            pidLabel.setForeground(
                    WHITE
            );

            pidLabel.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            14
                    )
            );

            JLabel timeLabel =
                    new JLabel(
                            startTime
                                    + " - "
                                    + result.completionTime,
                            SwingConstants.CENTER
                    );

            timeLabel.setForeground(
                    WHITE
            );

            timeLabel.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            11
                    )
            );

            processBox.add(
                    pidLabel,
                    BorderLayout.CENTER
            );

            processBox.add(
                    timeLabel,
                    BorderLayout.SOUTH
            );

            ganttPanel.add(
                    processBox
            );

            startTime =
                    result.completionTime;
        }

        ganttPanel.revalidate();
        ganttPanel.repaint();
    }

    // ---------------------------------------------------------
    // MAIN
    // ---------------------------------------------------------
    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    RoundRobinSchedulerPanel scheduler =
                            new RoundRobinSchedulerPanel();

                    scheduler.setVisible(
                            true
                    );
                }
        );
    }
}
