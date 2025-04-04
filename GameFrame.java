import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class GameFrame {

    private int width, height, playerID;
    private boolean left, right;
    private JFrame frame;
    private GameCanvas gameCanvas;
    private Timer timer;
    private Socket socket;
    private Player player;
    private Player playerOther;
    private ReadFromServer playerRFS;
    private WriteToServer playerWTS;

    public GameFrame(int w, int h) {
        frame = new JFrame("Final Project");
        gameCanvas = new GameCanvas();
        width = w;
        height = h;
        connectToServer();
        if (playerID == 1) {
            player = gameCanvas.getPlayer1();
            playerOther = gameCanvas.getPlayer2();
        } else {
            player = gameCanvas.getPlayer2();
            playerOther = gameCanvas.getPlayer1();
        }
        setupGUI();
        setupTimer();
        setupKeyListener();
    }

    private void setupGUI() {
        frame.add(gameCanvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void setupTimer() {
        int interval = 10;
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int speed = 5;
                if (left) {
                    player.moveX(-speed);
                } else if (right) {
                    player.moveX(speed);
                }
                gameCanvas.repaint();
            }
        };
        timer = new Timer(interval, actionListener);
        timer.start();
    }

    private void setupKeyListener() {
        KeyListener keyListener = new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_A:
                        left = true;
                        break;
                    case KeyEvent.VK_D:
                        right = true;
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                }
            }
        };
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 4444);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.printf("Player %d has conencted to the server.\n", playerID);
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
                        Thread.sleep(25);
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
