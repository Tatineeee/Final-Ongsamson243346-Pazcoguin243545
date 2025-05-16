import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class LevelOne extends Level {

    private Image backgroundImage;
	private Spikes NewSpike;
	private AffineTransform standardPosition;

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
		Rectangle2D.Double door1 = new Rectangle2D.Double(800,110,70,80);
		Rectangle2D.Double door2 = new Rectangle2D.Double(880,110,70,80);
		Rectangle2D.Double door3 = new Rectangle2D.Double(30,475,25,25);
		Rectangle2D.Double door4 = new Rectangle2D.Double(350,180,25,25);
		g2d.fill(door1);
		g2d.fill(door2);
		g2d.fill(door3);
		g2d.fill(door4);
		g2d.fill(new Rectangle2D.Double(410,180,25,25));
		g2d.fill(new Rectangle2D.Double(650,385,25,25));
        g2d.setColor(new Color(45, 45, 45));
        for (int i = 0; i < getPlatformCount(); i++) {
            g2d.fill(getPlatform(i));
        }
		g2d.setColor(Color.WHITE);
		AffineTransform transform = AffineTransform.getTranslateInstance(0,950);
		g2d.setTransform(transform);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform2 = AffineTransform.getTranslateInstance(60,950);
		g2d.setTransform(transform2);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform3 = AffineTransform.getTranslateInstance(120,950);
		g2d.setTransform(transform3);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform4 = AffineTransform.getTranslateInstance(700,360);
		g2d.setTransform(transform4);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform5 = AffineTransform.getTranslateInstance(760,360);
		g2d.setTransform(transform5);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform6 = AffineTransform.getTranslateInstance(820,360);
		g2d.setTransform(transform6);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform7 = AffineTransform.getTranslateInstance(290,360);
		g2d.setTransform(transform7);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform8 = AffineTransform.getTranslateInstance(350,360);
		g2d.setTransform(transform8);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform9 = AffineTransform.getTranslateInstance(1300,770);
		g2d.setTransform(transform9);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform10 = AffineTransform.getTranslateInstance(1360,770);
		g2d.setTransform(transform10);
		g2d.fill(NewSpike.SpikeDraw());
		g2d.setTransform(standardPosition);
    }

}
