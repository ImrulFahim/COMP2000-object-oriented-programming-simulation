import java.awt.Color;
import java.awt.Graphics;

public class Prey extends Creature {

    public Prey(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.GREEN);

        g.fillOval(
            getX(),
            getY(),
            getSize(),
            getSize()
        );
    }
}