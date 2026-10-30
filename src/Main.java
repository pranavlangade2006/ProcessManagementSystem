import ui.DashboardScreen;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DashboardScreen dashboard =
                    new DashboardScreen();

            dashboard.setVisible(true);
        });
    }
}