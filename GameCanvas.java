import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JComponent {

    private Image backgroundImage;
    private Player player1, player2;
    private Platforms platforms;

    public GameCanvas() {
        backgroundImage = new ImageIcon(getClass().getResource("/images/background.png")).getImage();
        setupPlayers();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        platforms = new Platforms();
        platforms.draw(g2d);
        player1.draw(g2d);
        player2.draw(g2d);
    }

    private void setupPlayers() {
        player1 = new Player(80, 68, 50, Color.RED);
        player2 = new Player(900, 698, 50, Color.BLUE);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Platforms getPlatforms() {
        return platforms;
    }

}
