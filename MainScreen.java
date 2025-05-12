import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MainScreen{
	
	private Image backgroundImage, bufferImage;
	public MainScreen(){
		backgroundImage = new ImageIcon(getClass().getResource("/intro/intro.png")).getImage();
		bufferImage = new ImageIcon(getClass().getResource("/intro/buffer.jpg")).getImage();
	}
	
	public Image getBackgroundImage() {
        return backgroundImage;
    }
	
	public Image getBufferImage(){
		return bufferImage;
	}
}