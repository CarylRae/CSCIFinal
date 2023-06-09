/**
This is a template for a Java file.
@author Caryl Rae T. Chan (221503) & Michelle Kim Abarico (220017)
@version May 14, 2023
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

The code below is the GameFrame that contains the GameCanvas and the Players.
It also contains the methods that allow the sending and receiving of data between the Player and the Server. 
It contains the methods to add and stop the KeyBindings.
The data it shares are the coordinates of the players and the boolean value of the end game.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;


public class GameFrame extends JFrame{

    private GameCanvas gc; //GameCanvas
    private JPanel cp; //ContentPane
    private int width, height;
    private Socket socket;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private Player me, enemy;

    private boolean end;

    private ImageIcon mazeImage;
    private JLabel mazeLabel;

    public GameFrame(int w, int h)
    {
        width = w;
        height = h;

        // https://www.youtube.com/watch?v=yGcYoz0s94E
        mazeImage = new ImageIcon(this.getClass().getResource("Maze Graphic.png"));
        mazeLabel = new JLabel(mazeImage);
        mazeLabel.setBounds(0,0,w,h);
    
    }

    public void setUpGUI(){
      
        cp = (JPanel) this.getContentPane();

        if (playerID == 1){
            this.setTitle("Adam | Final Project - Abarico, Michelle - Chan, Caryl 221503");
        }
        else{
            this.setTitle("Eve | Final Project - Abarico, Michelle - Chan, Caryl 221503");
        }

        cp.setPreferredSize(new Dimension(width,height));

        createPlayers();

        gc = new GameCanvas(width,height,this);
        end = false;

        cp.add(gc);
        gc.startAnimation();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public ImageIcon getMazeImage(){
        return mazeImage;
    }

    private void createPlayers(){
        if(playerID == 1){ 
            me = new Adam(414,543,10,"adam.png");
            enemy = new Eve(417,301,10,"eve.png");
           

        } else {
            enemy = new Adam(414,543,10,"adam.png");
            me = new Eve(417,301,10,"eve.png");
         
        }
    }

    public int getPlayerID(){
        return playerID;
    }

    public Player getMe()
    {
        return me;
    }

    public Player getEnemy()
    {
        return enemy;
    }


    public void addKeyBindings() {

        gc.setFocusable(true);

        ActionMap am = gc.getActionMap();
        InputMap im = gc.getInputMap();

        am.put("up", new MoveAction("up"));
        am.put("down", new MoveAction("down"));
        am.put("left", new MoveAction("left"));
        am.put("right", new MoveAction("right"));
        am.put("stop", new MoveAction(""));

        
        am.put("speedUp", new MoveAction("speedUp")); // power ups
        am.put("normalSpeed", new MoveAction("normalSpeed")); // undo changes

        am.put("random", new MoveAction("random")); // trap
        

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "stop");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "speedUp");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0, false), "normalSpeed");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "random");
    }


    public void stopKeyBindings() {


        ActionMap am = gc.getActionMap();
        InputMap im = gc.getInputMap();

        am.put("stop", new MoveAction(""));
        am.put("stop", new MoveAction(""));
        am.put("stop", new MoveAction(""));
        am.put("stop", new MoveAction(""));
        am.put("stop", new MoveAction(""));

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "stop");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "stop");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "stop");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "stop");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "stop");
    }


    private class MoveAction extends AbstractAction {
        private String direction;

        public MoveAction(String dir) {
            direction = dir;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
                me.setDirection(direction);
            }
            
    }

    public void playMusic(String filePath)
    {
        Clip clip;
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException x)
        {
            System.out.println("Error playing music.");
        }
    }
    

    //NETWORKING
    public void connectToServer(String i)
    {
        String ipAddress = i;
        try{
            socket = new Socket(ipAddress,23000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();

            if (playerID == 1)
            {
                System.out.println("Waiting for Eve...");
            }

            wtsRunnable = new WriteToServer(out);

            rfsRunnable = new ReadFromServer(in);
            rfsRunnable.waitForStartMsg();


        }
        catch (IOException iox){
            System.out.println("IOException from connectToServer()");
        }
    }

    private class ReadFromServer implements Runnable{
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in)
        {
            dataIn = in;
            System.out.println("RFS Runnable created.");
        }

        public void run()
        {
            try{
                
                while(end == false)
                {
                    end = dataIn.readBoolean(); 

                    //close client-side socket if true
                    if (end==true)
                    {
                        closeConnection();
                    }
                    
                    if (enemy != null)
                    {
                        //set enemy coordinates
                        enemy.setX(dataIn.readDouble());
                        enemy.setY(dataIn.readDouble());
                    }
                }
            }catch(IOException iox){
                closeConnection();
                System.out.println("IOException from RFS run()");
            }
        }

        public void waitForStartMsg()
        {
            try{

                String startMsg = dataIn.readUTF();
                System.out.println("Message from Server: " + startMsg);
                String goal = dataIn.readUTF();
                System.out.println(goal);

                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);

                readThread.start();
                writeThread.start();

            }catch(IOException iox){
                System.out.println("IOException from waitForStartMsg()");
            }
        }
    }

    private class WriteToServer implements Runnable{
        private DataOutputStream dataOut;

        public WriteToServer(DataOutputStream out)
        {
            dataOut = out;
            System.out.println("WTS Runnable created.");
        }

        public void run(){
            try{
                while(end==false)
                {
                    if (me != null) {
                        
                        dataOut.writeBoolean(end); 
                        dataOut.flush();

                    //close client-side socket if true
                        if (end == true)
                        {
                            closeConnection();
                        }

                        //sending my coordinates
                        dataOut.writeDouble(me.getX());
                        dataOut.writeDouble(me.getY());
                        dataOut.flush();

                    }

                    try{
                        Thread.sleep(25);
                    }catch(InterruptedException ix){
                        System.out.println("InterruptedException from WTS run()");
                    }
                }


            }catch(IOException iox){
                closeConnection();
                System.out.println("IOException from WTS run()");
            }
        }
        
    }

    public void closeConnection()
        {
            
            try {
                socket.close(); //closes client-side socket
                stopKeyBindings();
                gc.setIgnoreRepaint(true);
                System.out.println("---CONNECTION CLOSED---");
            }catch (IOException iox)
            {
                System.out.println("IOException from method closeConnection() CSC");
            }
        }

}
