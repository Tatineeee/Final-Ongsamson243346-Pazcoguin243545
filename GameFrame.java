import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class GameFrame {

    private int width, height, playerID;
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
        } else if (playerID == 2) {
            player = gameCanvas.getPlayer2();
            playerOther = gameCanvas.getPlayer1();
        } else {
            player = gameCanvas.getPlayer1();
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
                player.update(gameCanvas.getPlatforms());
                checkPlatform();
                gameCanvas.repaint();
            }
        };
        timer = new Timer(interval, actionListener);
        timer.start();
    }

    private void setupKeyListener() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.handleKeyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.handleKeyReleased(e.getKeyCode());
            }
        };
        frame.addKeyListener(keyAdapter);
        frame.setFocusable(true);
    }

    private void checkPlatform() {
        Level platforms = gameCanvas.getPlatforms();
        for (int i = 0; i < platforms.getPlatformCount(); i++) {
            Rectangle2D.Double platform = platforms.getPlatform(i);
            handlePlatformCollision(platform);
        }
    }

    private void handlePlatformCollision(Rectangle2D.Double platform) {
        double size = player.getSize();
        double playerLeft = player.getX();
        double playerTop = player.getY();
        double playerBottom = playerTop + size;
        double playerRight = playerLeft + size;
        double platformLeft = platform.getX();
        double platformTop = platform.getY();
        double platformRight = platformLeft + platform.getWidth();
        double platformBottom = platformTop + platform.getHeight();
        boolean horizontallyAligned = playerRight > platformLeft && playerLeft < platformRight;
        boolean verticallyAligned = playerBottom > platformTop && playerTop < platformBottom;
        if (horizontallyAligned && verticallyAligned) {
            double speedY = player.getSpeedY();
            if (player.isDropping() && (playerBottom - speedY <= platformTop)) {
                player.setY(platformTop - size);
                player.stopDropping();
                player.setSpeedY(0);
            } else if (player.isJumping() && (playerTop - speedY >= platformBottom)) {
                player.setY(platformBottom);
                player.stopJumping();
                player.setSpeedY(0);
            }
        }
    }

    private void playMusic(String filePath, float volume) {
        try {
            URL musicUrl = getClass().getResource(filePath);
            if (musicUrl == null) {
                throw new IOException("Music file not found: " + filePath);
            }
            try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = 20f * (float) Math.log10(Math.max(volume, 0.0001f)); // prevent log(0)
                gainControl.setValue(dB);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing music: " + e.getMessage());
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