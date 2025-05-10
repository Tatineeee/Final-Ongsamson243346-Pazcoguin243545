import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.text.*;

public class GameFrame {

    private int width, height, playerID;
	private int character_determiner1, character_determiner2, character_determiner3, determinerChecker;
	private int p1, p2;
    private JFrame frame;
    private GameCanvas gameCanvas;
	private JLayeredPane layeredPane;
    private Timer timer, timer2;
	private JButton startButton, characterButton1, characterButton2, characterExitButton;
	private JButton startAgainButton, exitButton, tempbutton;
	private JButton tester2;
    private Socket socket;
    private Player player, playerOther;
    private ReadFromServer playerRFS;
    private WriteToServer playerWTS;
	
	private JLabel counterLabel;
	private int second, minute;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private String ddSecond;
	private Font font1 = new Font("Arial", Font.PLAIN, 50);

    public GameFrame(int width, int height) {
        this.width = width;
        this.height = height;
        frame = new JFrame("Final Project");
		gameCanvas = new GameCanvas();
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,width,height);
		
		tempbutton = new JButton();
		tempbutton.setBounds(0,0,100,100);
		
		startButton = new JButton();
		characterButton1 = new JButton();
		characterButton2 = new JButton();
		characterExitButton = new JButton();
		startButton.setBounds(310,540,400,100);
        characterButton1.setBounds(590,240,300,165);
		characterButton2.setBounds(585,480,300,165);
		characterExitButton.setBounds(585,100,300,100);
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		characterButton1.setOpaque(false);
		characterButton1.setContentAreaFilled(false);
		characterButton1.setBorderPainted(false);
		characterButton2.setOpaque(false);
		characterButton2.setContentAreaFilled(false);
		characterButton2.setBorderPainted(false);
		characterExitButton.setOpaque(true);
		characterExitButton.setContentAreaFilled(false);
		characterExitButton.setBorderPainted(true);
		determinerChecker = 0;
		
		startAgainButton = new JButton();
		exitButton = new JButton();
		startAgainButton.setBounds(25, 590, 520, 60);
		exitButton.setBounds(25, 680, 520, 60);
		startAgainButton.setOpaque(false);
		startAgainButton.setContentAreaFilled(false);
		startAgainButton.setBorderPainted(false);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		
		tester2 = new JButton();
		tester2.setBounds(100,100,100,100);
		
		character_determiner1 = 2;
		character_determiner2 = 3;
		character_determiner3 = 4;
		/* character_or_alt1 = 0;
		character_or_alt2 = 0; */
		p1 = 0; 
		p2 = 0;
		
		counterLabel = new JLabel("0:15");
		counterLabel.setBounds(900,0,200,100);
		counterLabel.setFont(font1);
		counterLabel.setForeground(Color.WHITE);
		second = 15;
		minute = 0;
		countdownTimer();
		timer2.start();
		
        connectToServer();
		determineCharacter();
        initializePlayer();
        setupGUI();
        setupTimer();
        setupKeyListener();
		setUpButtonListeners();
        playMusic("/music/scarymusic.wav", 0.5f);
    }

    private void initializePlayer() {
        if (playerID == 1) {
            player = gameCanvas.getPlayer3();
            playerOther = gameCanvas.getPlayer4();
        } else if (playerID == 2) {
            player = gameCanvas.getPlayer4();
            playerOther = gameCanvas.getPlayer2();
        } else {
            player = gameCanvas.getPlayer1();
        }
		
		gameCanvas.playerChoose(2,2);
    }
	
	public void resetPlayer(){
		
		if (playerID == 1){
			if (p1 == 1 && p2 == 1){
			    player = gameCanvas.getPlayer1();
			    playerOther = gameCanvas.getPlayer2();
				gameCanvas.playerChoose(1,1);
			} else if (p1 == 2 && p2 == 1){
				player = gameCanvas.getPlayer3();
			    playerOther = gameCanvas.getPlayer2();
				gameCanvas.playerChoose(2,1);
			} else if (p1 == 1 && p2 == 2){
				player = gameCanvas.getPlayer1();
			    playerOther = gameCanvas.getPlayer4();
				gameCanvas.playerChoose(1,2);
			}  else if (p1 == 2 && p2 == 2){
				player = gameCanvas.getPlayer3();
			    playerOther = gameCanvas.getPlayer4();
				gameCanvas.playerChoose(2,2);
			} else {
				player = gameCanvas.getPlayer1();
			    playerOther = gameCanvas.getPlayer2();
				gameCanvas.playerChoose(1,1);
			}
			
		} else if (playerID == 2){
			if (p1 == 1 && p2 == 1){
			    player = gameCanvas.getPlayer2();
			    playerOther = gameCanvas.getPlayer1();
				gameCanvas.playerChoose(1,1);
			} else if (p1 == 2 && p2 == 1){
				player = gameCanvas.getPlayer4();
			    playerOther = gameCanvas.getPlayer1();
				gameCanvas.playerChoose(1,2);
			} else if (p1 == 1 && p2 == 2){
				player = gameCanvas.getPlayer2();
			    playerOther = gameCanvas.getPlayer3();
				gameCanvas.playerChoose(2,1);
			}  else if (p1 == 2 && p2 == 2){
				player = gameCanvas.getPlayer4();
			    playerOther = gameCanvas.getPlayer3();
				gameCanvas.playerChoose(2,2);
			} else {
				player = gameCanvas.getPlayer2();
			    playerOther = gameCanvas.getPlayer1();
				gameCanvas.playerChoose(1,1);
			}
		} else {
			player = gameCanvas.getPlayer1();
		}
		
	}
	
	private void determineCharacter(){
		if (playerID == 2){
			character_determiner1 = 5;
			character_determiner2 = 6;
			character_determiner3 = 7;
		}
	}

    private void setupGUI() {
		layeredPane.add(gameCanvas);
		layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
		gameCanvas.setBounds(0,0,width,height);
		//layeredPane.setPreferredSize(new Dimension(width,height));
        frame.setContentPane(layeredPane);
        //gameCanvas.setPreferredSize(new Dimension(width, height));
        //frame.pack();
		frame.setSize(width,height);
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
	
	public void setUpButtonListeners(){
		ActionListener buttonListener = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("LFG");
				gameCanvas.switchScreen(character_determiner1);
				layeredPane.add(characterButton1, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(characterButton2, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(characterExitButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.remove(startButton);
				layeredPane.revalidate();
				layeredPane.repaint();
				p1 = 0;
				p2 = 0;
				
			}
		};
		
		ActionListener buttonListener2 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("YAY");
				gameCanvas.switchScreen(character_determiner2);
				determinerChecker = 1;
				p1 = 1;
			}
		};
		
		ActionListener buttonListener3 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("NAY");
				gameCanvas.switchScreen(character_determiner3);
				determinerChecker = 1;
				p1 = 2;
				
			}
		};
		
		ActionListener buttonListener4 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if (determinerChecker == 1 && p1 != 0 && p2 != 0){
				    System.out.println("ew");
					resetPlayer();
				    gameCanvas.switchScreen(8);
				    layeredPane.remove(characterButton1);
				    layeredPane.remove(characterButton2);
				    layeredPane.remove(characterExitButton);
				    layeredPane.add(counterLabel, JLayeredPane.PALETTE_LAYER);
				    layeredPane.add(tempbutton, JLayeredPane.PALETTE_LAYER);
					layeredPane.add(tester2, JLayeredPane.PALETTE_LAYER);
				    resetAndStart(1,0);
				} else {
					System.out.println("Choose a character.");
				}
				determinerChecker = 0;
				
			}
		};
		
		ActionListener buttonListener5 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("ew");
				gameCanvas.changeRemoveCharacterSelector();
                gameCanvas.timesUpPositive(1);
				gameCanvas.switchScreen(8);
				layeredPane.remove(startAgainButton);
				layeredPane.remove(exitButton);
				layeredPane.add(counterLabel, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(tempbutton, JLayeredPane.PALETTE_LAYER);
				resetAndStart(1,0);
				
			}
		};
		
		ActionListener buttonListener6 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("ew");
                gameCanvas.changeRemoveCharacterSelector();
				gameCanvas.switchScreen(1);
				layeredPane.remove(startAgainButton);
				layeredPane.remove(exitButton);
                layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
				
			}
		};
		
		ActionListener buttonListener7 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("ew");
				gameCanvas.timesUp(1);
                stopTime();
				if (second <= 59 && second > 45){
					gameCanvas.switchEndScreen(2);
				} else if (second <= 45 && second > 30){
					gameCanvas.switchEndScreen(3);
				} else if (second <= 30 && second > 15){
					gameCanvas.switchEndScreen(4);
				} else if (second <= 15){
					gameCanvas.switchEndScreen(5);
				}
				layeredPane.remove(counterLabel);
				layeredPane.remove(tempbutton);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
				
			}
		};
		
		ActionListener buttonListener8 = new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.out.println("ew");
				gameCanvas.switchLevel(2);
				stopTime();
				layeredPane.remove(tester2);
				resetAndStart(1,0);
				
			}
		};
		
		startButton.addActionListener(buttonListener);
		characterButton1.addActionListener(buttonListener2);
		characterButton2.addActionListener(buttonListener3);
		characterExitButton.addActionListener(buttonListener4);
		startAgainButton.addActionListener(buttonListener5);
		exitButton.addActionListener(buttonListener6);
		tempbutton.addActionListener(buttonListener7);
		tester2.addActionListener(buttonListener8);

	}
	
	public void countdownTimer(){
		timer2 = new Timer(1000, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				second--;
				ddSecond = dFormat.format(second);
				
				counterLabel.setText(minute + ":" + ddSecond);
				
				if (second == -1){
					second = 59;
					minute--;
				    ddSecond = dFormat.format(second);
				    counterLabel.setText(minute + ":" + ddSecond);
					
				}
				
				if (minute == 0 && second == 0){
					timer2.stop();
					gameCanvas.switchEndScreen(1);
					gameCanvas.timesUp(1);
					layeredPane.remove(counterLabel);
					layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
					layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
					
				}
			}
		});
	}
	
	public void resetAndStart(int min, int sec){
		if (timer2.isRunning()){
			timer2.stop();
		}
		minute = min;
		second = sec;
		ddSecond = dFormat.format(second);
		counterLabel.setText(minute + ":" + ddSecond);
		timer2.start();
	}
	
	public void stopTime(){
		timer2.stop();
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
						p2 = dataIn.readInt();
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
						dataOut.writeInt(p1);
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