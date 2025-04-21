import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player1Alt extends Player {

    private int animationFrameCount;
    private boolean toggleFrame;
    private BufferedImage standing, right1, right2, left1, left2;

    public Player1Alt(int x, int y) {
        super(x, y);
        animationFrameCount = 0;
        toggleFrame = false;
        getPlayerImages();
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage currentImage = standing;
        if (isMovingLeft() || isMovingRight()) {
            animationFrameCount++;
            if (animationFrameCount >= 10) {
                toggleFrame = !toggleFrame;
                animationFrameCount = 0;
            }
            if (isMovingLeft()) {
                currentImage = toggleFrame ? left1 : left2;
            } else if (isMovingRight()) {
                currentImage = toggleFrame ? right1 : right2;
            }
        } else {
            animationFrameCount = 0;
            toggleFrame = false;
        }
        g2d.drawImage(currentImage, (int) getX(), (int) getY(), (int) getSize(), (int) getSize(), null);
    }

    private void getPlayerImages() {
        try {
            standing = ImageIO.read(getClass().getResourceAsStream("/player-assets/boy-alt-standing.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player-assets/boy-alt-right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player-assets/boy-alt-right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player-assets/boy-alt-left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player-assets/boy-alt-left2.png"));
        } catch (Exception e) {
            System.out.println("Error loading player images: " + e.getMessage());
        }
    }

}
