import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class LevelOne extends Level {

    private Image backgroundImage;

    public LevelOne() {
        super(new Rectangle2D.Double[] {
                new Rectangle2D.Double(0, 758, 1024, 10),
                new Rectangle2D.Double(0, 502, 350, 20),
                new Rectangle2D.Double(0, 206, 234, 20),
                new Rectangle2D.Double(77, 410, 108, 20),
                new Rectangle2D.Double(224, 706, 127, 62),
                new Rectangle2D.Double(234, 311, 294, 20),
                new Rectangle2D.Double(350, 206, 117, 20),
                new Rectangle2D.Double(413, 608, 127, 20),
                new Rectangle2D.Double(447, 0, 20, 206),
                new Rectangle2D.Double(582, 410, 220, 20),
                new Rectangle2D.Double(595, 216, 109, 20),
                new Rectangle2D.Double(764, 608, 260, 20),
                new Rectangle2D.Double(771, 186, 253, 20),
                new Rectangle2D.Double(803, 506, 85, 20)
        });
        backgroundImage = new ImageIcon(getClass().getResource("/level-assets/level1.png")).getImage();
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
