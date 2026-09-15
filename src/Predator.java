import java.awt.Color;
import java.awt.Graphics;

public class Predator extends Creature {

    public Predator(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.RED);

        g.fillOval(
            getX(),
            getY(),
            getSize(),
            getSize()
        );
    }
}