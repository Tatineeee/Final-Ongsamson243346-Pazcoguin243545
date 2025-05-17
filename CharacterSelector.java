import java.awt.*;
import javax.swing.*;

/**
 * This class handles the screen for the character selection.
 * It includes the backgrounds for each player and each variation.
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

public class CharacterSelector {

	private Image backgroundImage, backgroundImage1, backgroundImage2, backgroundImage3, backgroundImage4,
			backgroundImage5;

	/**
	 * Constructor for the CharacterSelector class.
	 * It initializes the images for the character selection screen.
	 */
	public CharacterSelector() {
		backgroundImage = new ImageIcon(getClass().getResource("/intro/character.PNG")).getImage();
		backgroundImage1 = new ImageIcon(getClass().getResource("/intro/character1.PNG")).getImage();
		backgroundImage2 = new ImageIcon(getClass().getResource("/intro/character2.PNG")).getImage();
		backgroundImage3 = new ImageIcon(getClass().getResource("/intro/character3.PNG")).getImage();
		backgroundImage4 = new ImageIcon(getClass().getResource("/intro/character4.PNG")).getImage();
		backgroundImage5 = new ImageIcon(getClass().getResource("/intro/character5.PNG")).getImage();
	}

	/**
	 * Returns the images for the character selection screen.
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public Image getBackgroundImage1() {
		return backgroundImage1;
	}

	public Image getBackgroundImage2() {
		return backgroundImage2;
	}

	public Image getBackgroundImage3() {
		return backgroundImage3;
	}

	public Image getBackgroundImage4() {
		return backgroundImage4;
	}

	public Image getBackgroundImage5() {
		return backgroundImage5;
	}

}