import java.awt.*;
import javax.swing.*;

/**
 * This class loads and handles the images for the end screen results.
 * There are 4 images for the ends screen, plus the game over screen.
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

public class EndScreenSelector {

	private Image gameoverImage;
	private Image finishImageA, finishImageB, finishImageC, finishImageF;

	/**
	 * Initializes the images for the end screen and game over screen.
	 */
	public EndScreenSelector() {
		gameoverImage = new ImageIcon(getClass().getResource("/intro/gameover.PNG")).getImage();
		finishImageA = new ImageIcon(getClass().getResource("/intro/finished_A.PNG")).getImage();
		finishImageB = new ImageIcon(getClass().getResource("/intro/finished_B.PNG")).getImage();
		finishImageC = new ImageIcon(getClass().getResource("/intro/finished_C.PNG")).getImage();
		finishImageF = new ImageIcon(getClass().getResource("/intro/finished_F.PNG")).getImage();
	}

	/**
	 * @return the images for the end screen and game over screen.
	 */
	public Image getGameOverImage() {
		return gameoverImage;
	}

	public Image getFinishImageA() {
		return finishImageA;
	}

	public Image getFinishImageB() {
		return finishImageB;
	}

	public Image getFinishImageC() {
		return finishImageC;
	}

	public Image getFinishImageF() {
		return finishImageF;
	}

}