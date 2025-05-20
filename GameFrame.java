import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.text.*;

/**
 * This file contains the GameFrame for the game where it encasulates the
 * buttons and the key listeners that are needed for the progression of the
 * game.
 * It also contains the countdown timer, animation for the title screen, item
 * logic, and collision of tiles with the player in game.
 * Lastly, it contains the gamecanvas which is placed in layered pane in order
 * to allow buttons to be placed on top of the image.
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
	private int x1, y1, x2, y2, x3;
	private int check1, check2, liftChecker1, liftChecker2, liftChecker3, liftChecker4, liftChecker5;
	private int spikeChecker1, spikeChecker2, doorCheck1, doorCheck2, notDoor, animationChecker;
	private JFrame frame;
	private GameCanvas gameCanvas;
	private JLayeredPane layeredPane;
	private Timer timer, timer2, timer3;
	private JButton startButton, characterButton1, characterButton2, characterExitButton;
	private Socket socket;
	private Player player, playerOther;
	private ReadFromServer playerRFS;
	private WriteToServer playerWTS;

	private boolean animationCheck1, animationCheck2, animationCheck3;

	private JLabel counterLabel;
	private int second, minute, second2;
	private DecimalFormat dFormat = new DecimalFormat("00");
	private String ddSecond;
	private Font font1 = new Font("Arial", Font.PLAIN, 50);

	/**
	 * Constructor for the GameFrame class.
	 * It initializes the variables for all the actions and conditions needed by
	 * this class for the game.
	 * it also creates the several JButtons and JLable alongside calling several
	 * methods.
	 */
	public GameFrame(int width, int height) {
		this.width = width;
		this.height = height;
		frame = new JFrame("Tiny Terrors");
		gameCanvas = new GameCanvas();
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, width, height);

		startButton = new JButton();
		characterButton1 = new JButton();
		characterButton2 = new JButton();
		characterExitButton = new JButton();
		startButton.setBounds(310, 540, 400, 100);
		characterButton1.setBounds(590, 240, 300, 165);
		characterButton2.setBounds(585, 480, 300, 165);
		characterExitButton.setBounds(160, 570, 265, 60);
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		characterButton1.setOpaque(false);
		characterButton1.setContentAreaFilled(false);
		characterButton1.setBorderPainted(false);
		characterButton2.setOpaque(false);
		characterButton2.setContentAreaFilled(false);
		characterButton2.setBorderPainted(false);
		characterExitButton.setOpaque(false);
		characterExitButton.setContentAreaFilled(false);
		characterExitButton.setBorderPainted(false);
		determinerChecker = 0;
		animationChecker = 0;
		animationCheck1 = true;
		animationCheck2 = true;
		animationCheck3 = true;

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

		spikeChecker1 = 0;
		spikeChecker2 = 0;

		counterLabel = new JLabel("2:00");
		counterLabel.setBounds(900, 0, 200, 100);
		counterLabel.setFont(font1);
		counterLabel.setForeground(Color.WHITE);
		second = 0;
		minute = 2;

		x1 = -30;
		y1 = -30;
		x2 = -30;
		y2 = 30;
		x3 = 30;

		connectToServer();
		determineCharacter();
		initializePlayer();
		setupGUI();
		setupKeyListener();
		setUpButtonListeners();
		setupAnimationTimer();
		playMusic("/music/scarymusic.wav", 0.5f);

	}

	/**
	 * Initializes the player depending on their playerID.
	 * This is to ensure that the player does not start as null.
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
	 * Sets which version of the character and their art will be played by the
	 * player.
	 * This is used in the Character Selector and contains two different types of
	 * checking, depending on the playerID.
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
	 * Used to show the screen for player 2's character selector.
	 */
	private void determineCharacter() {
		if (playerID == 2) {
			character_determiner1 = 5;
			character_determiner2 = 6;
			character_determiner3 = 7;
		}
	}

	/**
	 * Sets up the GUI of the Game where the initial buttons and the gameCanvas
	 * class are added.
	 * The frame is set to a JLayeredPane in order to accomodate both the image and
	 * buttons on top of each other.
	 */
	private void setupGUI() {
		layeredPane.add(gameCanvas);
		layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
		gameCanvas.setBounds(0, 0, width, height);
		frame.setContentPane(layeredPane);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		gameCanvas.repaint();
	}

	/**
	 * This is the main timer that runs during the game play of the game.
	 * It is responsible for consistenly updating the player's location, checking
	 * the platforms for collision, checking if the player has touched an item,
	 * if both players are on the doors and have pressed a key, and check if a
	 * player has touched a spike.
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

				} else if (lvlCounter == 2) {
					checkPlatform();
					gameCanvas.repaint();
					if (check1 != 0) {
						resetItem();
					}
					itemChecker2();
					if ((itemObtained1 == 1 && itemObtained4 == 1) || doorCheck2 == 2) {
						levelChecker();
					} else if ((itemObtained2 == 1 && itemObtained3 == 1) || doorCheck2 == 2) {
						levelChecker();
					}
					itemChecker2();
					checkSpikes();

				}

			}
		};
		timer = new Timer(interval, actionListener);
		timer.start();
	}

	/**
	 * Responsible for making the animation art play in the title screen.
	 */
	private void setupAnimationTimer() {
		int interval = 100;
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (animationCheck1 == true) {
					if (x1 <= 0) {
						x1++;
					}
					if (y1 <= 0) {
						y1++;
					}

					if (x1 > 0) {
						animationCheck1 = false;
					}
				} else if (animationCheck1 == false) {
					x1--;
					y1--;
					if (x1 < -30) {
						animationCheck1 = true;
					}
				}
				gameCanvas.animationImage1X(x1);
				gameCanvas.animationImage1Y(y1);

				if (animationCheck2 == true) {
					if (x2 <= 0) {
						x2++;
					}
					if (y2 <= 30) {
						y2++;
					}

					if (x2 > 0) {
						animationCheck2 = false;
					}
				} else if (animationCheck2 == false) {
					x2--;
					y2--;
					if (x2 < -30) {
						animationCheck2 = true;
					}
				}
				gameCanvas.animationImage2X(x2);
				gameCanvas.animationImage2Y(y2);

				if (animationCheck2 == true) {
					if (x3 >= 0) {
						x3--;
					}

					if (x3 < 0) {
						animationCheck2 = false;
					}
				} else if (animationCheck2 == false) {
					x3++;
					if (x3 > 30) {
						animationCheck2 = true;
					}
				}
				gameCanvas.animationImage3X(x3);

				if (animationChecker == 1) {
					timer3.stop();
				}

			}

		};
		timer3 = new Timer(interval, actionListener);
		timer3.start();

	}

	/**
	 * Stops the setupTimer method.
	 */
	public void mainTimerStop() {
		timer.stop();
	}

	/**
	 * Sets up the keylisteners for the movement (left & right, jumping) and going
	 * to the next level once both players are on the door.
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
	 * Sets up the buttons for the title screen and character selector.
	 */
	public void setUpButtonListeners() {
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
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
					lvlCounter = 1;
				} else {
					System.out.println("Choose a character.");
				}
				determinerChecker = 0;

			}
		};

		startButton.addActionListener(buttonListener);
		characterButton1.addActionListener(buttonListener2);
		characterButton2.addActionListener(buttonListener3);
		characterExitButton.addActionListener(buttonListener4);

	}

	/**
	 * Sets up the timer for the countdown timer in the game.
	 * It formats the time as well as fix the connection between the countdown
	 * timers of the two players.
	 * It takes into account the what will happen if the time hits 0.
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

				}
			}
		});

		timer2.start();
	}

	/**
	 * Resets the time back to 2 minutes to account to any possible error.
	 */
	public void resetAndStart() {
		minute = 2;
		second = 0;
	}

	/**
	 * Resets the time back to 2 minutes to account to any possible error.
	 */
	public void stopTime() {
		timer2.stop();
	}

	/**
	 * Responsible for checking if both players are on the door (regardless what
	 * position the two are, as long as they are in them door.
	 * It also checks if the "s" key for level 1, and "w" key for level 2 is pressed
	 * by one player.
	 * This causes the switch of the level or results screen.
	 */
	public void levelChecker() {
		if (lvlCounter == 1) {
			if (player.getX() > 790 && player.getX() < 840 && player.getY() < 190 && 110 < player.getY()) {
				lvlp1 = 1;
			} else if (player.getX() > 850 && player.getX() < 890 && player.getY() < 190 && 110 < player.getY()) {
				lvlp1 = 2;
			}

			if ((((lvlp1 == 1 && lvlp2 == 2) && doorCheck1 == 1) || doorCheck2 == 1) ||
					(((lvlp1 == 2 && lvlp2 == 1) && doorCheck1 == 1) || doorCheck2 == 1)) {
				gameCanvas.switchLevel(2);
				check1 = 1;
				resetItem();
				lvlCounter = 0;
				gameCanvas.fixItemChecker1(1);
				gameCanvas.fixItemChecker2(1);
				itemObtained2 = 0;
				lvlCounter = 2;
			}
		} else if (lvlCounter == 2) {
			if (player.getX() > 200 && player.getX() < 300 && player.getY() < 200 && player.getY() > 100) {
				lvlp1 = 1;
			} else if (player.getX() > 780 && player.getX() < 880 && player.getY() < 490 && player.getY() > 390) {
				lvlp1 = 2;
			}

			if ((((lvlp1 == 1 && lvlp2 == 2) && doorCheck1 == 2) || doorCheck2 == 2) ||
					(((lvlp1 == 2 && lvlp2 == 1) && doorCheck1 == 2) || doorCheck2 == 2)) {
				gameCanvas.timesUp(1);
				stopTime();
				if ((second <= 59 && second > 45) || (minute > 0)) {
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
			}
		}

	}

	/**
	 * Checks if the player is inside the bounds of the object in level 1 and makes
	 * the object dissapear into the items collected area.
	 * Likewise, it also ensures that this is properly printed on the opposing
	 * player's screen.
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

	/**
	 * Checks if the player is inside the bounds of the object in level 1 and makes
	 * the object dissapear into the items collected area.
	 * Likewise, it also ensures that this is properly printed on the opposing
	 * player's screen.
	 */
	public void itemChecker2() {
		if (lvlCounter == 2) {
			if (player.getX() > 600 && player.getX() < 700 && player.getY() < 360 && player.getY() > 260
					&& obtainItem == 0) {
				itemObtained1 = 1;
				gameCanvas.fixItemChecker1(0);
				obtainItem = 1;

			} else if (player.getX() > 900 && player.getX() < 1000 && player.getY() < 650 && player.getY() > 550
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

	/**
	 * Resets all of the variables that are used for checking.
	 */
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
		}
	}

	/**
	 * Checks if the player is touching the spike for each individual spike in both
	 * levels.
	 * The game will switch to the game over screen once either player touches a
	 * spike.
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
				spikeChecker1 = 1;
			}
		} else if (lvlCounter == 2) {
			if (((player.getX() + 25) > 360 && (player.getX() + 25) < (360 + 25) && (player.getY() + 40) > 535
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

					((player.getX() + 25) > 380 && (player.getX() + 25) < (380 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 410 && (player.getX() + 25) < (410 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 770 && (player.getX() + 25) < (650 + 25) && (player.getY() + 40) > 335
							&& (player.getY() + 40) < (335 + 25))
					||
					((player.getX() + 25) > 740 && (player.getX() + 25) < (740 + 25) && (player.getY() + 40) > 335
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
				spikeChecker1 = 1;
			}

		}
	}

	/**
	 * Obtains every platform for each level and checked them for collision.
	 */
	private void checkPlatform() {
		Level platforms = gameCanvas.getPlatforms();
		for (int i = 0; i < platforms.getPlatformCount(); i++) {
			Rectangle2D.Double platform = platforms.getPlatform(i);
			handlePlatformCollision(platform);
		}
	}

	/**
	 * Checks for collision of all sides of the player and the platform.
	 * It also check if a player is dropping or jumping to adjust their position and
	 * their speed.
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
	 * Plays the music that runs in a loop throughout the whole game, from title
	 * screen to the end screen.
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
	 * Creates the connection from the player to the server.
	 * It also ensures that the output and input streams are properly created.
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
	 * Reads the input from the server as long as the player is not null.
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
	 * Writes the output of the player to the server as long as the player is not
	 * null.
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