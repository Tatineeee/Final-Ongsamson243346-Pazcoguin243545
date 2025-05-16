import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Lift{
	
	private Image lvl2;
	
	public Lift(){
		lvl2 = new ImageIcon(getClass().getResource("/level-assets/level2_lift.PNG")).getImage();
		
	}
	
	public Image getLiftImage() {
        return lvl2;
    }
	
}