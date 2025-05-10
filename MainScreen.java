import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MainScreen{
	
	private Image backgroundImage;
	public MainScreen(){
		backgroundImage = new ImageIcon(getClass().getResource("/intro/intro.png")).getImage();
	}
	
	public Image getBackgroundImage() {
        return backgroundImage;
    }
}