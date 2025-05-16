import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Overlay {
	private Image lvl1Overlay;

	public Overlay() {
		lvl1Overlay = new ImageIcon(getClass().getResource("/intro/overlay_lvl1.PNG")).getImage();
	}

	public Image getOverlayLVL1() {
		return lvl1Overlay;
	}
}