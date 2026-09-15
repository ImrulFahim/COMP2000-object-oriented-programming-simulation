import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class SimulationPanel extends JPanel {

    private ArrayList<Prey> preyList;
    private Predator predator;

    private int caughtCount = 0;
    private int timerCount = 0;

    public SimulationPanel() {

        preyList = new ArrayList<Prey>();

        preyList.add(new Prey(100, 100));
        preyList.add(new Prey(200, 400));
        preyList.add(new Prey(350, 150));
        preyList.add(new Prey(500, 400));
        preyList.add(new Prey(650, 200));

        predator = new Predator(400, 300);

        Timer timer = new Timer(20, e -> {

            timerCount = timerCount + 1;

            // Move and flee
            for (int i = 0; i < preyList.size(); i++) {

                Prey prey = preyList.get(i);

                prey.move(
                    getWidth(),
                    getHeight()
                );

                prey.flee(
                    predator,
                    getWidth(),
                    getHeight()
                );
            }

            // Only chase if prey still exist
            if (preyList.size() > 0) {

                Prey closestPrey = preyList.get(0);

                int closestDistance = Integer.MAX_VALUE;

                // Find closest prey
                for (int i = 0; i < preyList.size(); i++) {

                    Prey currentPrey = preyList.get(i);

                    int xDifference =
                        predator.getX() - currentPrey.getX();

                    int yDifference =
                        predator.getY() - currentPrey.getY();

                    int distance =
                        xDifference * xDifference +
                        yDifference * yDifference;

                    if (distance < closestDistance) {

                        closestDistance = distance;
                        closestPrey = currentPrey;
                    }
                }

                predator.chase(
                    closestPrey,
                    getWidth(),
                    getHeight()
                );

                // Check if predator caught prey
                for (int i = 0; i < preyList.size(); i++) {

                    if (predator.catches(preyList.get(i))) {

                        preyList.remove(i);

                        caughtCount = caughtCount + 1;

                        break;
                    }
                }
            }

            // New prey is born about every 5 seconds
            if (timerCount >= 250) {

                if (preyList.size() < 12) {

                    int newX =
                        (int)(Math.random() *
                        Math.max(1, getWidth() - 30));

                    int newY =
                        (int)(Math.random() *
                        Math.max(1, getHeight() - 30));

                    preyList.add(
                        new Prey(newX, newY)
                    );
                }

                timerCount = 0;
            }

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw prey
        for (int i = 0; i < preyList.size(); i++) {
            preyList.get(i).draw(g);
        }

        // Draw predator
        predator.draw(g);

        // Information
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString(
            "Prey caught: " + caughtCount,
            20,
            30
        );

        g.drawString(
            "Prey alive: " + preyList.size(),
            20,
            55
        );
    }
}