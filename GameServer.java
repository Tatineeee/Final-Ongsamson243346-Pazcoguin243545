import java.io.*;
import java.net.*;

/**
 * This is the Server for the game that handles the connection between the
 * players.
 * It listens for incoming connections and manages the game state.
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

public class GameServer {

    private int numPlayers, maxPlayers;
    private double player1X, player1Y, player2X, player2Y;
    private ServerSocket ss;
    private Socket player1Socket, player2Socket;
    private ReadFromClient player1RFC, player2RFC;
    private WriteToClient player1WTC, player2WTC;
    private int play1, play2, second, lvlPlayer, itemObtained, itemObtained2, check, liftChecker, liftChecker2,
            spikeChecker, doorCheck;

    /**
     * Constructor for the GameServer class.
     * It initializes the server socket and sets the maximum number of players.
     */
    public GameServer() {
        try {
            ss = new ServerSocket(4444);
            System.out.println("Server started on port 4444");
        } catch (IOException e) {
            System.out.println("GameServer error: " + e.getMessage());
        }
        System.out.println("Starting server...");
        numPlayers = 0;
        maxPlayers = 2;
    }

    /**
     * This method accepts connections from players and starts the game.
     * It creates a new thread for each player to handle their input and output.
     */
    public void acceptConnections() {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < maxPlayers) {
                Socket socket = ss.accept();
                socket.setTcpNoDelay(true);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                numPlayers++;
                out.writeInt(numPlayers);
                System.out.printf("Player %d has connected\n", numPlayers);
                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);
                if (numPlayers == 1) {
                    player1Socket = socket;
                    player1RFC = rfc;
                    player1WTC = wtc;
                } else {
                    player2Socket = socket;
                    player2RFC = rfc;
                    player2WTC = wtc;
                    player1WTC.sendStartMessage();
                    player2WTC.sendStartMessage();
                    Thread readThread1 = new Thread(player1RFC);
                    Thread readThread2 = new Thread(player2RFC);
                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(player1WTC);
                    Thread writeThread2 = new Thread(player2WTC);
                    writeThread1.start();
                    writeThread2.start();
                }
            }
            System.out.println("All players have connected.");
        } catch (IOException e) {
            System.out.println("acceptConnections error: " + e.getMessage());
        }
    }

    /**
     * This method closes the server socket and all player sockets.
     */
    private class ReadFromClient implements Runnable {

        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClient(int id, DataInputStream in) {
            playerID = id;
            dataIn = in;
        }

        @Override
        public void run() {
            try {
                while (true) {
					doorCheck = dataIn.readInt();
                    if (playerID == 1) {
                        player1X = dataIn.readDouble();
                        player1Y = dataIn.readDouble();
                    } else {
                        player2X = dataIn.readDouble();
                        player2Y = dataIn.readDouble();
                    }
                    play1 = dataIn.readInt();
					play2 = dataIn.readInt();
                    second = dataIn.readInt();
                    lvlPlayer = dataIn.readInt();
                    itemObtained = dataIn.readInt();
                    itemObtained2 = dataIn.readInt();
                    check = dataIn.readInt();
                    liftChecker = dataIn.readInt();
                    liftChecker2 = dataIn.readInt();
                    spikeChecker = dataIn.readInt();
                }
            } catch (IOException e) {
                System.out.println("ReadFromClient error: " + e.getMessage());
            }

        }

    }

    /**
     * This method sends the game state to the clients.
     * It runs in a separate thread for each player.
     */
    private class WriteToClient implements Runnable {

        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClient(int id, DataOutputStream out) {
            playerID = id;
            dataOut = out;
        }

        @Override
        public void run() {
            try {
                while (true) {
					dataOut.writeInt(doorCheck);
                    if (playerID == 1) {
                        dataOut.writeDouble(player2X);
                        dataOut.writeDouble(player2Y);
                        dataOut.flush();
                    } else {
                        dataOut.writeDouble(player1X);
                        dataOut.writeDouble(player1Y);
                        dataOut.flush();
                    }
                    dataOut.writeInt(play1);
					dataOut.writeInt(play2);
                    dataOut.writeInt(second);
                    dataOut.writeInt(lvlPlayer);
                    dataOut.writeInt(itemObtained);
                    dataOut.writeInt(itemObtained2);
                    dataOut.writeInt(check);
                    dataOut.writeInt(liftChecker);
                    dataOut.writeInt(liftChecker2);
                    dataOut.writeInt(spikeChecker);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("WriteToClient Thread sleep error: " + e.getMessage());
                    }
                }
            } catch (IOException e) {
                System.out.println("WriteToClient error: " + e.getMessage());
            }
        }

        public void sendStartMessage() {
            try {
                dataOut.writeUTF("2 players connected.");
            } catch (IOException e) {
                System.out.println("sendStartMessage error: " + e.getMessage());
            }
        }

    }

    /**
     * Main method to start the server.
     * It creates an instance of GameServer and calls acceptConnections.
     **/
    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
    }

}