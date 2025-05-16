import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class LevelTwo extends Level {

    private Image backgroundImage;
	private Spikes NewSpike;
	private AffineTransform standardPosition;

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
		NewSpike = new Spikes();
    }

    @Override
    public Image getBackgroundImage() {
        return backgroundImage;
    }
	


    @Override
    public void draw(Graphics2D g2d) {
		standardPosition = g2d.getTransform();
		g2d.setColor(Color.BLACK);
		Rectangle2D.Double door1 = new Rectangle2D.Double(800,250,70,80);
		Rectangle2D.Double door2 = new Rectangle2D.Double(880,250,70,80);
		Rectangle2D.Double lift2 = new Rectangle2D.Double(730,680,55,10);
		Rectangle2D.Double lift3 = new Rectangle2D.Double(690,600,55,10);
		Rectangle2D.Double lift4 = new Rectangle2D.Double(730,520,55,10);
		Rectangle2D.Double lift5 = new Rectangle2D.Double(690,440,55,10);
		Rectangle2D.Double lift6 = new Rectangle2D.Double(730,360,55,10);
		g2d.fill(door1);
		g2d.fill(door2);
		g2d.fill(lift2);
		g2d.fill(lift3);
		g2d.fill(lift4);
		g2d.fill(lift5);
		g2d.fill(lift6);
        g2d.setColor(new Color(45, 45, 45));
        for (int i = 0; i < getPlatformCount(); i++) {
            g2d.fill(getPlatform(i));
        }
		
		g2d.setColor(Color.WHITE);
		AffineTransform transform = AffineTransform.getTranslateInstance(300,1260);
		g2d.setTransform(transform);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform2 = AffineTransform.getTranslateInstance(360,1260);
		g2d.setTransform(transform2);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform3 = AffineTransform.getTranslateInstance(500,180);
		g2d.setTransform(transform3);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform4 = AffineTransform.getTranslateInstance(560,180);
		g2d.setTransform(transform4);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform5 = AffineTransform.getTranslateInstance(620,180);
		g2d.setTransform(transform5);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform6 = AffineTransform.getTranslateInstance(800,180);
		g2d.setTransform(transform6);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform7 = AffineTransform.getTranslateInstance(860,180);
		g2d.setTransform(transform7);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform8 = AffineTransform.getTranslateInstance(1400,220);
		g2d.setTransform(transform8);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform9 = AffineTransform.getTranslateInstance(1460,220);
		g2d.setTransform(transform9);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform10 = AffineTransform.getTranslateInstance(1520,220);
		g2d.setTransform(transform10);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform11 = AffineTransform.getTranslateInstance(1580,220);
		g2d.setTransform(transform11);
		g2d.fill(NewSpike.SpikeDraw());
		g2d.setTransform(standardPosition);
		
    }
}
