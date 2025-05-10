import java.awt.*;
import javax.swing.*;

public class GameCanvas extends JComponent {

    private Player player1, player2, player3, player4, playerChoose1, playerChoose2;
	private int platforms;
    private Level platform1, platform2, platform3;
    private MainScreen intro;
	private Overlay overlayMaster;
	private Image introImage, characterImage, characterImage1, characterImage2, characterImage3, characterImage4, characterImage5, option;
	private Image deadImage, option2;
	private Image overlay1;
	private Image finishImageA, finishImageB, finishImageC, finishImageF;
	private CharacterSelector character;
	private EndScreenSelector end;
	private boolean removeCharacterSelector, timeTorF;

    public GameCanvas() {
        player1 = new Player1Alt(80, 758);
        player2 = new Player2Alt(900, 758);
		player3 = new Player1(80, 758);
		player4 = new Player2(900, 758);
		playerChoose1 = player1;
		playerChoose2 = player2;
		platform1 = new LevelOne();
		platform2 = new LevelTwo();
		platform3 = new LevelThree();
        platforms = 1; // ! Change to desired level: LevelOne, LevelTwo, LevelThree
		
		intro = new MainScreen();
		character = new CharacterSelector();
		overlayMaster = new Overlay();
		
		introImage = intro.getBackgroundImage();
		characterImage = character.getBackgroundImage();
		characterImage1 = character.getBackgroundImage1();
		characterImage2 = character.getBackgroundImage2();
		characterImage3 = character.getBackgroundImage3();
		characterImage4 = character.getBackgroundImage4();
		characterImage5 = character.getBackgroundImage5();
		option = introImage;
		overlay1 = overlayMaster.getOverlayLVL1();
		
		
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
	
	public void switchScreen(int checker){
		if(checker == 1){
			option = introImage;
		} else if (checker == 2){
			option = characterImage;
		} else if (checker == 3){
			option = characterImage1;
		} else if (checker == 4){
			option = characterImage2;
		} else if (checker == 5){
			option = characterImage3;
		} else if (checker == 6){
			option = characterImage4;
		} else if (checker == 7){
			option = characterImage5;
		} else if (checker == 8){
			timeTorF = true;
			removeCharacterSelector = false;
			System.out.println("hi");
		} 
		repaint();
	}
	
	public void switchLevel(int checker){
		if(checker == 1){
			platforms = 1;
		} else if (checker == 2){
			platforms = 2;
		} else if (checker == 3){
			platforms = 3;
		} 
		
		repaint();
	}
	
	public void switchEndScreen(int checker){
		if(checker == 1){
			option2 = deadImage;
		} else if (checker == 2){
			option2 = finishImageA;
		} else if (checker == 3){
			option2 = finishImageB;
		} else if (checker == 4){
			option2 = finishImageC;
		} else if (checker == 5){
			option2 = finishImageF;
		}
		repaint();
	}
	
	public void playerChoose(int p1, int p2){
		if (p1 == 1){
			playerChoose1 = player1;
		} else if (p1 == 2){
			playerChoose1 = player3;
		}
		
		if (p2 == 1){
			playerChoose2 = player2;
		} else if (p2 == 2){
			playerChoose2 = player4;
		}
		
		repaint();
	}
	
	public void changeRemoveCharacterSelector(){
		removeCharacterSelector = true;
	}
	
	public void timesUp(int timeChecker){
		if (timeChecker == 1){
			timeTorF = false;
		}
	}
	
	public void timesUpPositive(int timeChecker){
		if (timeChecker == 1){
			timeTorF = true;
		}
	}

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		if (removeCharacterSelector == false && timeTorF == true){
			if (platforms == 1){
                g2d.drawImage(platform1.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
                platform1.draw(g2d);
			} else if (platforms == 2){
				g2d.drawImage(platform2.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
                platform2.draw(g2d);
			} else if (platforms == 3){
				g2d.drawImage(platform3.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
                platform3.draw(g2d);
			}
            playerChoose1.draw(g2d);
            playerChoose2.draw(g2d); 
			g2d.drawImage(overlay1, 0, 0, getWidth(), getHeight(), this);
			
		} else if (removeCharacterSelector == true){
		    g2d.drawImage(option, 0, 0, 1024, 768, this);
		} else if (timeTorF == false){
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
		if (platforms == 1){
			return platform1;
		} else if (platforms == 2){
			return platform2;
		} else if (platforms == 3){
			return platform3;
		} else {
			return platform1;
		}
    }

}
