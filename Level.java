import java.awt.*;
import java.awt.geom.*;

/**
 * This is the Level abstract class that contains all the methods for the
 * levels.
 * It draws various Rectangle2D.Double objects representing platforms and
 * 
 * @author Constantine P. Pazcoguin (243545)
 * @author Liora T. Ongsamson (243346)
 * @version May 20, 2025
 * 
 *          I have not discussed the Java language code in my program
 *          with anyone other than my instructor or the teaching assistants
 *          assigned to this course.
 * 
 *          I have not used Java language code obtained from another student,
 *          or any other unauthorized source, either modified or unmodified.
 * 
 *          If any Java language code or documentation used in my program
 *          was obtained from another source, such as a textbook or website,
 *          that has been clearly noted with a proper citation in the comments
 *          of my program.
 */

public abstract class Level {

    private Rectangle2D.Double[] platforms;

    /**
     * Constructor for the Level class.
     * 
     * @param platforms An array of Rectangle2D.Double objects representing the
     *                  platforms in the level.
     */
    public Level(Rectangle2D.Double[] platforms) {
        this.platforms = platforms;
    }

    public abstract Image getBackgroundImage();

    public abstract void draw(Graphics2D g2d);

    /**
     * Start of the getters for the platforms.
     */
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
