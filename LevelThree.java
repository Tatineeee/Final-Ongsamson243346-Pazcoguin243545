import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class LevelThree extends Level {

    private Image backgroundImage;

    public LevelThree() {
        super(new Rectangle2D.Double[] {
                new Rectangle2D.Double(0, 758, 1024, 10),
                new Rectangle2D.Double(0, 560, 713, 20),
                new Rectangle2D.Double(24, 275, 83, 10),
                new Rectangle2D.Double(24, 454, 83, 10),
                new Rectangle2D.Double(94, 676, 128, 10),
                new Rectangle2D.Double(108, 359, 947, 20),
                new Rectangle2D.Double(134, 213, 83, 10),
                new Rectangle2D.Double(170, 505, 119, 10),
                new Rectangle2D.Double(245, 183, 779, 20),
                new Rectangle2D.Double(255, 697, 67, 10),
                new Rectangle2D.Double(371, 727, 67, 10),
                new Rectangle2D.Double(400, 0, 20, 92),
                new Rectangle2D.Double(400, 92, 355, 20),
                new Rectangle2D.Double(489, 305, 126, 10),
                new Rectangle2D.Double(707, 651, 247, 20),
                new Rectangle2D.Double(838, 127, 66, 10)
        });
        backgroundImage = new ImageIcon(getClass().getResource("/level-assets/level3.png")).getImage();
    }

    @Override
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		Rectangle2D.Double door1 = new Rectangle2D.Double(500,20,70,80);
		Rectangle2D.Double door2 = new Rectangle2D.Double(580,20,70,80);
		g2d.fill(door1);
		g2d.fill(door2);
        g2d.setColor(new Color(45, 45, 45));
        for (int i = 0; i < getPlatformCount(); i++) {
            g2d.fill(getPlatform(i));
        }
    }
}
