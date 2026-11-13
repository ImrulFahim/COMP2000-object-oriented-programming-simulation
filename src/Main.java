import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Predator Prey Simulation");

        SimulationPanel panel = new SimulationPanel();

        JComboBox<String> targeting = new JComboBox<>(
            new String[] {"Nearest prey", "Oldest surviving prey"}
        );
        targeting.addActionListener(e -> panel.setTargetingStrategy(
            targeting.getSelectedIndex() == 0
                ? new NearestPreyStrategy() : new OldestPreyStrategy()
        ));
        frame.add(targeting, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        });
    }
}
