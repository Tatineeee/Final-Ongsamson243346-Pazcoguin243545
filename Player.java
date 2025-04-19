import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public abstract class Player {

    private double x, y, size, speedY, gravity, jumpSpeed;
    private boolean isDropping, isJumping, movingLeft, movingRight;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        size = 50;
        gravity = 1.5;
        jumpSpeed = -20;
        speedY = 0;
        isDropping = false;
        isJumping = false;
        movingLeft = false;
        movingRight = false;
    }

    public abstract void draw(Graphics2D g2d);

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

    public void moveY(double n) {
        double newY = y + n;
        if (newY >= 0 && newY + size <= 768) {
            y = newY;
        } else if (newY + size > 768) {
            y = 768 - size;
        }
    }

    public void jump() {
        if (!isJumping && !isDropping) {
            speedY = jumpSpeed;
            isJumping = true;
        }
    }

    public void update(Level platforms) {
        int speed = 5;
        if (movingLeft) {
            moveX(-speed, platforms);
        } else if (movingRight) {
            moveX(speed, platforms);
        }
        updateVertical(platforms);
    }

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

    public void handleKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            movingLeft = true;
        } else if (keyCode == KeyEvent.VK_D) {
            movingRight = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    public void handleKeyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            movingLeft = false;
        } else if (keyCode == KeyEvent.VK_D) {
            movingRight = false;
        }
    }

    public void stopJumping() {
        isJumping = false;
    }

    public void stopDropping() {
        isDropping = false;
        speedY = 0;
    }

    public void setX(double n) {
        x = n;
    }

    public void setY(double n) {
        y = n;
    }

    public void setSpeedY(double speed) {
        speedY = speed;
    }

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

    public Rectangle2D.Double getHitbox() {
        return new Rectangle2D.Double(x, y, size, size);
    }

}
