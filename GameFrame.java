import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.text.*;

/**
 * lorem
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

public class GameFrame {

	private int width, height, playerID;
	private int character_determiner1, character_determiner2, character_determiner3, determinerChecker;
	private int p1, p2, lvlCounter, lvlp1, lvlp2, obtainItem, itemObtained1, itemObtained2, itemObtained3,
			itemObtained4;
	private int p12, p22, not2, not22;
	private int check1, check2, liftChecker1, liftChecker2, liftChecker3, liftChecker4, liftChecker5;
	private int spikeChecker1, spikeChecker2, testlol, doorCheck1, doorCheck2, notDoor;
	private JFrame frame;
	private GameCanvas gameCanvas;
	private JLayeredPane layeredPane;
	private Timer timer, timer2;
	private JButton startButton, characterButton1, characterButton2, characterExitButton;
	private JButton startAgainButton, exitButton, tempbutton;
	private JButton test, test2;
	private Socket socket;
	private Player player, playerOther;
	private ReadFromServer playerRFS;
	private WriteToServer playerWTS;

	private boolean liftCheck;

	private JLabel counterLabel;
	private int second, minute, second2;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private String ddSecond;
	private Font font1 = new Font("Arial", Font.PLAIN, 50);

	/**
	 * Constructs the GUI of the game and initializes the various values.
	 * 
	 * @param width  width of the screen.
	 * @param height height of the screen.
	 */
	public GameFrame(int width, int height) {
		this.width = width;
		this.height = height;
		frame = new JFrame("Final Project");
		gameCanvas = new GameCanvas();
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, width, height);

		tempbutton = new JButton();
		tempbutton.setBounds(0, 0, 100, 100);

		startButton = new JButton();
		characterButton1 = new JButton();
		characterButton2 = new JButton();
		characterExitButton = new JButton();
		startButton.setBounds(310, 540, 400, 100);
		characterButton1.setBounds(590, 240, 300, 165);
		characterButton2.setBounds(585, 480, 300, 165);
		characterExitButton.setBounds(585, 100, 300, 100);
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

		character_determiner1 = 2;
		character_determiner2 = 3;
		character_determiner3 = 4;
		p1 = 0;
		p2 = 0;
		p12 = 0;
		p22 = 0;
		not2 = 0;
		not22 = 0;
		doorCheck1 = 0;
		doorCheck2 = 0;

		lvlCounter = 0;
		lvlp1 = 0;
		lvlp2 = 0;
		itemObtained1 = 0;
		itemObtained2 = 0;
		itemObtained3 = 0;
		itemObtained4 = 0;
		check1 = 0;
		check2 = 0;
		liftChecker1 = 0;
		liftChecker2 = 0;
		liftChecker3 = 1;
		liftChecker4 = 0;
		liftChecker5 = 0;
		liftCheck = false;

		test = new JButton();
		test.setBounds(780, 390, 100, 100);
		test.setOpaque(false);
		test.setContentAreaFilled(false);
		test.setBorderPainted(true);

		test2 = new JButton();
		test2.setBounds(100, 100, 100, 100);
		test2.setOpaque(false);
		test2.setContentAreaFilled(true);
		test2.setBorderPainted(true);

		spikeChecker1 = 0;
		spikeChecker2 = 0;

		counterLabel = new JLabel("2:0");
		counterLabel.setBounds(900, 0, 200, 100);
		counterLabel.setFont(font1);
		counterLabel.setForeground(Color.WHITE);
		second = 0;
		minute = 2;

		connectToServer();
		determineCharacter();
		initializePlayer();
		setupGUI();
		setupKeyListener();
		setUpButtonListeners();
		playMusic("/music/scarymusic.wav", 0.5f);

		// areaChecker();
		// timer3.start();
	}

	/**
	 * Initiazlies the players based on playerID and which variation was chosen.
	 */
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

		gameCanvas.playerChoose(2, 2);
	}

	/**
	 * Resets the player.
	 */
	public void resetPlayer() {

		if (playerID == 1) {
			if (p1 == 1 && p22 == 1) {
				player = gameCanvas.getPlayer1();
				playerOther = gameCanvas.getPlayer2();
				gameCanvas.playerChoose(1, 1);
			} else if (p1 == 2 && p22 == 1) {
				player = gameCanvas.getPlayer3();
				playerOther = gameCanvas.getPlayer2();
				gameCanvas.playerChoose(2, 1);
			} else if (p1 == 1 && p22 == 2) {
				player = gameCanvas.getPlayer1();
				playerOther = gameCanvas.getPlayer4();
				gameCanvas.playerChoose(1, 2);
			} else if (p1 == 2 && p22 == 2) {
				player = gameCanvas.getPlayer3();
				playerOther = gameCanvas.getPlayer4();
				gameCanvas.playerChoose(2, 2);
			} else {
				player = gameCanvas.getPlayer1();
				playerOther = gameCanvas.getPlayer2();
				gameCanvas.playerChoose(1, 1);
			}

		} else if (playerID == 2) {
			if (p2 == 1 && p12 == 1) {
				player = gameCanvas.getPlayer2();
				playerOther = gameCanvas.getPlayer1();
				gameCanvas.playerChoose(1, 1);
			} else if (p2 == 2 && p12 == 1) {
				player = gameCanvas.getPlayer2();
				playerOther = gameCanvas.getPlayer3();
				gameCanvas.playerChoose(2, 1);
			} else if (p2 == 1 && p12 == 2) {
				player = gameCanvas.getPlayer4();
				playerOther = gameCanvas.getPlayer1();
				gameCanvas.playerChoose(1, 2);
			} else if (p2 == 2 && p12 == 2) {
				player = gameCanvas.getPlayer4();
				playerOther = gameCanvas.getPlayer3();
				gameCanvas.playerChoose(2, 2);
			} else {
				player = gameCanvas.getPlayer2();
				playerOther = gameCanvas.getPlayer1();
				gameCanvas.playerChoose(1, 1);
			}
		} else {
			player = gameCanvas.getPlayer1();
		}

	}

	/**
	 * Determines the player based on the playerID
	 */
	private void determineCharacter() {
		if (playerID == 2) {
			character_determiner1 = 5;
			character_determiner2 = 6;
			character_determiner3 = 7;
		}
	}

	/**
	 * Sets up the GUI for the game.
	 */
	private void setupGUI() {
		layeredPane.add(gameCanvas);
		layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
		gameCanvas.setBounds(0, 0, width, height);
		// layeredPane.setPreferredSize(new Dimension(width,height));
		frame.setContentPane(layeredPane);
		// gameCanvas.setPreferredSize(new Dimension(width, height));
		// frame.pack();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		gameCanvas.repaint();
	}

	/**
	 * Set up the timer for the game.
	 */
	private void setupTimer() {
		int interval = 10;
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				player.update(gameCanvas.getPlatforms());

				if (lvlCounter == 1) {
					checkPlatform();
					gameCanvas.repaint();
					itemChecker1();
					if ((itemObtained1 == 1 && itemObtained4 == 1) || doorCheck2 == 1) {
						levelChecker();
					} else if ((itemObtained2 == 1 && itemObtained3 == 1) || doorCheck2 == 1) {
						levelChecker();
					}
					checkSpikes();

				} else if (lvlCounter == 3) {
					checkPlatform();
					gameCanvas.repaint();
					if (check1 != 0) {
						resetItem();
					}
					itemChecker3();
					if ((itemObtained1 == 1 && itemObtained4 == 1) || doorCheck2 == 2) {
						levelChecker();
					} else if ((itemObtained2 == 1 && itemObtained3 == 1) || doorCheck2 == 2) {
						levelChecker();
					}
					// itemChecker2();
					checkSpikes();

				}

			}
		};
		timer = new Timer(interval, actionListener);
		timer.start();
	}

	public void mainTimerStop() {
		timer.stop();
	}

	/**
	 * Sets up the various key listeners in the game.
	 */
	private void setupKeyListener() {
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				player.handleKeyPressed(e.getKeyCode());

				if (e.getKeyCode() == KeyEvent.VK_S) {
					doorCheck1 = 1;
				}

				if (e.getKeyCode() == KeyEvent.VK_W) {
					doorCheck1 = 2;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player.handleKeyReleased(e.getKeyCode());
			}
		};
		frame.addKeyListener(keyAdapter);
		frame.setFocusable(true);
	}

	/**
	 * Set up the various button listeners.
	 */
	public void setUpButtonListeners() {
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
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
				p12 = 0;
				p22 = 0;

			}
		};

		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("YAY");
				gameCanvas.switchScreen(character_determiner2);
				determinerChecker = 1;
				if (playerID == 1)
					p1 = 1;
				else if (playerID == 2)
					p12 = 1;
			}
		};

		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("NAY");
				gameCanvas.switchScreen(character_determiner3);
				determinerChecker = 1;
				if (playerID == 1)
					p1 = 2;
				else if (playerID == 2)
					p12 = 2;

			}
		};

		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (determinerChecker == 1 && ((p1 != 0 && p22 != 0) || (p2 != 0 && p12 != 0))) {
					System.out.println("ew");
					resetItem();
					resetAndStart();
					setupTimer();
					resetPlayer();
					countdownTimer();
					gameCanvas.switchScreen(8);
					gameCanvas.switchLevel(1);
					gameCanvas.fixItemChecker1(1);
					gameCanvas.fixItemChecker2(1);
					layeredPane.remove(characterButton1);
					layeredPane.remove(characterButton2);
					layeredPane.remove(characterExitButton);
					layeredPane.add(counterLabel, JLayeredPane.PALETTE_LAYER);
					layeredPane.add(tempbutton, JLayeredPane.PALETTE_LAYER);
					// resetAndStart(1,0);
					lvlCounter = 1;
				} else {
					System.out.println("Choose a character.");
				}
				determinerChecker = 0;

			}
		};

		ActionListener buttonListener5 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gameCanvas.changeRemoveCharacterSelector();
				gameCanvas.timesUpPositive(1);
				gameCanvas.switchLevel(1);
				resetAndStart();
				setupTimer();
				resetPlayer();
				countdownTimer();
				gameCanvas.switchScreen(8);
				gameCanvas.fixItemChecker1(1);
				gameCanvas.fixItemChecker2(1);
				resetItem();
				layeredPane.remove(startAgainButton);
				layeredPane.remove(exitButton);
				layeredPane.add(counterLabel, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(tempbutton, JLayeredPane.PALETTE_LAYER);
				// resetAndStart(1,0);
				spikeChecker1 = 0;

			}
		};

		ActionListener buttonListener6 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gameCanvas.changeRemoveCharacterSelector();
				gameCanvas.switchScreen(1);
				layeredPane.remove(startAgainButton);
				layeredPane.remove(exitButton);
				layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
				spikeChecker1 = 0;

			}
		};

		ActionListener buttonListener7 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gameCanvas.timesUp(1);
				stopTime();
				if (second <= 59 && second > 45) {
					gameCanvas.switchEndScreen(2);
				} else if (second <= 45 && second > 30) {
					gameCanvas.switchEndScreen(3);
				} else if (second <= 30 && second > 15) {
					gameCanvas.switchEndScreen(4);
				} else if (second <= 15) {
					gameCanvas.switchEndScreen(5);
				}
				layeredPane.remove(counterLabel);
				layeredPane.remove(tempbutton);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);

			}
		};

		ActionListener buttonListener8 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gameCanvas.switchLevel(2);
				lvlCounter = 2;
				gameCanvas.fixItemChecker1(1);
				gameCanvas.fixItemChecker2(1);
				liftPlatform2();
				stopTime();
				// resetAndStart(1,0);
				layeredPane.add(test, JLayeredPane.PALETTE_LAYER);
				check1 = 1;

			}
		};

		startButton.addActionListener(buttonListener);
		characterButton1.addActionListener(buttonListener2);
		characterButton2.addActionListener(buttonListener3);
		characterExitButton.addActionListener(buttonListener4);
		startAgainButton.addActionListener(buttonListener5);
		exitButton.addActionListener(buttonListener6);
		tempbutton.addActionListener(buttonListener7);
		test2.addActionListener(buttonListener8);

	}

	/**
	 * Sets up the timer for the game.
	 */
	public void countdownTimer() {
		timer2 = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				if (second2 > second) {
					ddSecond = dFormat.format(second2);
				} else if (second > second2) {
					ddSecond = dFormat.format(second);
				}

				counterLabel.setText(minute + ":" + ddSecond);

				if (second == -1) {
					second = 59;
					System.out.println(minute);
					minute--;
					if (second2 > second) {
						ddSecond = dFormat.format(second2);
					} else if (second > second2) {
						ddSecond = dFormat.format(second);
					}
					counterLabel.setText(minute + ":" + ddSecond);

				}

				if (minute == 0 && second == 0) {
					timer2.stop();
					gameCanvas.switchEndScreen(1);
					gameCanvas.timesUp(1);
					layeredPane.remove(counterLabel);
					layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
					layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);

				}
			}
		});

		timer2.start();
	}

	/**
	 * Start and reset the timer.
	 */
	public void resetAndStart() {
		minute = 2;
		second = 0;
	}

	/**
	 * Stops the timer.
	 */
	public void stopTime() {
		timer2.stop();
	}

	/**
	 * Checks the current level.
	 */
	public void levelChecker() {
		if (lvlCounter == 1) {
			if (player.getX() > 800 && player.getX() < 870 && player.getY() < 190) {
				lvlp1 = 1;
			} else if (player.getX() > 880 && player.getX() < 950 && player.getY() < 190) {
				lvlp1 = 2;
			}

			if (((lvlp1 == 1 && lvlp2 == 2) && doorCheck1 == 1) || doorCheck2 == 1) {
				check1 = 1;
				resetItem();
				lvlCounter = 0;
				System.out.println("ew");
				gameCanvas.switchLevel(3);
				gameCanvas.fixItemChecker1(1);
				gameCanvas.fixItemChecker2(1);
				// stopTime();
				// resetAndStart(1,0);
				layeredPane.add(test, JLayeredPane.PALETTE_LAYER);
				lvlCounter = 3;
			} else if (((lvlp1 == 2 && lvlp2 == 1) && doorCheck1 == 1) || doorCheck2 == 1) {
				check1 = 1;
				resetItem();
				lvlCounter = 0;
				System.out.println("ew");
				gameCanvas.switchLevel(3);
				gameCanvas.fixItemChecker1(1);
				gameCanvas.fixItemChecker2(1);
				// stopTime();
				// resetAndStart(1,0);
				layeredPane.add(test, JLayeredPane.PALETTE_LAYER);
				lvlCounter = 3;
			}
		} else if (lvlCounter == 3) { // 500,20,70, 780,390,100,100
			if (player.getX() > 500 && player.getX() < 570 && player.getY() < 100 && player.getY() > 20) {
				lvlp1 = 1;
				System.out.println("ew");
			} else if (player.getX() > 780 && player.getX() < 880 && player.getY() < 490 && player.getY() > 390) {
				lvlp1 = 2;
				System.out.println("LMAO");
			}

			if (((lvlp1 == 1 && lvlp2 == 2) && doorCheck1 == 2) || doorCheck2 == 2) {
				System.out.println("ew");
				gameCanvas.timesUp(1);
				stopTime();
				if (second <= 59 && second > 45) {
					gameCanvas.switchEndScreen(2);
				} else if (second <= 45 && second > 30) {
					gameCanvas.switchEndScreen(3);
				} else if (second <= 30 && second > 15) {
					gameCanvas.switchEndScreen(4);
				} else if (second <= 15) {
					gameCanvas.switchEndScreen(5);
				}
				layeredPane.remove(counterLabel);
				layeredPane.remove(tempbutton);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
			} else if (((lvlp1 == 2 && lvlp2 == 1) && doorCheck1 == 2) || doorCheck2 == 2) {
				System.out.println("ew");
				gameCanvas.timesUp(1);
				stopTime();
				if (second <= 59 && second > 45) {
					gameCanvas.switchEndScreen(2);
				} else if (second <= 45 && second > 30) {
					gameCanvas.switchEndScreen(3);
				} else if (second <= 30 && second > 15) {
					gameCanvas.switchEndScreen(4);
				} else if (second <= 15) {
					gameCanvas.switchEndScreen(5);
				}
				stopTime();
				mainTimerStop();
				layeredPane.remove(counterLabel);
				layeredPane.remove(tempbutton);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
			}
		}

		/*
		 * else if (lvlCounter == 2){
		 * if (player.getX() > 800 && player.getX() < 870 && player.getY() < 330 &&
		 * player.getY() > 250){
		 * lvlp1 = 1;
		 * System.out.println("ew");
		 * } else if (player.getX() > 880 && player.getX() < 950 && player.getY() < 330
		 * && player.getY() > 250){
		 * lvlp1 = 2;
		 * System.out.println("LMAO");
		 * }
		 * 
		 * if (lvlp1 == 1 && lvlp2 == 2){
		 * System.out.println("ew");
		 * gameCanvas.switchLevel(3);
		 * lvlCounter = 3;
		 * gameCanvas.fixItemChecker1(1);
		 * gameCanvas.fixItemChecker2(1);
		 * //stopTime();
		 * //resetAndStart(1,0);
		 * layeredPane.add(test, JLayeredPane.PALETTE_LAYER);
		 * check1 = 1;
		 * //resetItem();
		 * } else if (lvlp1 == 2 && lvlp2 == 1){
		 * System.out.println("ew");
		 * gameCanvas.switchLevel(3);
		 * lvlCounter = 3;
		 * gameCanvas.fixItemChecker1(1);
		 * gameCanvas.fixItemChecker2(1);
		 * //stopTime();
		 * //resetAndStart(1,0);
		 * layeredPane.add(test, JLayeredPane.PALETTE_LAYER);
		 * check1 = 1;
		 * //resetItem();
		 * }
		 * } /*else if (lvlCounter == 3){ //500,20,70, 780,390,100,100
		 * if (player.getX() > 500 && player.getX() < 570 && player.getY() < 100 &&
		 * player.getY() > 20){
		 * lvlp1 = 1;
		 * System.out.println("ew");
		 * } else if (player.getX() > 780 && player.getX() < 880 && player.getY() < 490
		 * && player.getY() > 390){
		 * lvlp1 = 2;
		 * System.out.println("LMAO");
		 * }
		 * 
		 * if (lvlp1 == 1 && lvlp2 == 2){
		 * System.out.println("ew");
		 * gameCanvas.timesUp(1);
		 * stopTime();
		 * if (second <= 59 && second > 45){
		 * gameCanvas.switchEndScreen(2);
		 * } else if (second <= 45 && second > 30){
		 * gameCanvas.switchEndScreen(3);
		 * } else if (second <= 30 && second > 15){
		 * gameCanvas.switchEndScreen(4);
		 * } else if (second <= 15){
		 * gameCanvas.switchEndScreen(5);
		 * }
		 * layeredPane.remove(counterLabel);
		 * layeredPane.remove(tempbutton);
		 * layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
		 * layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
		 * } else if (lvlp1 == 2 && lvlp2 == 1){
		 * System.out.println("ew");
		 * gameCanvas.timesUp(1);
		 * stopTime();
		 * if (second <= 59 && second > 45){
		 * gameCanvas.switchEndScreen(2);
		 * } else if (second <= 45 && second > 30){
		 * gameCanvas.switchEndScreen(3);
		 * } else if (second <= 30 && second > 15){
		 * gameCanvas.switchEndScreen(4);
		 * } else if (second <= 15){
		 * gameCanvas.switchEndScreen(5);
		 * }
		 * layeredPane.remove(counterLabel);
		 * layeredPane.remove(tempbutton);
		 * layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
		 * layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
		 * }
		 * 
		 * }
		 */
	}

	/*
	 * public void itemChecker() {
	 * if (lvlCounter == 1) {
	 * if (player.getX() > 50 && player.getX() < 150 && player.getY() < 175 &&
	 * obtainItem == 0) {
	 * itemObtained1 = 1;
	 * gameCanvas.fixItemChecker1(0);
	 * obtainItem = 1;
	 * } else if (player.getX() > 900 && player.getX() < 1000 && player.getY() < 600
	 * && player.getY() > 500
	 * && obtainItem == 0) {
	 * itemObtained3 = 1;
	 * gameCanvas.fixItemChecker2(0);
	 * obtainItem = 1;
	 * }
	 * 
	 * if (itemObtained2 == 1) {
	 * gameCanvas.fixItemChecker1(0);
	 * } else if (itemObtained4 == 1) {
	 * gameCanvas.fixItemChecker2(0);
	 * }
	 * 
	 * } else if (lvlCounter == 2) { // 500,25,100,100 900,250,100,100
	 * if (player.getX() > 500 && player.getX() < 600 && player.getY() < 125 &&
	 * player.getY() > 25
	 * && obtainItem == 0) {
	 * itemObtained1 = 1;
	 * gameCanvas.fixItemChecker1(0);
	 * obtainItem = 1;
	 * } else if (player.getX() > 900 && player.getX() < 1000 && player.getY() < 350
	 * && player.getY() > 250
	 * && obtainItem == 0) {
	 * itemObtained3 = 1;
	 * gameCanvas.fixItemChecker2(0);
	 * obtainItem = 1;
	 * }
	 * 
	 * if (itemObtained2 == 1) {
	 * gameCanvas.fixItemChecker1(0);
	 * } else if (itemObtained4 == 1) {
	 * gameCanvas.fixItemChecker2(0);
	 * }
	 * } else if (lvlCounter == 3) { // 860,250,100,100
	 * if (player.getX() > 860 && player.getX() < 960 && player.getY() < 350 &&
	 * player.getY() > 250
	 * && obtainItem == 0) {
	 * itemObtained1 = 1;
	 * gameCanvas.fixItemChecker1(0);
	 * obtainItem = 1;
	 * } else if (player.getX() > 0 && player.getX() < 1 && player.getY() < 1 &&
	 * player.getY() > 0
	 * && obtainItem == 0) {
	 * itemObtained3 = 1;
	 * gameCanvas.fixItemChecker2(0);
	 * obtainItem = 1;
	 * }
	 * 
	 * if (itemObtained2 == 1) {
	 * gameCanvas.fixItemChecker1(0);
	 * } else if (itemObtained4 == 1) {
	 * gameCanvas.fixItemChecker2(0);
	 * }
	 * }
	 * }
	 */

	public void itemChecker1() {
		if (lvlCounter == 1) {
			if (player.getX() > 50 && player.getX() < 150 && player.getY() < 175 && obtainItem == 0) {
				itemObtained1 = 1;
				gameCanvas.fixItemChecker1(0);
				obtainItem = 1;
			} else if (player.getX() > 900 && player.getX() < 1000 && player.getY() < 600 && player.getY() > 500
					&& obtainItem == 0) {
				itemObtained3 = 1;
				gameCanvas.fixItemChecker2(0);
				obtainItem = 1;
			}

			if (itemObtained2 == 1) {
				gameCanvas.fixItemChecker1(0);
			} else if (itemObtained4 == 1) {
				gameCanvas.fixItemChecker2(0);
			}

		}
	}

	public void itemChecker3() {
		/*
		 * if ((lvlp1 != 0 && lvlp2 !=0) && testlol == 0){
		 * resetItem();
		 * testlol = 1;
		 * }
		 */
		if (lvlCounter == 3) {
			if (player.getX() > 890 && player.getX() < 990 && player.getY() < 360 && player.getY() > 260
					&& obtainItem == 0) {
				itemObtained1 = 1;
				gameCanvas.fixItemChecker1(0);
				obtainItem = 1;
			} else if (player.getX() > 765 && player.getX() < 865 && player.getY() < 515 && player.getY() > 415
					&& obtainItem == 0) {
				itemObtained3 = 1;
				gameCanvas.fixItemChecker2(0);
				obtainItem = 1;
			}

			if (itemObtained2 == 1) {
				gameCanvas.fixItemChecker1(0);
			} else if (itemObtained4 == 1) {
				gameCanvas.fixItemChecker2(0);
			}

		}
	}

	public void resetItem() {
		if (check1 == 1) {
			obtainItem = 0;
			itemObtained1 = 0;
			itemObtained2 = 0;
			itemObtained3 = 0;
			itemObtained4 = 0;
			lvlp1 = 0;
			lvlp2 = 0;
			check1 = 0;
			check2 = 0;
		} else if (lvlp1 == 2 && lvlp2 == 1 && check1 == 1 && check2 == 1) {
			obtainItem = 0;
			itemObtained1 = 0;
			itemObtained2 = 0;
			itemObtained3 = 0;
			itemObtained4 = 0;
			// gameCanvas.fixItemChecker1(1);
			// gameCanvas.fixItemChecker2(1);
			lvlp1 = 0;
			lvlp2 = 0;
			check1 = 0;
			check2 = 0;
		}
	}

	public void liftPlatform() {
		if (player.getX() > 500 && player.getX() < 600 && player.getY() < 125 && player.getY() > 25) {
			liftChecker1 = 1;
		}

		if (liftChecker1 == 1 || liftChecker2 == 1) {
			liftCheck = true;
		}

	}

	public void liftPlatform2() {
		Rectangle2D.Double lift2 = new Rectangle2D.Double(730, 680, 55, 10);
		Rectangle2D.Double lift3 = new Rectangle2D.Double(690, 600, 55, 10);
		Rectangle2D.Double lift4 = new Rectangle2D.Double(730, 520, 55, 10);
		Rectangle2D.Double lift5 = new Rectangle2D.Double(690, 440, 55, 10);
		Rectangle2D.Double lift6 = new Rectangle2D.Double(730, 360, 55, 10);
		if (liftChecker1 == 1 || liftChecker2 == 1) {
			handlePlatformCollision(lift2);
			handlePlatformCollision(lift3);
			handlePlatformCollision(lift4);
			handlePlatformCollision(lift5);
			handlePlatformCollision(lift6);
		}
	}

	/**
	 * Handles player and spike collision.
	 */
	public void checkSpikes() {
		if (lvlCounter == 1) {
			if (((player.getX()) + 25 > 0 && (player.getX() + 25) < (0 + 25) && (player.getY() + 40) > 475
					&& (player.getY() + 40) < (475 + 25)) ||
					((player.getX() + 25) > 30 && (player.getX() + 25) < (30 + 25) && (player.getY() + 40) > 475
							&& (player.getY() + 40) < (475 + 25))
					||
					((player.getX() + 25) > 60 && (player.getX() + 25) < (60 + 25) && (player.getY() + 40) > 475
							&& (player.getY() + 40) < (475 + 25))
					||
					((player.getX() + 25) > 350 && (player.getX() + 25) < (350 + 25) && (player.getY() + 40) > 180
							&& (player.getY() + 40) < (180 + 25))
					||
					((player.getX() + 25) > 380 && (player.getX() + 25) < (380 + 25) && (player.getY() + 40) > 180
							&& (player.getY() + 40) < (180 + 25))
					||
					((player.getX() + 25) > 410 && (player.getX() + 25) < (410 + 25) && (player.getY() + 40) > 180
							&& (player.getY() + 40) < (180 + 25))
					||
					((player.getX() + 25) > 145 && (player.getX() + 25) < (145 + 25) && (player.getY() + 40) > 180
							&& (player.getY() + 40) < (180 + 25))
					||
					((player.getX() + 25) > 175 && (player.getX() + 25) < (175 + 25) && (player.getY() + 40) > 180
							&& (player.getY() + 40) < (180 + 25))
					||
					((player.getX() + 25) > 650 && (player.getX() + 25) < (650 + 25) && (player.getY() + 40) > 385
							&& (player.getY() + 40) < (385 + 25))
					||
					((player.getX() + 25) > 680 && (player.getX() + 25) < (680 + 25) && (player.getY() + 40) > 385
							&& (player.getY() + 40) < (385 + 25))
					||
					(spikeChecker2 == 1)) {
				timer2.stop();
				gameCanvas.switchEndScreen(1);
				gameCanvas.timesUp(1);
				layeredPane.remove(counterLabel);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
				spikeChecker1 = 1;
			}
		} else if (lvlCounter == 2) {
			if (((player.getX()) + 25 > 150 && (player.getX() + 25) < (150 + 25) && (player.getY() + 40) > 630
					&& (player.getY() + 40) < (630 + 25)) ||
					((player.getX() + 25) > 180 && (player.getX() + 25) < (180 + 25) && (player.getY() + 40) > 630
							&& (player.getY() + 40) < (630 + 25))
					||
					((player.getX() + 25) > 250 && (player.getX() + 25) < (250 + 25) && (player.getY() + 40) > 90
							&& (player.getY() + 40) < (90 + 25))
					||
					((player.getX() + 25) > 280 && (player.getX() + 25) < (280 + 25) && (player.getY() + 40) > 90
							&& (player.getY() + 40) < (90 + 25))
					||
					((player.getX() + 25) > 310 && (player.getX() + 25) < (310 + 25) && (player.getY() + 40) > 90
							&& (player.getY() + 40) < (90 + 25))
					||
					((player.getX() + 25) > 400 && (player.getX() + 25) < (400 + 25) && (player.getY() + 40) > 90
							&& (player.getY() + 40) < (90 + 25))
					||
					((player.getX() + 25) > 430 && (player.getX() + 25) < (430 + 25) && (player.getY() + 40) > 90
							&& (player.getY() + 40) < (90 + 25))
					||
					((player.getX() + 25) > 700 && (player.getX() + 25) < (700 + 25) && (player.getY() + 40) > 110
							&& (player.getY() + 40) < (110 + 25))
					||
					((player.getX() + 25) > 730 && (player.getX() + 25) < (730 + 25) && (player.getY() + 40) > 110
							&& (player.getY() + 40) < (110 + 25))
					||
					((player.getX() + 25) > 760 && (player.getX() + 25) < (760 + 25) && (player.getY() + 40) > 110
							&& (player.getY() + 40) < (110 + 25))
					||
					((player.getX() + 25) > 790 && (player.getX() + 25) < (790 + 25) && (player.getY() + 40) > 110
							&& (player.getY() + 40) < (110 + 25))
					||
					(spikeChecker2 == 1)) {
				timer2.stop();
				gameCanvas.switchEndScreen(1);
				gameCanvas.timesUp(1);
				layeredPane.remove(counterLabel);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
				spikeChecker1 = 1;
			}

		} else if (lvlCounter == 3) {
			if (((player.getX() + 25) > 800 && (player.getX() + 25) < (800 + 25) && (player.getY() + 40) > 625
					&& (player.getY() + 40) < (625 + 25))
					||
					((player.getX() + 25) > 770 && (player.getX() + 25) < (770 + 25) && (player.getY() + 40) > 625
							&& (player.getY() + 40) < (625 + 25))
					||
					((player.getX() + 25) > 830 && (player.getX() + 25) < (830 + 25) && (player.getY() + 40) > 625
							&& (player.getY() + 40) < (625 + 25))
					||
					((player.getX() + 25) > 860 && (player.getX() + 25) < (860 + 25) && (player.getY() + 40) > 625
							&& (player.getY() + 40) < (625 + 25))
					||
					((player.getX() + 25) > 360 && (player.getX() + 25) < (360 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 390 && (player.getX() + 25) < (390 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 460 && (player.getX() + 25) < (460 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 490 && (player.getX() + 25) < (490 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 520 && (player.getX() + 25) < (520 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 100 && (player.getX() + 25) < (100 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 70 && (player.getX() + 25) < (70 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 130 && (player.getX() + 25) < (130 + 25) && (player.getY() + 40) > 535
							&& (player.getY() + 40) < (535 + 25))
					||
					((player.getX() + 25) > 220 && (player.getX() + 25) < (220 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 250 && (player.getX() + 25) < (250 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 280 && (player.getX() + 25) < (280 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 350 && (player.getX() + 25) < (350 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 380 && (player.getX() + 25) < (380 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 410 && (player.getX() + 25) < (410 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 650 && (player.getX() + 25) < (650 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 680 && (player.getX() + 25) < (680 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 710 && (player.getX() + 25) < (710 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 300 && (player.getX() + 25) < (300 + 25) && (player.getY() + 40) > 160
							&& (player.getY() + 40) < (160 + 25))
					||
					((player.getX() + 25) > 330 && (player.getX() + 25) < (330 + 25) && (player.getY() + 40) > 160
							&& (player.getY() + 40) < (160 + 25))
					||
					((player.getX() + 25) > 900 && (player.getX() + 25) < (900 + 25) && (player.getY() + 40) > 160
							&& (player.getY() + 40) < (160 + 25))
					||
					((player.getX() + 25) > 930 && (player.getX() + 25) < (930 + 25) && (player.getY() + 40) > 160
							&& (player.getY() + 40) < (160 + 25))
					||
					((player.getX() + 25) > 960 && (player.getX() + 25) < (960 + 25) && (player.getY() + 40) > 160
							&& (player.getY() + 40) < (160 + 25))
					||
					(spikeChecker2 == 1)) {
				timer2.stop();
				gameCanvas.switchEndScreen(1);
				gameCanvas.timesUp(1);
				layeredPane.remove(counterLabel);
				layeredPane.add(startAgainButton, JLayeredPane.PALETTE_LAYER);
				layeredPane.add(exitButton, JLayeredPane.PALETTE_LAYER);
				spikeChecker1 = 1;
			}

		}
	}

	/**
	 * Checks for collisions with platforms and handles them.
	 */
	private void checkPlatform() {
		Level platforms = gameCanvas.getPlatforms();
		for (int i = 0; i < platforms.getPlatformCount(); i++) {
			Rectangle2D.Double platform = platforms.getPlatform(i);
			handlePlatformCollision(platform);
		}
	}

	/**
	 * Handles the collision between the player and a platform.
	 * 
	 * @param platform The platform to check for collision with.
	 */
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

	/**
	 * Plays background music.
	 */
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

	/**
	 * Connects to the server and initializes input/output streams.
	 */
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

	/**
	 * Receives player data from the server.
	 */
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
						notDoor = dataIn.readInt();
						if (notDoor != 0) {
							doorCheck2 = notDoor;
						}
						double prevX = playerOther.getX();
						double newX = dataIn.readDouble();
						playerOther.setX(newX);
						playerOther.setY(dataIn.readDouble());
						if (newX < prevX) {
							playerOther.setMovingLeft(true);
							playerOther.setMovingRight(false);
							playerOther.setLastDirection("left");
						} else if (newX > prevX) {
							playerOther.setMovingLeft(false);
							playerOther.setMovingRight(true);
							playerOther.setLastDirection("right");
						} else {
							playerOther.setMovingLeft(false);
							playerOther.setMovingRight(false);
						}
						not2 = dataIn.readInt();
						if (not2 != 0) {
							p2 = not2;
						}
						not22 = dataIn.readInt();
						if (not22 != 0) {
							p22 = not22;
						}
						second2 = dataIn.readInt();
						lvlp2 = dataIn.readInt();
						itemObtained2 = dataIn.readInt();
						itemObtained4 = dataIn.readInt();
						check2 = dataIn.readInt();
						liftChecker2 = dataIn.readInt();
						liftChecker5 = dataIn.readInt();
						spikeChecker2 = dataIn.readInt();
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

	/**
	 * Sends player data to the server.
	 */
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
						dataOut.writeInt(doorCheck1);
						dataOut.writeDouble(player.getX());
						dataOut.writeDouble(player.getY());
						dataOut.writeInt(p1);
						dataOut.writeInt(p12);
						dataOut.writeInt(second);
						dataOut.writeInt(lvlp1);
						dataOut.writeInt(itemObtained1);
						dataOut.writeInt(itemObtained3);
						dataOut.writeInt(check1);
						dataOut.writeInt(liftChecker1);
						dataOut.writeInt(liftChecker4);
						dataOut.writeInt(spikeChecker1);
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