import java.awt.*;
import java.awt.geom.*;

public class Player {

    private double x, y, size;
    private Color color;

    public Player(int w, int h, int s, Color c) {
        x = w;
        y = h;
        size = s;
        color = c;
    }

    public void testDrawSprite(Graphics2D g2d) {
        Rectangle2D.Double player = new Rectangle2D.Double(x, y, size, size);
        g2d.setColor(color);
        g2d.fill(player);
    }

    public void moveX(double n) {
        x += n;
    }

    public void moveY(double n) {
        y += n;
    }

    public void setX(double n) {
        x = n;
    }

    public void setY(double n) {
        y = n;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
