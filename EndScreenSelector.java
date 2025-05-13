import java.awt.*;
import javax.swing.*;

public class EndScreenSelector {

	private Image gameoverImage, finishImageA, finishImageB, finishImageC, finishImageF;

	public EndScreenSelector() {
		gameoverImage = new ImageIcon(getClass().getResource("/intro/gameover.PNG")).getImage();
		finishImageA = new ImageIcon(getClass().getResource("/intro/finished_A.PNG")).getImage();
		finishImageB = new ImageIcon(getClass().getResource("/intro/finished_B.PNG")).getImage();
		finishImageC = new ImageIcon(getClass().getResource("/intro/finished_C.PNG")).getImage();
		finishImageF = new ImageIcon(getClass().getResource("/intro/finished_F.PNG")).getImage();
	}

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