import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public abstract class Level {

    private Rectangle2D.Double[] platforms;

    public Level(Rectangle2D.Double[] platforms) {
        this.platforms = platforms;
    }

    public abstract Image getBackgroundImage();

    public abstract void draw(Graphics2D g2d);

    public Rectangle2D.Double getPlatform(int index) {
        return platforms[index];
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

    public int getPlatformCount() {
        return platforms.length;
    }

}
