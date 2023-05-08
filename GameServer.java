import java.io.*;
import java.net.*;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private Socket adamSocket; //Adam
    private Socket snakeSocket; //Snake
    private ReadFromClient adamReadRunnable;
    private ReadFromClient snakeReadRunnable;
    private WriteToClient adamWriteRunnable;
    private WriteToClient snakeWriteRunnable;
    private double p1x,p1y,p2x,p2y;

    public GameServer()
    {
        System.out.println("==== GAME SERVER ====");
        numPlayers = 0;
        maxPlayers = 2;
        /* p1x = 419;
        p1y = 550;
        p2x = 416.5;
        p2y = 315.5; */


        try{
            ss = new ServerSocket(45372);
        }catch (IOException iox){
            System.out.println("IOException from GameServer constructor");
        }

    }

    public void acceptConnections()
    {
        try{
            System.out.println("Waiting for connections...");
            while(numPlayers<maxPlayers)
            {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers); //send the current number of players to the player
                //out.flush();

                System.out.println("Player #" + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers,in);
                WriteToClient wtc = new WriteToClient(numPlayers,out);

                if(numPlayers == 1)
                {
                    adamSocket = s;
                    adamReadRunnable = rfc;
                    adamWriteRunnable = wtc;
                }
                else
                {
                    snakeSocket = s;
                    snakeReadRunnable = rfc;
                    snakeWriteRunnable = wtc;

                    //Send start message to both players that both are connected
                    adamWriteRunnable.sendStartMsg();
                    adamWriteRunnable.sendStartMsg();

                    //start the threads to start sending data
                    Thread adamReadThread = new Thread(adamReadRunnable);
                    Thread snakeReadThread = new Thread(snakeReadRunnable);
                    adamReadThread.start();
                    snakeReadThread.start();

                    Thread adamWriteThread = new Thread(adamWriteRunnable);
                    Thread snakeWriteThread = new Thread(snakeWriteRunnable);
                    adamWriteThread.start();
                    snakeWriteThread.start();

                }

            }

            System.out.println("No longer accepting connections.");

        }catch (IOException iox){
            System.out.println("IOException from acceptConnections");
        }

    }

    private class ReadFromClient implements Runnable{
        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClient(int pid, DataInputStream in)
        {
            playerID = pid;
            dataIn = in;
            System.out.println("RFC" + playerID + " Runnable created.");

        }

        public void run(){
            try{
                while(true)
                {
                    if(playerID==1)
                    { //FOR REWRITING
                        p1x = dataIn.readDouble();
                        p1y = dataIn.readDouble();
                    } else{
                        p2x = dataIn.readDouble();
                        p2y = dataIn.readDouble();
                    }
                }
            }catch(IOException iox){
                System.out.println("IOException from RFC run()");
            }

        }
    }

    private class WriteToClient implements Runnable{
        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClient(int pid, DataOutputStream out)
        {
            playerID = pid;
            dataOut = out;
            System.out.println("WTC" + playerID + " Runnable created.");

        }

        public void run()
        {
            try{
                while(true)
                {
                    if(playerID==1)
                    {
                        dataOut.writeDouble(p2x);
                        dataOut.writeDouble(p2y);
                        dataOut.flush();
                    } else{
                        dataOut.writeDouble(p1x);
                        dataOut.writeDouble(p1y);
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    }catch(InterruptedException ix){
                        System.out.println("InterruptedException from WTC run()");
                    }
                }
            }catch(IOException iox){
                System.out.println("IOException from WTC run()");
            }
        }

        public void sendStartMsg()
        {
            try{
                dataOut.writeUTF("Both players are connected.");
                dataOut.flush();
            }catch(IOException iox){
                System.out.println("IOException from sendStartMsg()");
            }
        }
    }


    public static void main(String[] args)
    {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }


}
