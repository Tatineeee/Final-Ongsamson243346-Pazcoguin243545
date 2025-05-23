import java.awt.*;
import javax.swing.*;

/**
 * This class handles the overlay for the HUD of each level.
 * It includes the overlay for level 1 and level 2.
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

public class Overlay {

	private Image lvl1Overlay;

	/**
	 * Initializes the overlay image for level 1.
	 */
	public Overlay() {
		lvl1Overlay = new ImageIcon(getClass().getResource("/intro/overlay_lvl1.PNG")).getImage();
	}

	/**
	 * @return the overlay image for level 1.
	 */
	public Image getOverlayLVL1() {
		return lvl1Overlay;
	}

}