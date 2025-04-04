import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JComponent {

    private Player player1, player2;

    public GameCanvas() {
        setPreferredSize(new Dimension(1024, 768));
        setupPlayers();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        player1.testDrawSprite(g2d);
        player2.testDrawSprite(g2d);
    }

    private void setupPlayers() {
        player1 = new Player(100, 100, 50, Color.RED);
        player2 = new Player(200, 200, 50, Color.BLUE);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
