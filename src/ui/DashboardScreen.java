package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardScreen extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BACKGROUND = new Color(15, 30, 48);
    private final Color SIDEBAR = new Color(18, 39, 61);
    private final Color CARD = new Color(25, 48, 72);

    private final Color BLUE = new Color(20, 140, 240);
    private final Color GREEN = new Color(30, 190, 90);
    private final Color ORANGE = new Color(245, 145, 30);
    private final Color RED = new Color(235, 65, 65);
    private final Color PURPLE = new Color(150, 80, 240);

    private final Color WHITE = Color.WHITE;
    private final Color LIGHT_TEXT = new Color(180, 200, 220);

    // =========================================================
    // PROCESS DATA
    // =========================================================

    private int processCounter = 101;

    private DefaultTableModel tableModel;
    private JTable processTable;

    private JLabel totalProcessesLabel;
    private JLabel runningProcessesLabel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardScreen() {

        setTitle("Process Management System");

        setSize(1300, 850);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setMinimumSize(
                new Dimension(1100, 750)
        );

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        mainPanel.add(
                createSidebar(),
                BorderLayout.WEST
        );

        mainPanel.add(
                createDashboard(),
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setPreferredSize(
                new Dimension(235, 850)
        );

        sidebar.setBackground(SIDEBAR);

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        // =====================================================
        // CENTERED PROJECT TITLE
        // =====================================================

        JPanel logoPanel =
                new JPanel(new GridBagLayout());

        logoPanel.setBackground(SIDEBAR);

        logoPanel.setPreferredSize(
                new Dimension(235, 115)
        );

        logoPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        115
                )
        );

        JLabel logo =
                new JLabel(
                        "<html>" +
                                "<div style='text-align:center;'>" +
                                "Process<br>" +
                                "Management<br>" +
                                "System" +
                                "</div>" +
                                "</html>"
                );

        logo.setForeground(WHITE);

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        logo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        logoPanel.add(logo);

        sidebar.add(logoPanel);

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        // =====================================================
        // MENU
        // =====================================================

        sidebar.add(
                createMenuButton(
                        "⌂   Dashboard",
                        true,
                        "dashboard"
                )
        );

        sidebar.add(
                createMenuButton(
                        "□   Process Manager",
                        false,
                        "process"
                )
        );

        sidebar.add(
                createMenuButton(
                        "□   CPU Scheduling",
                        false,
                        "scheduler"
                )
        );

        sidebar.add(
                createMenuButton(
                        "□   Memory Manager",
                        false,
                        "memory"
                )
        );

        sidebar.add(
                createMenuButton(
                        "□   Process States",
                        false,
                        "states"
                )
        );

        sidebar.add(
                createMenuButton(
                        "□   Reports",
                        false,
                        "reports"
                )
        );

        sidebar.add(
                createMenuButton(
                        "□   Settings",
                        false,
                        "settings"
                )
        );

        sidebar.add(
                Box.createVerticalGlue()
        );

        return sidebar;
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String text,
            boolean selected,
            String action
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        235,
                        50
                )
        );

        button.setPreferredSize(
                new Dimension(
                        235,
                        50
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        10,
                        10
                )
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        button.setForeground(WHITE);

        if (selected) {
            button.setBackground(BLUE);
        } else {
            button.setBackground(SIDEBAR);
        }

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // =====================================================
        // MENU ACTIONS
        // =====================================================

        button.addActionListener(
                e -> {

                    if (action.equals("dashboard")) {
                        return;
                    }

                    if (action.equals("process")) {

                        openModule(
                                "ui.ProcessManagerPanel",
                                "Process Manager"
                        );

                    } else if (
                            action.equals("scheduler")
                    ) {

                        openModule(
                                "ui.SchedulerPanel",
                                "CPU Scheduling"
                        );

                    } else if (
                            action.equals("memory")
                    ) {

                        openModule(
                                "ui.MemoryPanel",
                                "Memory Manager"
                        );

                    } else if (
                            action.equals("reports")
                    ) {

                        openModule(
                                "ui.ReportsPanel",
                                "Reports & Analytics"
                        );

                    } else if (
                            action.equals("states")
                    ) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Process States\n\n"
                                        + "Ready → Running\n"
                                        + "Running → Waiting\n"
                                        + "Waiting → Ready\n"
                                        + "Running → Terminated\n"
                                        + "Ready → Terminated\n"
                                        + "Waiting → Terminated",
                                "Process States",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else if (
                            action.equals("settings")
                    ) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Settings\n\n"
                                        + "Current Algorithm: Round Robin\n"
                                        + "Time Quantum: 2\n"
                                        + "Theme: Dark Blue\n"
                                        + "System: Process Management System",
                                "Settings",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // OPEN MODULE
    // =========================================================

    private void openModule(
            String className,
            String title
    ) {

        try {

            Class<?> moduleClass =
                    Class.forName(className);

            Object module =
                    moduleClass
                            .getDeclaredConstructor()
                            .newInstance();

            // =================================================
            // JFrame
            // =================================================

            if (module instanceof JFrame) {

                JFrame frame =
                        (JFrame) module;

                frame.setTitle(title);

                frame.setLocationRelativeTo(this);

                frame.setVisible(true);

                return;
            }

            // =================================================
            // JPanel
            // =================================================

            if (module instanceof JPanel) {

                JPanel panel =
                        (JPanel) module;

                JFrame frame =
                        new JFrame(title);

                frame.setDefaultCloseOperation(
                        JFrame.DISPOSE_ON_CLOSE
                );

                frame.setSize(
                        1300,
                        850
                );

                frame.setMinimumSize(
                        new Dimension(
                                1100,
                                750
                        )
                );

                frame.setLocationRelativeTo(this);

                frame.setContentPane(panel);

                frame.setVisible(true);

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Module is not a JFrame or JPanel:\n"
                            + className,
                    "Module Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (ClassNotFoundException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Module not found:\n"
                            + className
                            + "\n\nMake sure the Java file exists.",
                    "Module Not Found",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open module:\n"
                            + className
                            + "\n\nError:\n"
                            + ex.getMessage(),
                    "Module Error",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private JPanel createDashboard() {

        JPanel dashboard =
                new JPanel(
                        new BorderLayout()
                );

        dashboard.setBackground(BACKGROUND);

        dashboard.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        dashboard.add(
                createHeader(),
                BorderLayout.NORTH
        );

        dashboard.add(
                createCenterContent(),
                BorderLayout.CENTER
        );

        return dashboard;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(BACKGROUND);

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setBackground(BACKGROUND);

        JLabel title =
                new JLabel(
                        "Process Management System"
                );

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Simulate and visualize how an Operating System manages processes"
                );

        subtitle.setForeground(LIGHT_TEXT);

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitle);

        // =====================================================
        // NO "HI, PRANAV"
        // =====================================================
        // Intentionally no user label here.

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        header.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        20,
                        0
                )
        );

        return header;
    }

    // =========================================================
    // CENTER CONTENT
    // =========================================================

    private JPanel createCenterContent() {

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        content.setBackground(BACKGROUND);

        // =====================================================
        // TOP CARDS
        // =====================================================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                15
                        )
                );

        cards.setBackground(BACKGROUND);

        cards.setPreferredSize(
                new Dimension(
                        0,
                        125
                )
        );

        cards.add(
                createCard(
                        "CPU Usage",
                        "68%",
                        "System CPU",
                        BLUE
                )
        );

        cards.add(
                createCard(
                        "Memory Usage",
                        "0%",
                        "Used Memory: 0 MB",
                        GREEN
                )
        );

        cards.add(
                createProcessCountCard()
        );

        cards.add(
                createCard(
                        "Current Algorithm",
                        "Round Robin",
                        "Time Quantum: 2",
                        ORANGE
                )
        );

        content.add(
                cards,
                BorderLayout.NORTH
        );

        // =====================================================
        // MIDDLE
        // =====================================================

        JPanel middle =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                15
                        )
                );

        middle.setBackground(BACKGROUND);

        middle.add(
                createCreateProcessPanel()
        );

        middle.add(
                createProcessListPanel()
        );

        content.add(
                middle,
                BorderLayout.CENTER
        );

        return content;
    }

    // =========================================================
    // CARD
    // =========================================================

    private JPanel createCard(
            String title,
            String value,
            String description,
            Color accent
    ) {

        JPanel card =
                new JPanel();

        card.setBackground(CARD);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(LIGHT_TEXT);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(accent);

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        JLabel descriptionLabel =
                new JLabel(description);

        descriptionLabel.setForeground(LIGHT_TEXT);

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(valueLabel);

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(descriptionLabel);

        return card;
    }

    // =========================================================
    // PROCESS COUNT CARD
    // =========================================================

    private JPanel createProcessCountCard() {

        JPanel card =
                new JPanel();

        card.setBackground(CARD);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        JLabel title =
                new JLabel("Processes");

        title.setForeground(LIGHT_TEXT);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        totalProcessesLabel =
                new JLabel("0");

        totalProcessesLabel.setForeground(PURPLE);

        totalProcessesLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        runningProcessesLabel =
                new JLabel("Running: 0");

        runningProcessesLabel.setForeground(LIGHT_TEXT);

        runningProcessesLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        card.add(title);

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(
                totalProcessesLabel
        );

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(
                runningProcessesLabel
        );

        return card;
    }

    // =========================================================
    // CREATE PROCESS PANEL
    // =========================================================

    private JPanel createCreateProcessPanel() {

        JPanel panel =
                new JPanel();

        panel.setBackground(CARD);

        panel.setLayout(
                new GridLayout(
                        6,
                        2,
                        10,
                        12
                )
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        40,
                                        70,
                                        95
                                )
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel heading =
                new JLabel(
                        "Create New Process"
                );

        heading.setForeground(WHITE);

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        panel.add(heading);

        panel.add(
                new JLabel("")
        );

        // =====================================================
        // PROCESS NAME
        // =====================================================

        JLabel nameLabel =
                new JLabel("Process Name");

        nameLabel.setForeground(LIGHT_TEXT);

        JTextField nameField =
                new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);

        // =====================================================
        // BURST TIME
        // =====================================================

        JLabel burstLabel =
                new JLabel("Burst Time");

        burstLabel.setForeground(LIGHT_TEXT);

        JTextField burstField =
                new JTextField();

        panel.add(burstLabel);
        panel.add(burstField);

        // =====================================================
        // PRIORITY
        // =====================================================

        JLabel priorityLabel =
                new JLabel("Priority");

        priorityLabel.setForeground(LIGHT_TEXT);

        JTextField priorityField =
                new JTextField();

        panel.add(priorityLabel);
        panel.add(priorityField);

        // =====================================================
        // MEMORY
        // =====================================================

        JLabel memoryLabel =
                new JLabel("Memory (MB)");

        memoryLabel.setForeground(LIGHT_TEXT);

        JTextField memoryField =
                new JTextField();

        panel.add(memoryLabel);
        panel.add(memoryField);

        // =====================================================
        // CREATE BUTTON
        // =====================================================

        JButton createButton =
                new JButton(
                        "+  Create Process"
                );

        createButton.setBackground(GREEN);

        createButton.setForeground(WHITE);

        createButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        createButton.setFocusPainted(false);

        createButton.setBorderPainted(false);

        createButton.addActionListener(
                e -> createProcess(
                        nameField,
                        burstField,
                        priorityField,
                        memoryField
                )
        );

        panel.add(
                new JLabel("")
        );

        panel.add(createButton);

        return panel;
    }

    // =========================================================
    // CREATE PROCESS
    // =========================================================

    private void createProcess(
            JTextField nameField,
            JTextField burstField,
            JTextField priorityField,
            JTextField memoryField
    ) {

        String name =
                nameField.getText().trim();

        String burst =
                burstField.getText().trim();

        String priority =
                priorityField.getText().trim();

        String memory =
                memoryField.getText().trim();

        if (
                name.isEmpty()
                        || burst.isEmpty()
                        || priority.isEmpty()
                        || memory.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int burstTime =
                    Integer.parseInt(burst);

            int priorityValue =
                    Integer.parseInt(priority);

            int memoryValue =
                    Integer.parseInt(memory);

            if (
                    burstTime <= 0
                            || priorityValue <= 0
                            || memoryValue <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Values must be greater than 0.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String pid =
                    "P" + processCounter++;

            tableModel.addRow(
                    new Object[]{
                            pid,
                            name,
                            burstTime,
                            priorityValue,
                            memoryValue + " MB",
                            "Ready"
                    }
            );

            updateDashboardCounters();

            nameField.setText("");
            burstField.setText("");
            priorityField.setText("");
            memoryField.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Process created successfully!\n\n"
                            + "PID: " + pid
                            + "\nProcess: " + name
                            + "\nState: Ready",
                    "Process Created",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                NumberFormatException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Burst Time, Priority and Memory must be numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // PROCESS LIST PANEL
    // =========================================================

    private JPanel createProcessListPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        panel.setBackground(CARD);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        40,
                                        70,
                                        95
                                )
                        ),
                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel title =
                new JLabel("Process List");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {
                "PID",
                "Process",
                "Burst",
                "Priority",
                "Memory",
                "State"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
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

        processTable.setBackground(
                new Color(
                        30,
                        55,
                        80
                )
        );

        processTable.setForeground(WHITE);

        processTable.setGridColor(
                new Color(
                        50,
                        75,
                        100
                )
        );

        processTable.setSelectionBackground(BLUE);

        processTable.setSelectionForeground(WHITE);

        processTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        processTable
                .getTableHeader()
                .setBackground(
                        new Color(
                                35,
                                70,
                                100
                        )
                );

        processTable
                .getTableHeader()
                .setForeground(WHITE);

        processTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        processTable
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // PROCESS CONTROLS
        // =====================================================

        JPanel controlArea =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        controlArea.setBackground(CARD);

        JLabel controlLabel =
                new JLabel(
                        "Process Controls"
                );

        controlLabel.setForeground(LIGHT_TEXT);

        controlLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        controlArea.add(
                controlLabel,
                BorderLayout.NORTH
        );

        JPanel controls =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                8,
                                0
                        )
                );

        controls.setBackground(CARD);

        JButton startButton =
                createControlButton(
                        "▶ Start",
                        GREEN
                );

        JButton pauseButton =
                createControlButton(
                        "⏸ Pause",
                        ORANGE
                );

        JButton resumeButton =
                createControlButton(
                        "▶ Resume",
                        BLUE
                );

        JButton terminateButton =
                createControlButton(
                        "✕ Terminate",
                        RED
                );

        startButton.addActionListener(
                e -> changeProcessState("Start")
        );

        pauseButton.addActionListener(
                e -> changeProcessState("Pause")
        );

        resumeButton.addActionListener(
                e -> changeProcessState("Resume")
        );

        terminateButton.addActionListener(
                e -> changeProcessState("Terminate")
        );

        controls.add(startButton);
        controls.add(pauseButton);
        controls.add(resumeButton);
        controls.add(terminateButton);

        controlArea.add(
                controls,
                BorderLayout.CENTER
        );

        panel.add(
                controlArea,
                BorderLayout.SOUTH
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
                        115,
                        40
                )
        );

        button.setBackground(color);

        button.setForeground(WHITE);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // CHANGE PROCESS STATE
    // =========================================================

    private void changeProcessState(
            String action
    ) {

        int selectedRow =
                processTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a process from the table first.",
                    "No Process Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String currentState =
                processTable
                        .getValueAt(
                                selectedRow,
                                5
                        )
                        .toString();

        String newState = null;

        if (action.equals("Start")) {

            if (currentState.equals("Ready")) {

                newState = "Running";

            } else {

                showInvalidTransition(
                        "Only a Ready process can be started."
                );

                return;
            }
        }

        if (action.equals("Pause")) {

            if (currentState.equals("Running")) {

                newState = "Waiting";

            } else {

                showInvalidTransition(
                        "Only a Running process can be paused."
                );

                return;
            }
        }

        if (action.equals("Resume")) {

            if (currentState.equals("Waiting")) {

                newState = "Ready";

            } else {

                showInvalidTransition(
                        "Only a Waiting process can be resumed."
                );

                return;
            }
        }

        if (action.equals("Terminate")) {

            if (
                    currentState.equals("Running")
                            || currentState.equals("Ready")
                            || currentState.equals("Waiting")
            ) {

                newState = "Terminated";

            } else {

                showInvalidTransition(
                        "This process is already terminated."
                );

                return;
            }
        }

        processTable.setValueAt(
                newState,
                selectedRow,
                5
        );

        updateDashboardCounters();

        String pid =
                processTable
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        JOptionPane.showMessageDialog(
                this,
                pid
                        + " state changed:\n\n"
                        + currentState
                        + " → "
                        + newState,
                "Process State Updated",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // INVALID TRANSITION
    // =========================================================

    private void showInvalidTransition(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Invalid Process State",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================================
    // UPDATE COUNTERS
    // =========================================================

    private void updateDashboardCounters() {

        if (tableModel == null) {
            return;
        }

        int total =
                tableModel.getRowCount();

        int running = 0;
        int ready = 0;

        for (
                int i = 0;
                i < total;
                i++
        ) {

            String state =
                    tableModel
                            .getValueAt(
                                    i,
                                    5
                            )
                            .toString();

            if (state.equals("Running")) {

                running++;

            } else if (
                    state.equals("Ready")
            ) {

                ready++;
            }
        }

        if (totalProcessesLabel != null) {

            totalProcessesLabel.setText(
                    String.valueOf(total)
            );
        }

        if (runningProcessesLabel != null) {

            runningProcessesLabel.setText(
                    "Running: "
                            + running
                            + " | Ready: "
                            + ready
            );
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    DashboardScreen dashboard =
                            new DashboardScreen();

                    dashboard.setVisible(true);
                }
        );
    }
}