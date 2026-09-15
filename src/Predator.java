import java.awt.Color;
import java.awt.Graphics;

public class Predator extends Creature {

    public Predator(int x, int y) {
        super(x, y);
    }

    public void chase(Prey prey, int width, int height) {

        int speed = 3;

        if (getX() < prey.getX()) {
            setX(getX() + speed);
        }

        if (getX() > prey.getX()) {
            setX(getX() - speed);
        }

        if (getY() < prey.getY()) {
            setY(getY() + speed);
        }

        if (getY() > prey.getY()) {
            setY(getY() - speed);
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

    public boolean catches(Prey prey) {

        int xDifference = getX() - prey.getX();
        int yDifference = getY() - prey.getY();

        int distanceSquared =
            xDifference * xDifference +
            yDifference * yDifference;

        int catchDistance = getSize();

        return distanceSquared < catchDistance * catchDistance;
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