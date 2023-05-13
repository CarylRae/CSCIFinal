/**
This is a template for a Java file.
@author Caryl Rae T. Chan (221503) & Michelle Kim Abarico (220017)
@version May 13, 2023
**/
/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.

The code below is the Game Server that allows the sending and receiving of data between the two players. 
The data it shares are the coordinates of the players and the boolean value of the end game.
*/


import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {

    public static final int width = 843;
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

    private MazeSkeleton MZ;
    private ArrayList<MazeBlock> canvasMaze;

    //private int winner; //1 for Adam, 2 for Eve


    private boolean end;


    public GameServer()
    {
        System.out.println("==== GAME SERVER ====");
        numPlayers = 0;
        maxPlayers = 2;

        

        MZ = new MazeSkeleton(width);
        canvasMaze = MZ.buildMaze(); // Lamberlain V. Muli helped here

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

    public boolean aeCollide(int pid) {
        int playerID = pid;
            if (playerID == 1){
                return!(
                adamX + 12 <= eveX ||
                adamX >= eveX + 12 ||
                adamY + 12 <= eveY ||
                adamY >= eveY + 12);
            } else {
                return!(
                eveX + 12 <= adamX ||
                eveX >= adamX + 12 ||
                eveY + 12 <= adamY ||
                eveY >= adamY + 12);
            }
    }

    public boolean doesAdamWin(MazeBlock other) {
        return!(adamX + 12 <= other.getX() ||
                adamX >= other.getX() + other.getWidth() ||
                adamY + 12 <= other.getY() ||
                adamY >= other.getY() + other.getHeight());
    }

    public boolean checkForWin(int pid)
    {
        int playerID = pid;

        if (aeCollide(playerID)){
            System.out.println("Eve won!");
            end = true;
            return end;
        }

        for(MazeBlock block : canvasMaze)
        {
            if(playerID == 1 && doesAdamWin(block) && block instanceof Gate){
                System.out.println("Adam won!");
                end = true;
                
                return end;
            } else if (playerID == 2 && doesAdamWin(block) && block instanceof Gate){
                System.out.println("Adam won!");
                end = true;

                return end;
            }
                    
        }

        return end;
        
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
                            closeConnection(playerID);
                        }
                        
                        adamX = dataIn.readDouble();
                        adamY = dataIn.readDouble();
                        end = checkForWin(playerID);
                        
                    } else{

                        end = dataIn.readBoolean(); //true
                        if (end==true)
                        {

                            closeConnection(playerID);
                        }

                        //Receiving EVE coordinates
                        eveX = dataIn.readDouble();
                        eveY = dataIn.readDouble();
                        end = checkForWin(playerID);

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
                        dataOut.flush();


                        //send Eve coordinates to Adam
                        dataOut.writeDouble(eveX);
                        dataOut.writeDouble(eveY);
                        dataOut.flush();

                    } else{

                        
                        dataOut.writeBoolean(end);
                        dataOut.flush();

                        
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
 