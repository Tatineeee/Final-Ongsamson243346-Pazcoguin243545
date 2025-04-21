import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class LevelTwo extends Level {

    private Image backgroundImage;

    public LevelTwo() {
        super(new Rectangle2D.Double[] {
                new Rectangle2D.Double(0, 758, 1024, 10),
                new Rectangle2D.Double(32, 239, 91, 20),
                new Rectangle2D.Double(77, 653, 329, 20),
                new Rectangle2D.Double(120, 158, 91, 20),
                new Rectangle2D.Double(120, 333, 91, 20),
                new Rectangle2D.Double(200, 414, 91, 20),
                new Rectangle2D.Double(210, 113, 482, 20),
                new Rectangle2D.Double(256, 482, 91, 20),
                new Rectangle2D.Double(290, 570, 91, 20),
                new Rectangle2D.Double(673, 133, 170, 20),
                new Rectangle2D.Double(780, 323, 244, 20),
                new Rectangle2D.Double(823, 0, 20, 133)
        });
        backgroundImage = new ImageIcon(getClass().getResource("/level-assets/level2.png")).getImage();
    }

    @Override
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(45, 45, 45));
        for (int i = 0; i < getPlatformCount(); i++) {
            g2d.fill(getPlatform(i));
        }
    }
}
