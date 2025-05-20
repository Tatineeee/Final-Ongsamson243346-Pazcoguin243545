import java.awt.*;
import javax.swing.*;

/**
 * This is the class that handles and contains all the animation images for the title screen in the game.
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
 
 public class AnimationImages {
	 private Image animation1, animation2, animation3;
	 
	 /**
	 * Constructor for the AnimationImages class.
	 * It initializes the images for all the animation images in the game.
	 */
	public AnimationImages() {
		
		animation1 = new ImageIcon(getClass().getResource("/intro/animation_1.PNG")).getImage();
		animation2 = new ImageIcon(getClass().getResource("/intro/animation_2.PNG")).getImage();
		animation3 = new ImageIcon(getClass().getResource("/intro/animation_3.PNG")).getImage();
	
	}
	
	public Image getAnimationImage1(){
		return animation1;
	}
	
	public Image getAnimationImage2(){
		return animation2;
	}
	
	public Image getAnimationImage3(){
		return animation3;
	}
	
 }