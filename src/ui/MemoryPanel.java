package ui;

import memory.MemoryManager;
import memory.MemoryManager.MemoryBlock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MemoryPanel extends JFrame {

    private final Color BACKGROUND = new Color(15, 30, 48);
    private final Color CARD = new Color(25, 48, 72);
    private final Color BLUE = new Color(20, 140, 240);
    private final Color GREEN = new Color(30, 190, 90);
    private final Color RED = new Color(210, 70, 70);
    private final Color WHITE = Color.WHITE;
    private final Color LIGHT_TEXT = new Color(180, 200, 220);

    private MemoryManager memoryManager;

    private JTextField processField;
    private JTextField memoryField;

    private JLabel totalLabel;
    private JLabel usedLabel;
    private JLabel freeLabel;
    private JLabel utilizationLabel;

    private JPanel memoryBlocksPanel;

    public MemoryPanel() {

        setTitle("Memory Manager");
        setSize(1000, 700);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        memoryManager = new MemoryManager(1024);

        createUI();
        updateDisplay();
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // Header
        JPanel header = new JPanel();

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBackground(BACKGROUND);

        JLabel title =
                new JLabel("Memory Manager");

        title.setForeground(WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Dynamic memory allocation and release"
                );

        subtitle.setForeground(LIGHT_TEXT);

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

        // Center
        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setBackground(BACKGROUND);

        center.add(
                createStatsPanel(),
                BorderLayout.NORTH
        );

        center.add(
                createMemoryPanel(),
                BorderLayout.CENTER
        );

        center.add(
                createControlPanel(),
                BorderLayout.SOUTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    private JPanel createStatsPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                10,
                                0
                        )
                );

        panel.setBackground(BACKGROUND);

        totalLabel =
                createStatCard(
                        panel,
                        "Total Memory",
                        "1024 MB"
                );

        usedLabel =
                createStatCard(
                        panel,
                        "Used Memory",
                        "0 MB"
                );

        freeLabel =
                createStatCard(
                        panel,
                        "Free Memory",
                        "1024 MB"
                );

        utilizationLabel =
                createStatCard(
                        panel,
                        "Utilization",
                        "0.00%"
                );

        return panel;
    }

    private JLabel createStatCard(
            JPanel parent,
            String title,
            String value
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                5,
                                5
                        )
                );

        card.setBackground(CARD);

        card.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setForeground(
                LIGHT_TEXT
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setForeground(WHITE);

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        parent.add(card);

        return valueLabel;
    }

    private JPanel createMemoryPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(CARD);

        panel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JLabel title =
                new JLabel("Memory Blocks");

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

        memoryBlocksPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                15
                        )
                );

        memoryBlocksPanel.setBackground(CARD);

        JScrollPane scrollPane =
                new JScrollPane(
                        memoryBlocksPanel
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createControlPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(CARD);

        panel.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
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

        JLabel processLabel =
                new JLabel("Process ID");

        processLabel.setForeground(
                LIGHT_TEXT
        );

        processField =
                new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;

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

        JLabel memoryLabel =
                new JLabel("Memory (MB)");

        memoryLabel.setForeground(
                LIGHT_TEXT
        );

        memoryField =
                new JTextField();

        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(
                memoryLabel,
                gbc
        );

        gbc.gridx = 3;
        gbc.weightx = 1;

        panel.add(
                memoryField,
                gbc
        );

        JButton allocateButton =
                new JButton("Allocate");

        allocateButton.setBackground(GREEN);
        allocateButton.setForeground(WHITE);
        allocateButton.setFocusPainted(false);
        allocateButton.setBorderPainted(false);

        allocateButton.addActionListener(
                e -> allocateMemory()
        );

        gbc.gridx = 4;
        gbc.weightx = 0;

        panel.add(
                allocateButton,
                gbc
        );

        JButton releaseButton =
                new JButton("Release");

        releaseButton.setBackground(RED);
        releaseButton.setForeground(WHITE);
        releaseButton.setFocusPainted(false);
        releaseButton.setBorderPainted(false);

        releaseButton.addActionListener(
                e -> releaseMemory()
        );

        gbc.gridx = 5;

        panel.add(
                releaseButton,
                gbc
        );

        JButton clearButton =
                new JButton("Clear Memory");

        clearButton.setBackground(BLUE);
        clearButton.setForeground(WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);

        clearButton.addActionListener(
                e -> clearMemory()
        );

        gbc.gridx = 6;

        panel.add(
                clearButton,
                gbc
        );

        return panel;
    }

    private void allocateMemory() {

        String processId =
                processField.getText().trim();

        String memoryText =
                memoryField.getText().trim();

        if (processId.isEmpty()
                || memoryText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Process ID and Memory."
            );

            return;
        }

        try {

            int memory =
                    Integer.parseInt(memoryText);

            if (memory <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Memory must be greater than 0."
                );

                return;
            }

            boolean allocated =
                    memoryManager.allocate(
                            processId,
                            memory
                    );

            if (allocated) {

                JOptionPane.showMessageDialog(
                        this,
                        processId
                                + " allocated "
                                + memory
                                + " MB."
                );

                processField.setText("");
                memoryField.setText("");

                updateDisplay();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Memory allocation failed.\n"
                                + "Check available memory or Process ID."
                );
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Memory must be a number."
            );
        }
    }

    private void releaseMemory() {

        String processId =
                processField.getText().trim();

        if (processId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter Process ID to release."
            );

            return;
        }

        boolean released =
                memoryManager.release(
                        processId
                );

        if (released) {

            JOptionPane.showMessageDialog(
                    this,
                    processId
                            + " memory released."
            );

            processField.setText("");

            updateDisplay();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Process not found."
            );
        }
    }

    private void clearMemory() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Clear all allocated memory?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            memoryManager.clearMemory();

            updateDisplay();
        }
    }

    private void updateDisplay() {

        int total =
                memoryManager.getTotalMemory();

        int used =
                memoryManager.getUsedMemory();

        int free =
                memoryManager.getFreeMemory();

        double utilization =
                memoryManager.getMemoryUtilization();

        totalLabel.setText(
                total + " MB"
        );

        usedLabel.setText(
                used + " MB"
        );

        freeLabel.setText(
                free + " MB"
        );

        utilizationLabel.setText(
                String.format(
                        "%.2f%%",
                        utilization
                )
        );

        updateMemoryBlocks();
    }

    private void updateMemoryBlocks() {

        memoryBlocksPanel.removeAll();

        List<MemoryBlock> blocks =
                memoryManager.getBlocks();

        if (blocks.isEmpty()) {

            JLabel emptyLabel =
                    new JLabel(
                            "No memory allocated yet."
                    );

            emptyLabel.setForeground(
                    LIGHT_TEXT
            );

            memoryBlocksPanel.add(
                    emptyLabel
            );

        } else {

            for (MemoryBlock block : blocks) {

                JPanel blockPanel =
                        new JPanel(
                                new BorderLayout(
                                        5,
                                        5
                                )
                        );

                blockPanel.setPreferredSize(
                        new Dimension(
                                150,
                                90
                        )
                );

                blockPanel.setBackground(
                        block.allocated
                                ? BLUE
                                : new Color(
                                        55,
                                        75,
                                        90
                                )
                );

                blockPanel.setBorder(
                        BorderFactory.createLineBorder(
                                WHITE
                        )
                );

                JLabel blockLabel =
                        new JLabel(
                                "Block "
                                        + block.blockId,
                                SwingConstants.CENTER
                        );

                blockLabel.setForeground(
                        WHITE
                );

                blockLabel.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

                String processText =
                        block.allocated
                                ? block.processId
                                : "FREE";

                JLabel processLabel =
                        new JLabel(
                                processText,
                                SwingConstants.CENTER
                        );

                processLabel.setForeground(
                        WHITE
                );

                JLabel sizeLabel =
                        new JLabel(
                                block.size
                                        + " MB",
                                SwingConstants.CENTER
                        );

                sizeLabel.setForeground(
                        WHITE
                );

                blockPanel.add(
                        blockLabel,
                        BorderLayout.NORTH
                );

                blockPanel.add(
                        processLabel,
                        BorderLayout.CENTER
                );

                blockPanel.add(
                        sizeLabel,
                        BorderLayout.SOUTH
                );

                memoryBlocksPanel.add(
                        blockPanel
                );
            }
        }

        memoryBlocksPanel.revalidate();
        memoryBlocksPanel.repaint();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MemoryPanel memoryPanel =
                    new MemoryPanel();

            memoryPanel.setVisible(true);
        });
    }
}