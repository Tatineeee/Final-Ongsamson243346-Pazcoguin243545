import java.io.*;
import java.net.*;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private Socket player1Socket;
    private Socket player2Socket;
    private ReadFromClient player1RFC;
    private ReadFromClient player2RFC;
    private WriteToClient player1WTC;
    private WriteToClient player2WTC;
    private double player1X, player1Y, player2X, player2Y;

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

    public void acceptConnections() {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < maxPlayers) {
                Socket socket = ss.accept();
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
                    if (playerID == 1) {
                        player1X = dataIn.readDouble();
                        player1Y = dataIn.readDouble();
                    } else {
                        player2X = dataIn.readDouble();
                        player2Y = dataIn.readDouble();
                    }
                }
            } catch (IOException e) {
                System.out.println("ReadFromClient error: " + e.getMessage());
            }

        }

    }

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
                    if (playerID == 1) {
                        dataOut.writeDouble(player2X);
                        dataOut.writeDouble(player2Y);
                        dataOut.flush();
                    } else {
                        dataOut.writeDouble(player1X);
                        dataOut.writeDouble(player1Y);
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
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

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
    }

}