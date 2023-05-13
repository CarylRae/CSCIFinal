import java.io.*;
import java.net.*;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;
    private Socket adamSocket; //Adam
    private Socket eveSocket; //Eve
    private ReadFromClient adamReadRunnable;
    private ReadFromClient eveReadRunnable;
    private WriteToClient adamWriteRunnable;
    private WriteToClient eveWriteRunnable;
    private double adamX,adamY,eveX,eveY;

    private boolean end;


    public GameServer()
    {
        System.out.println("==== GAME SERVER ====");
        numPlayers = 0;
        maxPlayers = 2;

        try{
            ss = new ServerSocket(23000);
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

                if (numPlayers == 1){
                    System.out.println("Adam has connected.");
                } else {
                    System.out.println("Eve has connected.");
                }

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
                    eveSocket = s;
                    eveReadRunnable = rfc;
                    eveWriteRunnable = wtc;

                    //Send start message to both players that both are connected
                    adamWriteRunnable.sendStartMsg();
                    eveWriteRunnable.sendStartMsg();

                    //start the threads to start receiving data
                    Thread adamReadThread = new Thread(adamReadRunnable);
                    Thread eveReadThread = new Thread(eveReadRunnable);
                    adamReadThread.start();
                    eveReadThread.start();

                    //start the threads to start sending data
                    Thread adamWriteThread = new Thread(adamWriteRunnable);
                    Thread eveWriteThread = new Thread(eveWriteRunnable);
                    adamWriteThread.start();
                    eveWriteThread.start();

                    end = false;


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

            if (numPlayers == 1){
                System.out.println("RFC Adam Runnable created.");

            } else {
                System.out.println("RFC Eve Runnable created.");
            }

        }

        public void run(){
            try{
                while(end == false)
                {
                    if(playerID==1)
                    {
                        //Receiving ADAM coordinates

                        end = dataIn.readBoolean();
                        if (end==true)
                        {
                            try{
                                Thread.sleep(25);
                            }catch(InterruptedException ix){
                                System.out.println("InterruptedException from WTC run()");
                            }
                            closeConnection(playerID);
                        }
                        
                        adamX = dataIn.readDouble();
                        adamY = dataIn.readDouble();
                        
                    } else{

                        end = dataIn.readBoolean(); //true
                        if (end)
                        {

                            try{
                                Thread.sleep(25);
                            }catch(InterruptedException ix){
                                System.out.println("InterruptedException from WTC run()");
                            }
                            //pause tell other player to stop HERE
                            closeConnection(playerID);
                        }

                        //Receiving EVE coordinates
                        eveX = dataIn.readDouble();
                        eveY = dataIn.readDouble();
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
            if (playerID == 1){
                System.out.println("WTC Adam Runnable created.");

            } else {
                System.out.println("WTC Eve Runnable created.");
            }

        }

        public void run()
        {
            try{
                while(end==false)
                {
                    if(playerID==1)
                    {
                       
                        dataOut.writeBoolean(end);

                        //send Eve coordinates to Adam
                        dataOut.writeDouble(eveX);
                        dataOut.writeDouble(eveY);
                        dataOut.flush();

                    } else{

                        
                        dataOut.writeBoolean(end);
                        
                        //send Adam coordinates to Eve
                        dataOut.writeDouble(adamX);
                        dataOut.writeDouble(adamY);
                        
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

                if (playerID==1){
                    String goal = "You are Adam. Exit the opposite side of the garden without colliding with Eve.";
                    dataOut.writeUTF(goal);
                    dataOut.flush();

                } else {
                    String goal = "You are Eve. Collide with Adam before he leaves the garden. Deliver the Forbidden Fruit.";
                    dataOut.writeUTF(goal);
                    dataOut.flush();

                }

            }catch(IOException iox){
                System.out.println("IOException from sendStartMsg()");
            }
        }
    }

    public void closeConnection(int playerID)
        {
            try {
                adamSocket.close();
                eveSocket.close();
                System.out.println("adam closed.");
                System.out.println("eve closed.");

                System.out.println("Connection closed.");
            }catch (IOException iox)
            {
                System.out.println("IOException from method closeConnection() SSC");
            }
        }

    public static void main(String[] args)
    {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
 