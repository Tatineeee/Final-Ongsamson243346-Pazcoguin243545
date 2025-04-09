import java.awt.*;
import java.awt.geom.*;

public class Player {

    private double x, y, size, speedY, gravity, jumpSpeed;
    private boolean isDropping, isJumping;
    private Color color;

    public Player(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        gravity = 1.4;
        speedY = 0;
        jumpSpeed = -20;
        isDropping = false;
        isJumping = false;
    }

    public void draw(Graphics2D g2d) {
        Rectangle2D.Double player = new Rectangle2D.Double(x, y, size, size);
        g2d.setColor(color);
        g2d.fill(player);
    }

    public void moveX(double n) {
        if (x + n >= 0 && x + n + size < 1024) {
            x += n;
        }
    }

    public void moveY(double n) {
        if (y + n >= 0 && y + n + size <= 768) {
            y += n;
        } else if (y + n + size > 768) {
            y -= 768 - size;
        }
    }

    public void jump() {
        if (!isJumping && !isDropping) {
            speedY = jumpSpeed;
            isJumping = true;
            isDropping = false;
        }
    }

    public void updateVertical() {
        speedY += gravity;
        moveY(speedY);
        if (speedY > 0) {
            isDropping = true;
            isJumping = false;
        }
    }

    public void stopJumping() {
        isJumping = false;
    }

    public void stopDropping() {
        isDropping = false;
        isJumping = false;
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

    public Rectangle2D.Double getHitbox() {
        return new Rectangle2D.Double(x, y, size, size);
    }

}
