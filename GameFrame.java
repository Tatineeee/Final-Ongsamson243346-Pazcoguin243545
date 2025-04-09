import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class GameFrame {

    private int width, height, playerID;
    private boolean left, right;
    private JFrame frame;
    private GameCanvas gameCanvas;
    private Timer timer;
    private Socket socket;
    private Player player, playerOther;
    private ReadFromServer playerRFS;
    private WriteToServer playerWTS;

    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
        frame = new JFrame("Final Project");
        gameCanvas = new GameCanvas();
        connectToServer();
        initializePlayer();
        setupGUI();
        setupTimer();
        setupKeyListener();
        playMusic("/music/scarymusic.wav", 0.5f);
    }

    private void initializePlayer() {
        if (playerID == 1) {
            player = gameCanvas.getPlayer1();
            playerOther = gameCanvas.getPlayer2();
        } else {
            player = gameCanvas.getPlayer2();
            playerOther = gameCanvas.getPlayer1();
        }
    }

    private void setupGUI() {
        frame.add(gameCanvas);
        gameCanvas.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        gameCanvas.repaint();
    }

    private void setupTimer() {
        int interval = 10;
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int speed = 8;
                if (left) {
                    player.moveX(-speed);
                } else if (right) {
                    player.moveX(speed);
                }
                player.updateVertical();
                checkPlatform();
                gameCanvas.repaint();
            }
        };
        timer = new Timer(interval, actionListener);
        timer.start();
    }

    private void checkPlatform() {
        Platforms platforms = gameCanvas.getPlatforms();
        for (int i = 0; i < platforms.getPlatformCount(); i++) {
            double platformX = platforms.getPlatformX(i);
            double platformY = platforms.getPlatformY(i);
            double platformWidth = platforms.getPlatformWidth(i);
            double platformHeight = platforms.getPlatformHeight(i);
            Rectangle2D.Double platformBounds = new Rectangle2D.Double(
                    platformX, platformY, platformWidth, platformHeight);
            if (player.getHitbox().intersects(platformBounds)) {
                handlePlatformCollision(platformY, platformHeight);
            }
        }
    }

    private void handlePlatformCollision(double platformY, double platformHeight) {
        double playerBottom = player.getY() + player.getSize();
        double playerTop = player.getY();
        double platformTop = platformY;
        double platformBottom = platformY + platformHeight;
        if (playerBottom > platformTop && playerTop < platformBottom) {
            if (player.isDropping()) {
                player.setY(platformTop - player.getSize());
                player.stopDropping();
                player.setSpeedY(0);
            } else if (player.isJumping()) {
                player.setY(platformBottom);
                player.stopJumping();
                player.setSpeedY(0);
            }
        }
    }

    private void setupKeyListener() {
        KeyListener keyListener = new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                handleKeyPressed(e.getKeyCode());
            }

            public void keyReleased(KeyEvent e) {
                handleKeyReleased(e.getKeyCode());
            }
        };
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
    }

    private void handleKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_SPACE:
                player.jump();
                break;
        }
    }

    private void handleKeyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
        }
    }

    private void playMusic(String filePath, float volume) {
        try {
            URL musicUrl = getClass().getResource(filePath);
            if (musicUrl == null) {
                throw new IOException("Music file not found: " + filePath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = 20f * (float) Math.log10(volume);
            gainControl.setValue(dB);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 4444);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.printf("Player %d has connected to the server.\n", playerID);
            playerRFS = new ReadFromServer(in);
            playerWTS = new WriteToServer(out);
            playerRFS.waitForPlayer();
        } catch (IOException e) {
            System.out.println("connectToServer error: " + e.getMessage());
        }
    }

    private class ReadFromServer implements Runnable {
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in) {
            dataIn = in;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (player != null) {
                        playerOther.setX(dataIn.readDouble());
                        playerOther.setY(dataIn.readDouble());
                    }
                }
            } catch (IOException e) {
                System.out.println("ReadFromServer error: " + e.getMessage());
            }
        }

        public void waitForPlayer() {
            try {
                String startMessage = dataIn.readUTF();
                System.out.printf("Message from server: %s\n", startMessage);
                Thread readThread = new Thread(playerRFS);
                Thread writeThread = new Thread(playerWTS);
                readThread.start();
                writeThread.start();
            } catch (IOException e) {
                System.out.println("waitForPlayer error: " + e.getMessage());
            }
        }
    }

    private class WriteToServer implements Runnable {
        private DataOutputStream dataOut;

        public WriteToServer(DataOutputStream out) {
            dataOut = out;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (player != null) {
                        dataOut.writeDouble(player.getX());
                        dataOut.writeDouble(player.getY());
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("WriteToServer Thread sleep error: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("WriteToServer error: " + e.getMessage());
            }
        }
    }

}
