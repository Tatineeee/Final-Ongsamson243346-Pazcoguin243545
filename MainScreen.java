import java.awt.*;
import javax.swing.*;

/**
 * This is the class for the main screen of the game.
 * It loads the background image and buffer image.
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

public class MainScreen {

	private Image backgroundImage;

	/**
	 * Constructor for the MainScreen class.
	 * It initializes the background image.
	 */
	public MainScreen() {
		backgroundImage = new ImageIcon(getClass().getResource("/intro/intro.png")).getImage();
	}

	/**
	 * Returns the background image.
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}


}