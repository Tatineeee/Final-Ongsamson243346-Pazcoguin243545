import java.awt.geom.*;

/**
 * This is the Spike class which acts as the spikes in the game.
 * It uses a Path2D.Double object to create the shape of the spikes.
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

public class Spikes {

	private int translateX, translateY;
	private Path2D.Double path;

	/**
	 * Constructor for the Spikes class.
	 * Initializes the translation values and creates the spike shape.
	 */
	public Spikes() {
		translateX = 0;
		translateY = 0;
		path = new Path2D.Double();
		path.moveTo(12.5, 0);
		path.lineTo(0, 25);
		path.lineTo(25, 25);
		path.closePath();
	}

	/**
	 * Constructor for the Spikes class with translation values.
	 * Initializes the translation values and creates the spike shape.
	 */
	public Path2D.Double SpikeDraw() {
		return path;
	}

	/**
	 * Gets the translation values for the spikes.
	 * 
	 * @param x The x-coordinate for translation.
	 * @param y The y-coordinate for translation.
	 */
	public int getPathX() {
		return translateX;
	}

	public int getPathY() {
		return translateY;
	}

}