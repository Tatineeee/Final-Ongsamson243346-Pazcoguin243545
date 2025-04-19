import java.awt.*;
import javax.swing.*;

public class GameCanvas extends JComponent {

    private Player player1, player2;
    private Level platforms;

    public GameCanvas() {
        player1 = new Player1(80, 758);
        player2 = new Player2(900, 758);
        platforms = new LevelThree(); // ! Change to desired level: LevelOne, LevelTwo, LevelThree
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(platforms.getBackgroundImage(), 0, 0, getWidth(), getHeight(), this);
        platforms.draw(g2d);
        player1.draw(g2d);
        player2.draw(g2d);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Level getPlatforms() {
        return platforms;
    }

}
