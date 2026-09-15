import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;

public class SimulationPanel extends JPanel {

    private Creature prey;
    private Creature predator;

    public SimulationPanel() {

        prey = new Prey(100, 100);

        predator = new Predator(500, 300);

        Timer timer = new Timer(20, e -> {

            prey.move(getWidth(), getHeight());

            predator.move(getWidth(), getHeight());

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        prey.draw(g);

        predator.draw(g);
    }
}