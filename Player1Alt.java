import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * This class extends the Player class for the alternate version of Player1.
 * It handles the animation and drawing of the player character in the game.
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

public class Player1Alt extends Player {

    private int animationFrameCount;
    private boolean toggleFrame;
    private BufferedImage standing, right1, right2, left1, left2;

    /**
     * Initializes the player position and loads images.
     * 
     * @param x The x-coordinate of the player.
     * @param y The y-coordinate of the player.
     */
    public Player1Alt(int x, int y) {
        super(x, y);
        animationFrameCount = 0;
        toggleFrame = false;
        getPlayerImages();
    }

    /**
     * Draws the player on the screen.
     * Handles animation for moving left and right.
     * 
     * @param g2d The Graphics2D object used for drawing.
     */
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
            if (getLastDirection().equals("left")) {
                currentImage = left1;
            } else if (getLastDirection().equals("right")) {
                currentImage = right1;
            } else {
                currentImage = standing;
            }
        }
        g2d.drawImage(currentImage, (int) getX(), (int) getY(), (int) getSize(), (int) getSize(), null);
    }

    /**
     * Loads player images from the folder.
     */
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