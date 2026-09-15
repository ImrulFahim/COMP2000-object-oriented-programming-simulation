import java.awt.Color;
import java.awt.Graphics;

public class Creature {

    private int x;
    private int y;

    private int xSpeed;
    private int ySpeed;

    private int size;

    public Creature(int x, int y) {

        this.x = x;
        this.y = y;

        xSpeed = (int)(Math.random() * 4) + 2;
        ySpeed = (int)(Math.random() * 4) + 2;

        size = 30;
    }

    public void move(int width, int height) {

        x = x + xSpeed;
        y = y + ySpeed;

        if (x <= 0) {
            x = 0;
            xSpeed = -xSpeed;
        }

        if (x >= width - size) {
            x = width - size;
            xSpeed = -xSpeed;
        }

        if (y <= 0) {
            y = 0;
            ySpeed = -ySpeed;
        }

        if (y >= height - size) {
            y = height - size;
            ySpeed = -ySpeed;
        }
    }

    public void draw(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillOval(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}

    