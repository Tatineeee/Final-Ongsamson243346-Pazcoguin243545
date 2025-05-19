import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * This is the Player abstract class, which handles the physics of the player.
 * It contains methods for moving, jumping, and dropping.
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

public abstract class Player {

    private double x, y, size, speedY, gravity, jumpSpeed;
    private boolean isDropping, isJumping, movingLeft, movingRight;
    private String lastDirection;

    /**
     * Initializes the player's position, size, and physics properties.
     *
     * @param x The initial x-coordinate of the player.
     * @param y The initial y-coordinate of the player.
     */
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        lastDirection = "right";
        size = 50;
        gravity = 1.5;
        jumpSpeed = -20;
        speedY = 0;
        isDropping = false;
        isJumping = false;
        movingLeft = false;
        movingRight = false;
    }

    /**
     * Handles the drawing of the player.
     *
     * @param g2d The Graphics2D object used for drawing.
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * Moves the player horizontally.
     * 
     * @param n         The distance to move the player.
     * @param platforms The level containing the platforms.
     */
    public void moveX(double n, Level platforms) {
        double newX = Math.max(0, Math.min(1024 - size, x + n));
        x = newX;
        Rectangle2D.Double bounds = getHitbox();
        for (int i = 0; i < platforms.getPlatformCount(); i++) {
            Rectangle2D.Double platform = platforms.getPlatform(i);
            if (bounds.intersects(platform)) {
                if (n > 0) {
                    x = platform.getX() - size;
                } else if (n < 0) {
                    x = platform.getX() + platform.getWidth();
                }
                break;
            }
        }
    }

    /**
     * Moves the player vertically.
     * 
     * @param n
     */
    public void moveY(double n) {
        double newY = y + n;
        if (newY >= 0 && newY + size <= 768) {
            y = newY;
        } else if (newY + size > 768) {
            y = 768 - size;
        }
    }

    /**
     * Makes the player jump if they are not already jumping or dropping.
     */
    public void jump() {
        if (!isJumping && !isDropping) {
            speedY = jumpSpeed;
            isJumping = true;
        }
    }

    /**
     * Updates the player's position based on their movement.
     * 
     * @param platforms The level containing the platforms.
     */
    public void update(Level platforms) {
        int speed = 5;
        if (movingLeft) {
            moveX(-speed, platforms);
        } else if (movingRight) {
            moveX(speed, platforms);
        }
        updateVertical(platforms);
    }

    /**
     * Updates the player's vertical position based on gravity and platform
     * collision.
     * 
     * @param platforms The level containing the platforms.
     */
    public void updateVertical(Level platforms) {
        speedY += gravity;
        y += speedY;
        Rectangle2D.Double bounds = getHitbox();
        for (int i = 0; i < platforms.getPlatformCount(); i++) {
            Rectangle2D.Double platform = platforms.getPlatform(i);
            if (bounds.intersects(platform)) {
                if (speedY > 0) {
                    y = platform.getY() - size;
                    stopDropping();
                } else if (speedY < 0) {
                    y = platform.getY() + platform.getHeight();
                    stopJumping();
                }
                speedY = 0;
                break;
            }
        }
        isDropping = speedY > 0;
        if (isDropping)
            isJumping = false;
    }

    /**
     * Handles key pressed events for player movement and jumping.
     * 
     * @param keyCode The key code of the pressed key.
     */
    public void handleKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            movingLeft = true;
        } else if (keyCode == KeyEvent.VK_D) {
            movingRight = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    /**
     * Handles key released events for player movement.
     * 
     * @param keyCode The key code of the released key.
     */
    public void handleKeyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            movingLeft = false;
        } else if (keyCode == KeyEvent.VK_D) {
            movingRight = false;
        }
    }

    /**
     * Stops the player from jumping.
     */
    public void stopJumping() {
        isJumping = false;
    }

    /**
     * Stops the player from dropping.
     */
    public void stopDropping() {
        isDropping = false;
        speedY = 0;
    }

    /**
     * Setter methods for player properties.
     */
    public void setX(double n) {
        x = n;
    }

    public void setY(double n) {
        y = n;
    }

    public void setSpeedY(double speed) {
        speedY = speed;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setLastDirection(String dir) {
        lastDirection = dir;
    }

    /**
     * @return player properties.
     */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getSize() {
        return size;
    }

    public boolean isDropping() {
        return isDropping;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public String getLastDirection() {
        return lastDirection;
    }

    /**
     * @return the hitbox of the player.
     */
    public Rectangle2D.Double getHitbox() {
        return new Rectangle2D.Double(x, y, size, size);
    }

}