import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * This is the GameCanvas class that handles all the drawings and animations.
 * It contains the logic for the players, timer, items, and levels.
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

public class GameCanvas extends JComponent {

	private Player player1, player2, player3, player4, playerChoose1, playerChoose2;
	private int platforms;
	private int itemChecker1, itemChecker2;
	private int x1, y1, x2, y2, x3, y3;
	private Level platform1, platform2;
	private MainScreen intro;
	private Overlay overlayMaster;
	private Image introImage, characterImage, characterImage1, characterImage2, characterImage3, characterImage4,
			characterImage5, option;
	private Image deadImage, option2;
	private Image overlay1;
	private Image finishImageA, finishImageB, finishImageC, finishImageF;
	private Image book1, book2, book3, water1, water2, water3;
	private Image painting1, painting2, painting3, bear1, bear2, bear3;
	private Image animation1, animation2, animation3;
	private CharacterSelector character;
	private EndScreenSelector end;
	private Items item;
	private AnimationImages animationImages;
	private boolean removeCharacterSelector, timeTorF;

	private int lifty;
	private int player1X, player1Y, player2X, player2Y;

	public GameCanvas() {
		player1 = new Player1Alt(player1X, player1Y);
		player2 = new Player2Alt(player2X, player2Y);
		player3 = new Player1(player1X, player1Y);
		player4 = new Player2(player2X, player2Y);
		player1 = new Player1Alt(80, 758);
		player2 = new Player2Alt(900, 758);
		player3 = new Player1(80, 758);
		player4 = new Player2(900, 758);
		playerChoose1 = player1;
		playerChoose2 = player2;
		platform1 = new LevelOne();
		platform2 = new LevelTwo();
		platforms = 1;

		intro = new MainScreen();
		character = new CharacterSelector();
		overlayMaster = new Overlay();
		animationImages = new AnimationImages();

		animation1 = animationImages.getAnimationImage1();
		animation2 = animationImages.getAnimationImage2();
		animation3 = animationImages.getAnimationImage3();
		x1 = -30;
		y1 = -30;
		x2 = -30;
		y2 = 30;
		x3 = 30;
		y3 = 0;

		introImage = intro.getBackgroundImage();
		characterImage = character.getBackgroundImage();
		characterImage1 = character.getBackgroundImage1();
		characterImage2 = character.getBackgroundImage2();
		characterImage3 = character.getBackgroundImage3();
		characterImage4 = character.getBackgroundImage4();
		characterImage5 = character.getBackgroundImage5();
		option = introImage;
		overlay1 = overlayMaster.getOverlayLVL1();

		item = new Items();
		book1 = item.getBookImage1();
		book2 = item.getBookImage2();
		book3 = item.getBookImage3();
		water1 = item.getWaterImage1();
		water2 = item.getWaterImage2();
		water3 = item.getWaterImage3();
		painting1 = item.getPaintingImage1();
		painting2 = item.getPaintingImage2();
		painting3 = item.getPaintingImage3();
		bear1 = item.getBearImage1();
		bear2 = item.getBearImage2();
		bear3 = item.getBearImage3();
		itemChecker1 = 0;
		itemChecker2 = 0;

		end = new EndScreenSelector();
		deadImage = end.getGameOverImage();
		finishImageA = end.getFinishImageA();
		finishImageB = end.getFinishImageB();
		finishImageC = end.getFinishImageC();
		finishImageF = end.getFinishImageF();
		option2 = deadImage;

		removeCharacterSelector = true;
		timeTorF = true;

	}

	public void switchScreen(int checker) {
		if (checker == 1) {
			option = introImage;
		} else if (checker == 2) {
			option = characterImage;
		} else if (checker == 3) {
			option = characterImage1;
		} else if (checker == 4) {
			option = characterImage2;
		} else if (checker == 5) {
			option = characterImage3;
		} else if (checker == 6) {
			option = characterImage4;
		} else if (checker == 7) {
			option = characterImage5;
		} else if (checker == 8) {
			timeTorF = true;
			removeCharacterSelector = false;
		}
		repaint();
	}

	public void switchLevel(int checker) {
		if (checker == 1) {
			platforms = 1;
			player1X = 80;
			player1Y = 706;
			player2X = 900;
			player2Y = 706;
		} else if (checker == 2) {
			platforms = 2;
			player1X = 106;
			player1Y = 626;
			player2X = 169;
			player2Y = 626;
		}
		playerChoose1.setX(player1X);
		playerChoose1.setY(player1Y);
		playerChoose2.setX(player2X);
		playerChoose2.setY(player2Y);
		repaint();
	}

	public void switchEndScreen(int checker) {
		if (checker == 1) {
			option2 = deadImage;
		} else if (checker == 2) {
			option2 = finishImageA;
		} else if (checker == 3) {
			option2 = finishImageB;
		} else if (checker == 4) {
			option2 = finishImageC;
		} else if (checker == 5) {
			option2 = finishImageF;
		}
		repaint();
	}

	public void playerChoose(int p1, int p2) {
		if (p1 == 1) {
			playerChoose1 = player1;
		} else if (p1 == 2) {
			playerChoose1 = player3;
		}

		if (p2 == 1) {
			playerChoose2 = player2;
		} else if (p2 == 2) {
			playerChoose2 = player4;
		}

		repaint();
	}

	public void changeRemoveCharacterSelector() {
		removeCharacterSelector = true;
	}

	public void timesUp(int timeChecker) {
		if (timeChecker == 1) {
			timeTorF = false;
		}
	}

	public void timesUpPositive(int timeChecker) {
		if (timeChecker == 1) {
			timeTorF = true;
		}
	}

	public void fixItemChecker1(int num) {
		itemChecker1 = num;
		repaint();
	}

	public void fixItemChecker2(int num) {
		itemChecker2 = num;
		repaint();
	}

	public void animationImage1X(int x) {
		x1 = x;
		repaint();
	}

	public void animationImage1Y(int y) {
		y1 = y;
		repaint();
	}

	public void animationImage2X(int x) {
		x2 = x;
		repaint();
	}

	public void animationImage2Y(int y) {
		y2 = y;
		repaint();
	}

	public void animationImage3X(int x) {
		x3 = x;
		repaint();
	}

	public void animationImage3Y(int y) {
		y3 = y;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (removeCharacterSelector == false && timeTorF == true) {
			if (platforms == 1) {
				g2d.drawImage(platform1.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
				platform1.draw(g2d);
				playerChoose1.draw(g2d);
				playerChoose2.draw(g2d);

			} else if (platforms == 2) {
				g2d.drawImage(platform2.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
				platform2.draw(g2d);
				playerChoose1.draw(g2d);
				playerChoose2.draw(g2d);
			}

			if (platforms == 1 && itemChecker1 == 1) {
				g2d.drawImage(book1, 0, 0, getWidth(), getHeight(), this);
				g2d.drawImage(book3, 0, 0, getWidth(), getHeight(), this);
			} else if (platforms == 1 && itemChecker1 == 0) {
				g2d.drawImage(book2, 0, 0, getWidth(), getHeight(), this);
			}
			if (platforms == 1 && itemChecker2 == 1) {
				g2d.drawImage(water1, 0, 0, getWidth(), getHeight(), this);
				g2d.drawImage(water3, 0, 0, getWidth(), getHeight(), this);
			} else if (platforms == 1 && itemChecker2 == 0) {
				g2d.drawImage(water2, 0, 0, getWidth(), getHeight(), this);
			}

			if (platforms == 2 && itemChecker2 == 1) {
				g2d.drawImage(painting1, 0, 0, getWidth(), getHeight(), this);
				g2d.drawImage(painting3, 0, 0, getWidth(), getHeight(), this);
			} else if (platforms == 2 && itemChecker2 == 0) {
				g2d.drawImage(painting2, 0, 0, getWidth(), getHeight(), this);
			}
			if (platforms == 2 && itemChecker1 == 1) {
				g2d.drawImage(bear1, 0, 0, getWidth(), getHeight(), this);
				g2d.drawImage(bear3, 0, 0, getWidth(), getHeight(), this);
			} else if (platforms == 2 && itemChecker1 == 0) {
				g2d.drawImage(bear2, 0, 0, getWidth(), getHeight(), this);
			}

			g2d.drawImage(overlay1, 0, 0, getWidth(), getHeight(), this);

		} else if (removeCharacterSelector == true) {
			g2d.drawImage(option, 0, 0, 1024, 768, this);

			if (option == introImage) {
				g2d.drawImage(animation1, x1, y1, 1024, 768, this);
				g2d.drawImage(animation2, x2, y2, 1024, 768, this);
				g2d.drawImage(animation3, x3, y3, 1024, 768, this);
			}
		} else if (timeTorF == false) {
			g2d.drawImage(option2, 0, 0, 1024, 768, this);
		}

		repaint();
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public Player getPlayer4() {
		return player4;
	}

	public Level getPlatforms() {
		if (platforms == 1) {
			return platform1;
		} else if (platforms == 2) {
			return platform2;
		} else {
			return platform1;
		}
	}

}
