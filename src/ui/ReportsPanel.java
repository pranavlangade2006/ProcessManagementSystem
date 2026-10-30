package ui;

import reports.ReportGenerator;
import reports.ReportGenerator.ProcessResult;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReportsPanel extends JPanel {

    // =========================
    // COLORS
    // =========================
    private final Color BACKGROUND = new Color(15, 23, 42);
    private final Color SIDEBAR = new Color(17, 24, 39);
    private final Color CARD = new Color(30, 41, 59);
    private final Color CARD_LIGHT = new Color(51, 65, 85);
    private final Color WHITE = Color.WHITE;
    private final Color MUTED = new Color(148, 163, 184);
    private final Color GREEN = new Color(34, 197, 94);
    private final Color BLUE = new Color(59, 130, 246);
    private final Color PURPLE = new Color(139, 92, 246);
    private final Color ORANGE = new Color(249, 115, 22);

    // =========================
    // UI COMPONENTS
    // =========================
    private JComboBox<String> algorithmComboBox;

    private JLabel algorithmValue;
    private JLabel avgWaitingValue;
    private JLabel avgTurnaroundValue;
    private JLabel cpuUtilizationValue;
    private JLabel completionValue;

    private JTable processTable;
    private DefaultTableModel tableModel;

    private JPanel ganttPanel;

    // =========================
    // DATA
    // =========================
    private List<ProcessResult> results = new ArrayList<>();

    private static List<ProcessResult> fcfsResults = new ArrayList<>();
    private static List<ProcessResult> sjfResults = new ArrayList<>();
    private static List<ProcessResult> priorityResults = new ArrayList<>();
    private static List<ProcessResult> roundRobinResults = new ArrayList<>();

    private static ReportsPanel activeInstance;

    // =========================
    // CONSTRUCTOR
    // =========================
    public ReportsPanel() {

        activeInstance = this;

        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        buildUI();

        // Load FCFS real result if available
        if (!fcfsResults.isEmpty()) {
            results = new ArrayList<>(fcfsResults);
        } else {
            results = getDemoFCFSResults();
        }

        updateReport();
    }

    // =========================
    // MAIN UI
    // =========================
    private void buildUI() {

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BACKGROUND);
        header.setBorder(new EmptyBorder(25, 30, 15, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(BACKGROUND);

        JLabel title = new JLabel("Reports & Analytics");
        title.setForeground(WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel subtitle = new JLabel(
                "Analyze CPU scheduling performance and process statistics"
        );
        subtitle.setForeground(MUTED);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        // Algorithm selection
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        selectionPanel.setBackground(BACKGROUND);

        JLabel algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setForeground(WHITE);
        algorithmLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        algorithmComboBox = new JComboBox<>(
                new String[]{
                        "FCFS",
                        "SJF",
                        "Priority",
                        "Round Robin"
                }
        );

        algorithmComboBox.setPreferredSize(new Dimension(150, 38));
        algorithmComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton loadButton = new JButton("Load Report");
        styleButton(loadButton, BLUE);

        loadButton.addActionListener(e -> loadSelectedReport());

        selectionPanel.add(algorithmLabel);
        selectionPanel.add(algorithmComboBox);
        selectionPanel.add(loadButton);

        header.add(selectionPanel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // MAIN CONTENT
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout(20, 20));
        content.setBackground(BACKGROUND);
        content.setBorder(new EmptyBorder(0, 30, 30, 30));

        // STAT CARDS
        JPanel statsPanel = new JPanel(new GridLayout(1, 5, 15, 0));
        statsPanel.setBackground(BACKGROUND);

        JPanel algorithmCard = createStatCard(
                "Algorithm",
                "FCFS",
                BLUE
        );

        JPanel waitingCard = createStatCard(
                "Avg Waiting Time",
                "0.00",
                PURPLE
        );

        JPanel turnaroundCard = createStatCard(
                "Avg Turnaround",
                "0.00",
                GREEN
        );

        JPanel cpuCard = createStatCard(
                "CPU Utilization",
                "0.00%",
                ORANGE
        );

        JPanel completionCard = createStatCard(
                "Completion Time",
                "0",
                BLUE
        );

        algorithmValue = findValueLabel(algorithmCard);
        avgWaitingValue = findValueLabel(waitingCard);
        avgTurnaroundValue = findValueLabel(turnaroundCard);
        cpuUtilizationValue = findValueLabel(cpuCard);
        completionValue = findValueLabel(completionCard);

        statsPanel.add(algorithmCard);
        statsPanel.add(waitingCard);
        statsPanel.add(turnaroundCard);
        statsPanel.add(cpuCard);
        statsPanel.add(completionCard);

        content.add(statsPanel, BorderLayout.NORTH);

        // CENTER
        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.setBackground(BACKGROUND);

        // PROCESS TABLE
        JPanel tablePanel = createTablePanel();

        // GANTT CHART
        JPanel ganttContainer = createGanttPanel();

        centerPanel.add(tablePanel, BorderLayout.CENTER);
        centerPanel.add(ganttContainer, BorderLayout.SOUTH);

        content.add(centerPanel, BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);
    }

    // =========================
    // STAT CARD
    // =========================
    private JPanel createStatCard(
            String title,
            String value,
            Color accent
    ) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD);
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(accent, 1),
                        new EmptyBorder(15, 18, 15, 18)
                )
        );

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(MUTED);
        titleLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(WHITE);
        valueLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(valueLabel);

        return card;
    }

    // =========================
    // FIND VALUE LABEL
    // =========================
    private JLabel findValueLabel(JPanel panel) {

        Component[] components = panel.getComponents();

        for (Component component : components) {

            if (component instanceof JLabel) {

                JLabel label = (JLabel) component;

                if (label.getFont().isBold()) {
                    return label;
                }
            }
        }

        return new JLabel();
    }

    // =========================
    // TABLE
    // =========================
    private JPanel createTablePanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD);
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(CARD_LIGHT),
                        new EmptyBorder(15, 15, 15, 15)
                )
        );

        JLabel title = new JLabel("Process Performance");
        title.setForeground(WHITE);
        title.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        title.setBorder(
                new EmptyBorder(0, 0, 12, 0)
        );

        panel.add(title, BorderLayout.NORTH);

        String[] columns = {
                "PID",
                "Burst Time",
                "Completion Time",
                "Waiting Time",
                "Turnaround Time"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        processTable = new JTable(tableModel);

        processTable.setBackground(
                new Color(15, 23, 42)
        );

        processTable.setForeground(WHITE);

        processTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        processTable.setRowHeight(30);

        processTable.getTableHeader().setBackground(
                CARD_LIGHT
        );

        processTable.getTableHeader().setForeground(
                WHITE
        );

        processTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        JScrollPane scrollPane =
                new JScrollPane(processTable);

        scrollPane.setBorder(null);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // =========================
    // GANTT PANEL
    // =========================
    private JPanel createGanttPanel() {

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(CARD);

        container.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(CARD_LIGHT),
                        new EmptyBorder(15, 15, 15, 15)
                )
        );

        JLabel title = new JLabel("Gantt Chart");
        title.setForeground(WHITE);
        title.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        title.setBorder(
                new EmptyBorder(0, 0, 12, 0)
        );

        container.add(title, BorderLayout.NORTH);

        ganttPanel = new JPanel();
        ganttPanel.setLayout(
                new FlowLayout(
                        FlowLayout.LEFT,
                        5,
                        15
                )
        );

        ganttPanel.setBackground(CARD);

        JScrollPane scrollPane =
                new JScrollPane(ganttPanel);

        scrollPane.setPreferredSize(
                new Dimension(100, 130)
        );

        scrollPane.setBorder(null);

        container.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return container;
    }

    // =========================
    // LOAD SELECTED REPORT
    // =========================
    private void loadSelectedReport() {

        String selectedAlgorithm =
                (String) algorithmComboBox.getSelectedItem();

        if (selectedAlgorithm == null) {
            return;
        }

        // =========================
        // FCFS
        // =========================
        if (selectedAlgorithm.equals("FCFS")) {

            if (!fcfsResults.isEmpty()) {

                results =
                        new ArrayList<>(fcfsResults);

            } else {

                results =
                        getDemoFCFSResults();
            }
        }

        // =========================
        // SJF
        // =========================
        else if (selectedAlgorithm.equals("SJF")) {

            if (!sjfResults.isEmpty()) {

                results =
                        new ArrayList<>(sjfResults);

            } else {

                results =
                        getDemoSJFResults();
            }
        }

        // =========================
        // PRIORITY
        // =========================
        else if (selectedAlgorithm.equals("Priority")) {

            if (!priorityResults.isEmpty()) {

                results =
                        new ArrayList<>(priorityResults);

            } else {

                results =
                        getDemoPriorityResults();
            }
        }

        // =========================
        // ROUND ROBIN
        // =========================
        else if (selectedAlgorithm.equals("Round Robin")) {

            if (!roundRobinResults.isEmpty()) {

                results =
                        new ArrayList<>(roundRobinResults);

            } else {

                results =
                        getDemoRoundRobinResults();
            }
        }

        updateReport();
    }

    // =========================
    // UPDATE REPORT
    // =========================
    private void updateReport() {

        if (results == null) {
            results = new ArrayList<>();
        }

        String algorithm =
                (String) algorithmComboBox.getSelectedItem();

        if (algorithm == null) {
            algorithm = "FCFS";
        }

        // STATISTICS
        double avgWaiting =
                ReportGenerator.calculateAverageWaitingTime(
                        results
                );

        double avgTurnaround =
                ReportGenerator.calculateAverageTurnaroundTime(
                        results
                );

        double cpuUtilization =
                ReportGenerator.calculateCPUUtilization(
                        results
                );

        int completionTime =
                ReportGenerator.calculateTotalExecutionTime(
                        results
                );

        // UPDATE CARDS
        algorithmValue.setText(algorithm);

        avgWaitingValue.setText(
                String.format(
                        "%.2f",
                        avgWaiting
                )
        );

        avgTurnaroundValue.setText(
                String.format(
                        "%.2f",
                        avgTurnaround
                )
        );

        cpuUtilizationValue.setText(
                String.format(
                        "%.2f%%",
                        cpuUtilization
                )
        );

        completionValue.setText(
                String.valueOf(completionTime)
        );

        updateTable();

        updateGanttChart();
    }

    // =========================
    // UPDATE TABLE
    // =========================
    private void updateTable() {

        tableModel.setRowCount(0);

        for (ProcessResult result : results) {

            tableModel.addRow(
                    new Object[]{
                            result.pid,
                            result.burstTime,
                            result.completionTime,
                            result.waitingTime,
                            result.turnaroundTime
                    }
            );
        }
    }

    // =========================
    // GANTT CHART
    // =========================
    private void updateGanttChart() {

        ganttPanel.removeAll();

        int currentTime = 0;

        for (ProcessResult result : results) {

            JPanel block =
                    new JPanel(
                            new BorderLayout()
                    );

            block.setPreferredSize(
                    new Dimension(
                            Math.max(
                                    80,
                                    result.burstTime * 45
                            ),
                            55
                    )
            );

            block.setBackground(
                    getProcessColor(
                            result.pid
                    )
            );

            block.setBorder(
                    BorderFactory.createLineBorder(
                            WHITE
                    )
            );

            JLabel pidLabel =
                    new JLabel(
                            result.pid,
                            SwingConstants.CENTER
                    );

            pidLabel.setForeground(WHITE);

            pidLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            JLabel timeLabel =
                    new JLabel(
                            currentTime +
                                    " - " +
                                    result.completionTime,
                            SwingConstants.CENTER
                    );

            timeLabel.setForeground(WHITE);

            timeLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            11
                    )
            );

            block.add(
                    pidLabel,
                    BorderLayout.CENTER
            );

            block.add(
                    timeLabel,
                    BorderLayout.SOUTH
            );

            ganttPanel.add(block);

            currentTime =
                    result.completionTime;
        }

        ganttPanel.revalidate();
        ganttPanel.repaint();
    }

    // =========================
    // PROCESS COLORS
    // =========================
    private Color getProcessColor(String pid) {

        int hash =
                Math.abs(
                        pid.hashCode()
                );

        Color[] colors = {

                new Color(37, 99, 235),

                new Color(124, 58, 237),

                new Color(5, 150, 105),

                new Color(234, 88, 12),

                new Color(220, 38, 127),

                new Color(14, 116, 144)
        };

        return colors[
                hash % colors.length
        ];
    }

    // =========================
    // BUTTON STYLE
    // =========================
    private void styleButton(
            JButton button,
            Color color
    ) {

        button.setBackground(color);
        button.setForeground(WHITE);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );
    }

    // =========================================================
    // STATIC METHODS
    // OTHER PANELS USE THESE TO SEND REAL RESULTS
    // =========================================================

    // =========================
    // FCFS RESULTS
    // =========================
    public static void setFCFSResults(
            List<ProcessResult> newResults
    ) {

        if (newResults == null) {
            fcfsResults =
                    new ArrayList<>();
        } else {
            fcfsResults =
                    new ArrayList<>(newResults);
        }

        if (activeInstance != null) {

            String selected =
                    (String)
                            activeInstance.algorithmComboBox
                                    .getSelectedItem();

            if ("FCFS".equals(selected)) {

                activeInstance.results =
                        new ArrayList<>(
                                fcfsResults
                        );

                activeInstance.updateReport();
            }
        }
    }

    // =========================
    // SJF RESULTS
    // =========================
    public static void setSJFResults(
            List<ProcessResult> newResults
    ) {

        if (newResults == null) {
            sjfResults =
                    new ArrayList<>();
        } else {
            sjfResults =
                    new ArrayList<>(newResults);
        }

        if (activeInstance != null) {

            String selected =
                    (String)
                            activeInstance.algorithmComboBox
                                    .getSelectedItem();

            if ("SJF".equals(selected)) {

                activeInstance.results =
                        new ArrayList<>(
                                sjfResults
                        );

                activeInstance.updateReport();
            }
        }
    }

    // =========================
    // PRIORITY RESULTS
    // =========================
    public static void setPriorityResults(
            List<ProcessResult> newResults
    ) {

        if (newResults == null) {
            priorityResults =
                    new ArrayList<>();
        } else {
            priorityResults =
                    new ArrayList<>(newResults);
        }

        if (activeInstance != null) {

            String selected =
                    (String)
                            activeInstance.algorithmComboBox
                                    .getSelectedItem();

            if ("Priority".equals(selected)) {

                activeInstance.results =
                        new ArrayList<>(
                                priorityResults
                        );

                activeInstance.updateReport();
            }
        }
    }

    // =========================
    // ROUND ROBIN RESULTS
    // =========================
    public static void setRoundRobinResults(
            List<ProcessResult> newResults
    ) {

        if (newResults == null) {

            roundRobinResults =
                    new ArrayList<>();

        } else {

            roundRobinResults =
                    new ArrayList<>(
                            newResults
                    );
        }

        if (activeInstance != null) {

            String selected =
                    (String)
                            activeInstance.algorithmComboBox
                                    .getSelectedItem();

            if ("Round Robin".equals(selected)) {

                activeInstance.results =
                        new ArrayList<>(
                                roundRobinResults
                        );

                activeInstance.updateReport();
            }
        }
    }

    // =========================================================
    // DEMO DATA
    // Used only when real scheduler results are not available.
    // =========================================================

    // =========================
    // FCFS DEMO
    // =========================
    private List<ProcessResult> getDemoFCFSResults() {

        List<ProcessResult> demo =
                new ArrayList<>();

        demo.add(
                new ProcessResult(
                        "P1",
                        5,
                        5,
                        0,
                        5
                )
        );

        demo.add(
                new ProcessResult(
                        "P2",
                        3,
                        8,
                        5,
                        8
                )
        );

        demo.add(
                new ProcessResult(
                        "P3",
                        8,
                        16,
                        8,
                        16
                )
        );

        demo.add(
                new ProcessResult(
                        "P4",
                        4,
                        20,
                        16,
                        20
                )
        );

        return demo;
    }

    // =========================
    // SJF DEMO
    // =========================
    private List<ProcessResult> getDemoSJFResults() {

        List<ProcessResult> demo =
                new ArrayList<>();

        demo.add(
                new ProcessResult(
                        "P2",
                        3,
                        3,
                        0,
                        3
                )
        );

        demo.add(
                new ProcessResult(
                        "P4",
                        4,
                        7,
                        3,
                        7
                )
        );

        demo.add(
                new ProcessResult(
                        "P1",
                        5,
                        12,
                        7,
                        12
                )
        );

        demo.add(
                new ProcessResult(
                        "P3",
                        8,
                        20,
                        12,
                        20
                )
        );

        return demo;
    }

    // =========================
    // PRIORITY DEMO
    // =========================
    private List<ProcessResult> getDemoPriorityResults() {

        List<ProcessResult> demo =
                new ArrayList<>();

        demo.add(
                new ProcessResult(
                        "P3",
                        8,
                        8,
                        0,
                        8
                )
        );

        demo.add(
                new ProcessResult(
                        "P1",
                        5,
                        13,
                        8,
                        13
                )
        );

        demo.add(
                new ProcessResult(
                        "P4",
                        4,
                        17,
                        13,
                        17
                )
        );

        demo.add(
                new ProcessResult(
                        "P2",
                        3,
                        20,
                        17,
                        20
                )
        );

        return demo;
    }

    // =========================
    // ROUND ROBIN DEMO
    // =========================
    private List<ProcessResult> getDemoRoundRobinResults() {

        List<ProcessResult> demo =
                new ArrayList<>();

        demo.add(
                new ProcessResult(
                        "P1",
                        5,
                        17,
                        12,
                        17
                )
        );

        demo.add(
                new ProcessResult(
                        "P2",
                        3,
                        8,
                        5,
                        8
                )
        );

        demo.add(
                new ProcessResult(
                        "P3",
                        8,
                        20,
                        12,
                        20
                )
        );

        demo.add(
                new ProcessResult(
                        "P4",
                        4,
                        12,
                        8,
                        12
                )
        );

        return demo;
    }

    // =========================
    // MAIN - FOR TESTING
    // =========================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame(
                            "Reports & Analytics"
                    );

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setSize(
                    1200,
                    750
            );

            frame.setLocationRelativeTo(null);

            frame.setContentPane(
                    new ReportsPanel()
            );

            frame.setVisible(true);
        });
    }
}