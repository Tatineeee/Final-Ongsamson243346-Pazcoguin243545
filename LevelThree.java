import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class LevelThree extends Level {

	private Image backgroundImage;
	private Spikes NewSpike;
	private AffineTransform standardPosition;

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
		Rectangle2D.Double door1 = new Rectangle2D.Double(500, 20, 70, 80);
		Rectangle2D.Double door2 = new Rectangle2D.Double(580, 20, 70, 80);
		g2d.fill(door1);
		g2d.fill(door2);
		g2d.setColor(new Color(45, 45, 45));
		for (int i = 0; i < getPlatformCount(); i++) {
			g2d.fill(getPlatform(i));
		}

		g2d.setColor(Color.WHITE);
		AffineTransform transform = AffineTransform.getTranslateInstance(300, 1300);
		g2d.setTransform(transform);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform2 = AffineTransform.getTranslateInstance(1600, 1250);
		g2d.setTransform(transform2);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform3 = AffineTransform.getTranslateInstance(1540, 1250);
		g2d.setTransform(transform3);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform4 = AffineTransform.getTranslateInstance(1660, 1250);
		g2d.setTransform(transform4);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform5 = AffineTransform.getTranslateInstance(1720, 1250);
		g2d.setTransform(transform5);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform6 = AffineTransform.getTranslateInstance(720, 1070);
		g2d.setTransform(transform6);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform7 = AffineTransform.getTranslateInstance(780, 1070);
		g2d.setTransform(transform7);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform8 = AffineTransform.getTranslateInstance(920, 1070);
		g2d.setTransform(transform8);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform9 = AffineTransform.getTranslateInstance(980, 1070);
		g2d.setTransform(transform9);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform10 = AffineTransform.getTranslateInstance(1040, 1070);
		g2d.setTransform(transform10);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform11 = AffineTransform.getTranslateInstance(200, 1070);
		g2d.setTransform(transform11);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform12 = AffineTransform.getTranslateInstance(140, 1070);
		g2d.setTransform(transform12);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform13 = AffineTransform.getTranslateInstance(260, 1070);
		g2d.setTransform(transform13);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform14 = AffineTransform.getTranslateInstance(440, 670);
		g2d.setTransform(transform14);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform15 = AffineTransform.getTranslateInstance(500, 670);
		g2d.setTransform(transform15);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform16 = AffineTransform.getTranslateInstance(560, 670);
		g2d.setTransform(transform16);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform17 = AffineTransform.getTranslateInstance(700, 670);
		g2d.setTransform(transform17);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform18 = AffineTransform.getTranslateInstance(760, 670);
		g2d.setTransform(transform18);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform19 = AffineTransform.getTranslateInstance(820, 670);
		g2d.setTransform(transform19);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform20 = AffineTransform.getTranslateInstance(1300, 670);
		g2d.setTransform(transform20);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform21 = AffineTransform.getTranslateInstance(1360, 670);
		g2d.setTransform(transform21);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform22 = AffineTransform.getTranslateInstance(1420, 670);
		g2d.setTransform(transform22);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform23 = AffineTransform.getTranslateInstance(600, 320);
		g2d.setTransform(transform23);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform24 = AffineTransform.getTranslateInstance(660, 320);
		g2d.setTransform(transform24);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform25 = AffineTransform.getTranslateInstance(1800, 320);
		g2d.setTransform(transform25);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform26 = AffineTransform.getTranslateInstance(1860, 320);
		g2d.setTransform(transform26);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform27 = AffineTransform.getTranslateInstance(1920, 320);
		g2d.setTransform(transform27);
		g2d.fill(NewSpike.SpikeDraw());

		g2d.setTransform(standardPosition);
	}
}
