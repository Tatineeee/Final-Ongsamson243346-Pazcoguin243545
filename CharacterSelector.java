import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CharacterSelector{
	
	private Image backgroundImage;
	private Image backgroundImage1;
	private Image backgroundImage2;
	private Image backgroundImage3;
	private Image backgroundImage4;
	private Image backgroundImage5;
	
	public CharacterSelector(){
		backgroundImage = new ImageIcon(getClass().getResource("/intro/character.PNG")).getImage();
		backgroundImage1 = new ImageIcon(getClass().getResource("/intro/character1.PNG")).getImage();
		backgroundImage2 = new ImageIcon(getClass().getResource("/intro/character2.PNG")).getImage();
		backgroundImage3 = new ImageIcon(getClass().getResource("/intro/character3.PNG")).getImage();
		backgroundImage4 = new ImageIcon(getClass().getResource("/intro/character4.PNG")).getImage();
		backgroundImage5 = new ImageIcon(getClass().getResource("/intro/character5.PNG")).getImage();
	}
	
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