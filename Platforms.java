import java.awt.*;
import java.awt.geom.*;

public class Platforms {

    private double width, height;
    private Rectangle2D.Double[] platforms;

    public Platforms() {
        width = 880;
        height = 20;
        platforms = new Rectangle2D.Double[7];
        platforms[0] = new Rectangle2D.Double(144, 223, width, height);
        platforms[1] = new Rectangle2D.Double(144, 433, width, height);
        platforms[2] = new Rectangle2D.Double(144, 643, width, height);
        platforms[3] = new Rectangle2D.Double(0, 118, width, height);
        platforms[4] = new Rectangle2D.Double(0, 328, width, height);
        platforms[5] = new Rectangle2D.Double(0, 538, width, height);
        platforms[6] = new Rectangle2D.Double(0, 748, 1024, height);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (Rectangle2D.Double platform : platforms) {
            g2d.fill(platform);
        }
    }

    public double getPlatformX(int index) {
        return platforms[index].getX();
    }

    public double getPlatformY(int index) {
        return platforms[index].getY();
    }

    public double getPlatformWidth(int index) {
        return platforms[index].getWidth();
    }

    public double getPlatformHeight(int index) {
        return platforms[index].getHeight();
    }

    public Rectangle2D.Double getPlatform(int index) {
        return platforms[index];
    }

    public int getPlatformCount() {
        return platforms.length;
    }

}
