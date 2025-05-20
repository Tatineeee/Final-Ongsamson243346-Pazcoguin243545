import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * This is the LevelOne class, the first level that extends the Level class.
 * It draws various Rectangle2D.Double objects representing platforms and
 * spikes.
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

public class LevelOne extends Level {

	private Image backgroundImage;
	private AffineTransform standardPosition;
	private Spikes newSpike;

	/**
	 * Constructor for the LevelOne class.
	 * It initializes the platforms and background image.
	 * 
	 * @param platforms An array of Rectangle2D.Double objects representing the
	 *                  platforms in the level.
	 */
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
		newSpike = new Spikes();
	}

	/**
	 * Draws the level, including platforms, doors, and spikes.
	 * 
	 * @param g2d The Graphics2D object used for drawing.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		standardPosition = g2d.getTransform();
		g2d.setColor(Color.BLACK);
		Rectangle2D.Double door1 = new Rectangle2D.Double(790, 110, 50, 80);
		Rectangle2D.Double door2 = new Rectangle2D.Double(850, 110, 50, 80);
		g2d.fill(door1);
		g2d.fill(door2);
		g2d.setColor(new Color(45, 45, 45));
		for (int i = 0; i < getPlatformCount(); i++) {
			g2d.fill(getPlatform(i));
		}
		g2d.setColor(Color.WHITE);
		AffineTransform transform = AffineTransform.getTranslateInstance(0,475);
		g2d.setTransform(transform);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform2 = AffineTransform.getTranslateInstance(30,475);
		g2d.setTransform(transform2);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform3 = AffineTransform.getTranslateInstance(60,475);
		g2d.setTransform(transform3);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform4 = AffineTransform.getTranslateInstance(350,180);
		g2d.setTransform(transform4);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform5 = AffineTransform.getTranslateInstance(380,180);
		g2d.setTransform(transform5);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform6 = AffineTransform.getTranslateInstance(410,180);
		g2d.setTransform(transform6);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform7 = AffineTransform.getTranslateInstance(145,180);
		g2d.setTransform(transform7);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform8 = AffineTransform.getTranslateInstance(175,180);
		g2d.setTransform(transform8);
		g2d.fill(newSpike.SpikeDraw());
		AffineTransform transform9 = AffineTransform.getTranslateInstance(650,385);
		g2d.setTransform(transform9);
		g2d.fill(NewSpike.SpikeDraw());
		AffineTransform transform10 = AffineTransform.getTranslateInstance(680,385);
		g2d.setTransform(transform10);
		g2d.fill(NewSpike.SpikeDraw());
		g2d.setTransform(standardPosition);
	}

	/**
	 * Returns the background image of the level.
	 */
	@Override
	public Image getBackgroundImage() {
		return backgroundImage;
	}

}
