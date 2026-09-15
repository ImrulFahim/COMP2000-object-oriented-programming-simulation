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

        addPrey(100, 100);
        addPrey(200, 400);
        addPrey(350, 150);
        addPrey(500, 400);
        addPrey(650, 200);

        predator = new Predator(400, 300);

        Timer timer = new Timer(20, e -> {

            timerCount = timerCount + 1;

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

            if (preyList.size() > 0) {

                Prey closestPrey = preyList.get(0);

                int closestDistance = Integer.MAX_VALUE;

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

                for (int i = 0; i < preyList.size(); i++) {

                    if (predator.catches(preyList.get(i))) {

                        preyList.remove(i);

                        caughtCount = caughtCount + 1;

                        break;
                    }
                }
            }

            if (timerCount >= 250) {

                if (preyList.size() < 12) {

                    int newX =
                        (int)(Math.random() *
                        Math.max(1, getWidth() - 30));

                    int newY =
                        (int)(Math.random() *
                        Math.max(1, getHeight() - 30));

                    addPrey(newX, newY);
                }

                timerCount = 0;
            }

            repaint();
        });

        timer.start();
    }

    private void addPrey(int x, int y) {

        try {

            preyList.add(
                new Prey(x, y)
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Could not create prey: " + e.getMessage()
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int i = 0; i < preyList.size(); i++) {
            preyList.get(i).draw(g);
        }

        predator.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

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