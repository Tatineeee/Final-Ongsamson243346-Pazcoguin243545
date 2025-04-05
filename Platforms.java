import javax.swing.*;
import java.awt.*;

public class Platforms extends JComponent {

    public Platforms(Graphics2D g2d) {
        Color color = Color.WHITE;
        int width = 880;
        int height = 20;
        Rectangle[] platforms = {
                new Rectangle(0, 118, width, height, color),
                new Rectangle(144, 223, width, height, color),
                new Rectangle(0, 328, width, height, color),
                new Rectangle(144, 433, width, height, color),
                new Rectangle(0, 538, width, height, color),
                new Rectangle(144, 643, width, height, color),
                new Rectangle(0, 748, 1024, height, color),
        };
        for (Rectangle platform : platforms) {
            platform.draw(g2d);
        }
    }

}
