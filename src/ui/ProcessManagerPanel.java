
package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessManagerPanel extends JPanel {

    // =========================================================
    // COLORS
    // =========================================================
    private static final Color BACKGROUND = new Color(15, 30, 48);
    private static final Color SIDEBAR = new Color(18, 39, 61);
    private static final Color CARD = new Color(25, 48, 72);

    private static final Color BLUE = new Color(20, 140, 240);
    private static final Color GREEN = new Color(30, 190, 90);
    private static final Color ORANGE = new Color(245, 145, 30);
    private static final Color RED = new Color(235, 65, 65);

    private static final Color WHITE = Color.WHITE;
    private static final Color LIGHT_TEXT = new Color(190, 205, 220);

    // =========================================================
    // UI COMPONENTS
    // =========================================================

    private JTextField processNameField;
    private JTextField burstTimeField;
    private JTextField priorityField;
    private JTextField memoryField;

    private JTable processTable;
    private DefaultTableModel tableModel;

    private JLabel totalProcessesLabel;
    private JLabel runningProcessesLabel;

    // =========================================================
    // PROCESS DATA
    // =========================================================

    private final List<ManagedProcess> processes =
            new ArrayList<>();

    private int processCounter = 101;

    // =========================================================
    // PROCESS MODEL
    // =========================================================

    private static class ManagedProcess {

        String pid;
        String name;
        int burstTime;
        int priority;
        int memory;
        String state;

        ManagedProcess(
                String pid,
                String name,
                int burstTime,
                int priority,
                int memory
        ) {
            this.pid = pid;
            this.name = name;
            this.burstTime = burstTime;
            this.priority = priority;
            this.memory = memory;
            this.state = "Ready";
        }
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ProcessManagerPanel() {

        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        createUI();
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        // Main content
        JPanel mainPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // Header
        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        // Center
        JPanel centerPanel = new JPanel(
                new BorderLayout(15, 15)
        );

        centerPanel.setBackground(BACKGROUND);

        centerPanel.add(
                createCreateProcessPanel(),
                BorderLayout.WEST
        );

        centerPanel.add(
                createProcessListPanel(),
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // Bottom controls
        mainPanel.add(
                createControlsPanel(),
                BorderLayout.SOUTH
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel panel = new JPanel(
                new BorderLayout()
        );

        panel.setBackground(BACKGROUND);

        JLabel title = new JLabel(
                "Process Manager"
        );

        title.setForeground(WHITE);
        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle = new JLabel(
                "Create, manage and control operating system processes"
        );

        subtitle.setForeground(LIGHT_TEXT);
        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JPanel titlePanel = new JPanel();

        titlePanel.setBackground(BACKGROUND);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.add(title);
        titlePanel.add(
                Box.createVerticalStrut(5)
        );
        titlePanel.add(subtitle);

        // Statistics
        JPanel statsPanel = new JPanel(
                new GridLayout(1, 2, 10, 0)
        );

        statsPanel.setBackground(BACKGROUND);

        totalProcessesLabel =
                new JLabel("0");

        runningProcessesLabel =
                new JLabel("0");

        statsPanel.add(
                createSmallStat(
                        "Total Processes",
                        totalProcessesLabel,
                        BLUE
                )
        );

        statsPanel.add(
                createSmallStat(
                        "Running",
                        runningProcessesLabel,
                        GREEN
                )
        );

        panel.add(
                titlePanel,
                BorderLayout.WEST
        );

        panel.add(
                statsPanel,
                BorderLayout.EAST
        );

        return panel;
    }

    // =========================================================
    // SMALL STAT CARD
    // =========================================================

    private JPanel createSmallStat(
            String title,
            JLabel value,
            Color accent
    ) {

        JPanel panel = new JPanel(
                new BorderLayout()
        );

        panel.setPreferredSize(
                new Dimension(150, 65)
        );

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                accent,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                LIGHT_TEXT
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        value.setForeground(WHITE);

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        panel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        panel.add(
                value,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // CREATE PROCESS PANEL
    // =========================================================

    private JPanel createCreateProcessPanel() {

        JPanel panel = new JPanel();

        panel.setPreferredSize(
                new Dimension(390, 430)
        );

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(50, 75, 100)
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("Create New Process");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(25)
        );

        // Process name
        panel.add(
                createLabel("Process Name")
        );

        processNameField =
                createTextField();

        panel.add(processNameField);

        panel.add(
                Box.createVerticalStrut(15)
        );

        // Burst time
        panel.add(
                createLabel("Burst Time")
        );

        burstTimeField =
                createTextField();

        panel.add(burstTimeField);

        panel.add(
                Box.createVerticalStrut(15)
        );

        // Priority
        panel.add(
                createLabel("Priority")
        );

        priorityField =
                createTextField();

        panel.add(priorityField);

        panel.add(
                Box.createVerticalStrut(15)
        );

        // Memory
        panel.add(
                createLabel("Memory (MB)")
        );

        memoryField =
                createTextField();

        panel.add(memoryField);

        panel.add(
                Box.createVerticalStrut(25)
        );

        JButton createButton =
                new JButton(
                        "+  Create Process"
                );

        createButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        createButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        48
                )
        );

        createButton.setBackground(
                GREEN
        );

        createButton.setForeground(
                WHITE
        );

        createButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        createButton.setFocusPainted(false);

        createButton.addActionListener(
                e -> createProcess()
        );

        panel.add(createButton);

        return panel;
    }

    // =========================================================
    // LABEL
    // =========================================================

    private JLabel createLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setForeground(
                LIGHT_TEXT
        );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(90, 110, 130)
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        field.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return field;
    }

    // =========================================================
    // PROCESS LIST
    // =========================================================

    private JPanel createProcessListPanel() {

        JPanel panel = new JPanel(
                new BorderLayout(10, 10)
        );

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(50, 75, 100)
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel title =
                new JLabel("Process List");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                                "PID",
                                "Process",
                                "Burst",
                                "Priority",
                                "Memory",
                                "State"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        processTable =
                new JTable(tableModel);

        processTable.setRowHeight(32);

        processTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        processTable.setBackground(CARD);

        processTable.setForeground(WHITE);

        processTable.setGridColor(
                new Color(
                        60,
                        80,
                        100
                )
        );

        processTable.setSelectionBackground(
                new Color(
                        45,
                        85,
                        120
                )
        );

        processTable.setSelectionForeground(
                WHITE
        );

        processTable.getTableHeader()
                .setBackground(
                        new Color(
                                35,
                                65,
                                92
                        )
                );

        processTable.getTableHeader()
                .setForeground(
                        WHITE
                );

        processTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        processTable
                );

        scrollPane.setBorder(null);

        scrollPane.getViewport()
                .setBackground(CARD);

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // CONTROL PANEL
    // =========================================================

    private JPanel createControlsPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                BACKGROUND
        );

        JLabel title =
                new JLabel(
                        "Process Controls"
                );

        title.setForeground(
                LIGHT_TEXT
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                10,
                                0
                        )
                );

        buttons.setBackground(
                BACKGROUND
        );

        JButton startButton =
                createControlButton(
                        "▶  Start",
                        GREEN
                );

        JButton pauseButton =
                createControlButton(
                        "Ⅱ  Pause",
                        ORANGE
                );

        JButton resumeButton =
                createControlButton(
                        "▶  Resume",
                        BLUE
                );

        JButton terminateButton =
                createControlButton(
                        "✕  Terminate",
                        RED
                );

        startButton.addActionListener(
                e -> startProcess()
        );

        pauseButton.addActionListener(
                e -> pauseProcess()
        );

        resumeButton.addActionListener(
                e -> resumeProcess()
        );

        terminateButton.addActionListener(
                e -> terminateProcess()
        );

        buttons.add(startButton);
        buttons.add(pauseButton);
        buttons.add(resumeButton);
        buttons.add(terminateButton);

        panel.add(
                buttons,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // CONTROL BUTTON
    // =========================================================

    private JButton createControlButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        140,
                        42
                )
        );

        button.setBackground(
                color
        );

        button.setForeground(
                WHITE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        return button;
    }

    // =========================================================
    // CREATE PROCESS
    // =========================================================

    private void createProcess() {

        String name =
                processNameField.getText().trim();

        String burstText =
                burstTimeField.getText().trim();

        String priorityText =
                priorityField.getText().trim();

        String memoryText =
                memoryField.getText().trim();

        // Validation
        if (name.isEmpty()) {

            showError(
                    "Please enter a process name."
            );

            return;
        }

        if (burstText.isEmpty()
                || priorityText.isEmpty()
                || memoryText.isEmpty()) {

            showError(
                    "Please fill all process details."
            );

            return;
        }

        int burstTime;
        int priority;
        int memory;

        try {

            burstTime =
                    Integer.parseInt(
                            burstText
                    );

            priority =
                    Integer.parseInt(
                            priorityText
                    );

            memory =
                    Integer.parseInt(
                            memoryText
                    );

        } catch (NumberFormatException e) {

            showError(
                    "Burst Time, Priority and Memory must be numbers."
            );

            return;
        }

        if (burstTime <= 0) {

            showError(
                    "Burst Time must be greater than 0."
            );

            return;
        }

        if (priority < 0) {

            showError(
                    "Priority cannot be negative."
            );

            return;
        }

        if (memory <= 0) {

            showError(
                    "Memory must be greater than 0."
            );

            return;
        }

        // PID
        String pid =
                "P" + processCounter++;

        ManagedProcess process =
                new ManagedProcess(
                        pid,
                        name,
                        burstTime,
                        priority,
                        memory
                );

        processes.add(process);

        updateTable();

        clearInputFields();

        JOptionPane.showMessageDialog(
                this,
                "Process " + pid
                        + " created successfully!",
                "Process Created",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // START PROCESS
    // =========================================================

    private void startProcess() {

        ManagedProcess process =
                getSelectedProcess();

        if (process == null) {
            return;
        }

        if ("Terminated".equals(
                process.state
        )) {

            showError(
                    "Terminated process cannot be started."
            );

            return;
        }

        if ("Running".equals(
                process.state
        )) {

            showError(
                    "Process is already running."
            );

            return;
        }

        process.state = "Running";

        updateTable();
    }

    // =========================================================
    // PAUSE PROCESS
    // =========================================================

    private void pauseProcess() {

        ManagedProcess process =
                getSelectedProcess();

        if (process == null) {
            return;
        }

        if (!"Running".equals(
                process.state
        )) {

            showError(
                    "Only a running process can be paused."
            );

            return;
        }

        process.state = "Waiting";

        updateTable();
    }

    // =========================================================
    // RESUME PROCESS
    // =========================================================

    private void resumeProcess() {

        ManagedProcess process =
                getSelectedProcess();

        if (process == null) {
            return;
        }

        if (!"Waiting".equals(
                process.state
        )) {

            showError(
                    "Only a waiting process can be resumed."
            );

            return;
        }

        process.state = "Ready";

        updateTable();
    }

    // =========================================================
    // TERMINATE PROCESS
    // =========================================================

    private void terminateProcess() {

        ManagedProcess process =
                getSelectedProcess();

        if (process == null) {
            return;
        }

        if ("Terminated".equals(
                process.state
        )) {

            showError(
                    "Process is already terminated."
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Terminate process "
                                + process.pid
                                + "?",
                        "Confirm Termination",
                        JOptionPane.YES_NO_OPTION
                );

        if (result ==
                JOptionPane.YES_OPTION) {

            process.state =
                    "Terminated";

            updateTable();
        }
    }

    // =========================================================
    // GET SELECTED PROCESS
    // =========================================================

    private ManagedProcess getSelectedProcess() {

        int selectedRow =
                processTable.getSelectedRow();

        if (selectedRow < 0) {

            showError(
                    "Please select a process first."
            );

            return null;
        }

        String pid =
                String.valueOf(
                        tableModel.getValueAt(
                                selectedRow,
                                0
                        )
                );

        for (ManagedProcess process :
                processes) {

            if (process.pid.equals(pid)) {
                return process;
            }
        }

        return null;
    }

    // =========================================================
    // UPDATE TABLE
    // =========================================================

    private void updateTable() {

        tableModel.setRowCount(0);

        int runningCount = 0;

        for (ManagedProcess process :
                processes) {

            tableModel.addRow(
                    new Object[]{
                            process.pid,
                            process.name,
                            process.burstTime,
                            process.priority,
                            process.memory,
                            process.state
                    }
            );

            if ("Running".equals(
                    process.state
            )) {

                runningCount++;
            }
        }

        totalProcessesLabel.setText(
                String.valueOf(
                        processes.size()
                )
        );

        runningProcessesLabel.setText(
                String.valueOf(
                        runningCount
                )
        );
    }

    // =========================================================
    // CLEAR INPUT
    // =========================================================

    private void clearInputFields() {

        processNameField.setText("");
        burstTimeField.setText("");
        priorityField.setText("");
        memoryField.setText("");

        processNameField.requestFocus();
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Process Manager",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================================
    // TEST MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame(
                            "Process Manager"
                    );

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.setSize(
                    1300,
                    850
            );

            frame.setLocationRelativeTo(
                    null
            );

            frame.setContentPane(
                    new ProcessManagerPanel()
            );

            frame.setVisible(true);
        });
    }
}
