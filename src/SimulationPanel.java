import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class SimulationPanel extends JPanel {

    private Prey prey;
    private Predator predator;

    private int caughtCount = 0;

    public SimulationPanel() {

        prey = new Prey(100, 100);
        predator = new Predator(500, 300);

        Timer timer = new Timer(20, e -> {

            prey.move(
                getWidth(),
                getHeight()
            );

            predator.chase(
                prey,
                getWidth(),
                getHeight()
            );

            if (predator.catches(prey)) {

                caughtCount = caughtCount + 1;

                int newX =
                    (int)(Math.random() *
                    Math.max(1, getWidth() - 30));

                int newY =
                    (int)(Math.random() *
                    Math.max(1, getHeight() - 30));

                prey = new Prey(newX, newY);
            }

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        prey.draw(g);
        predator.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString(
            "Prey caught: " + caughtCount,
            20,
            30
        );
    }
}