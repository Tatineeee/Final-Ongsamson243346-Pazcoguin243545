import java.awt.*;
import javax.swing.*;

/**
 * This is the class that handles and contains all the items in the game.
 * It loads the images from the folders and returns the images for the items.
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

public class Items {

	private Image book1, book2, book3;
	private Image water1, water2, water3;
	private Image cassette1, cassette2, cassette3;
	private Image weapon1, weapon2, weapon3;
	private Image painting1, painting2, painting3;
	private Image bear1, bear2, bear3;

	/**
	 * Constructor for the Items class.
	 * It initializes the images for all the items in the game.
	 */
	public Items() {
		book1 = new ImageIcon(getClass().getResource("/items/book1.PNG")).getImage();
		book2 = new ImageIcon(getClass().getResource("/items/book2.PNG")).getImage();
		book3 = new ImageIcon(getClass().getResource("/items/book3.PNG")).getImage();
		water1 = new ImageIcon(getClass().getResource("/items/water1.PNG")).getImage();
		water2 = new ImageIcon(getClass().getResource("/items/water2.PNG")).getImage();
		water3 = new ImageIcon(getClass().getResource("/items/water3.PNG")).getImage();
		cassette1 = new ImageIcon(getClass().getResource("/items/cassette1.PNG")).getImage();
		cassette2 = new ImageIcon(getClass().getResource("/items/cassette2.PNG")).getImage();
		cassette3 = new ImageIcon(getClass().getResource("/items/cassette3.PNG")).getImage();
		weapon1 = new ImageIcon(getClass().getResource("/items/weapon1.PNG")).getImage();
		weapon2 = new ImageIcon(getClass().getResource("/items/weapon2.PNG")).getImage();
		weapon3 = new ImageIcon(getClass().getResource("/items/weapon3.PNG")).getImage();
		painting1 = new ImageIcon(getClass().getResource("/items/painting1.PNG")).getImage();
		painting2 = new ImageIcon(getClass().getResource("/items/painting2.PNG")).getImage();
		painting3 = new ImageIcon(getClass().getResource("/items/painting3.PNG")).getImage();
		bear1 = new ImageIcon(getClass().getResource("/items/bear1.PNG")).getImage();
		bear2 = new ImageIcon(getClass().getResource("/items/bear2.PNG")).getImage();
		bear3 = new ImageIcon(getClass().getResource("/items/bear3.PNG")).getImage();
	}

	/**
	 * Returns the images for all the items in the game.
	 */
	public Image getBookImage1() {
		return book1;
	}

	public Image getBookImage2() {
		return book2;
	}

	public Image getBookImage3() {
		return book3;
	}

	public Image getWaterImage1() {
		return water1;
	}

	public Image getWaterImage2() {
		return water2;
	}

	public Image getWaterImage3() {
		return water3;
	}

	public Image getCassetteImage1() {
		return cassette1;
	}

	public Image getCassetteImage2() {
		return cassette2;
	}

	public Image getCassetteImage3() {
		return cassette3;
	}

	public Image getWeaponImage1() {
		return weapon1;
	}

	public Image getWeaponImage2() {
		return weapon2;
	}

	public Image getWeaponImage3() {
		return weapon3;
	}

	public Image getPaintingImage1() {
		return painting1;
	}

	public Image getPaintingImage2() {
		return painting2;
	}

	public Image getPaintingImage3() {
		return painting3;
	}

	public Image getBearImage1() {
		return bear1;
	}

	public Image getBearImage2() {
		return bear2;
	}

	public Image getBearImage3() {
		return bear3;
	}

}