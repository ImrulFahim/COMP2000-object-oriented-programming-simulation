import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;

public class SimulationPanel extends JPanel {

    private int x = 50;
    private int y = 250;

    public SimulationPanel() {

        Timer timer = new Timer(30, e -> {

            x = x + 2;

            if (x > getWidth()) {
                x = 0;
            }

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(x, y, 30, 30);
    }
}
