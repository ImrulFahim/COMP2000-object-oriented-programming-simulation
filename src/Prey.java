import java.awt.Color;
import java.awt.Graphics;

public class Prey extends Creature {

    public Prey(int x, int y) {
        super(x, y);
    }

    public void flee(Predator predator, int width, int height) {

        int xDifference = getX() - predator.getX();
        int yDifference = getY() - predator.getY();

        int distanceSquared =
            xDifference * xDifference +
            yDifference * yDifference;

        int dangerDistance = 150;

        if (distanceSquared < dangerDistance * dangerDistance) {

            int fleeSpeed = 4;

            if (getX() < predator.getX()) {
                setX(getX() - fleeSpeed);
            }

            if (getX() > predator.getX()) {
                setX(getX() + fleeSpeed);
            }

            if (getY() < predator.getY()) {
                setY(getY() - fleeSpeed);
            }

            if (getY() > predator.getY()) {
                setY(getY() + fleeSpeed);
            }
        }

        if (getX() < 0) {
            setX(0);
        }

        if (getY() < 0) {
            setY(0);
        }

        if (getX() > width - getSize()) {
            setX(width - getSize());
        }

        if (getY() > height - getSize()) {
            setY(height - getSize());
        }
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