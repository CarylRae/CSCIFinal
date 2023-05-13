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
    //private SnakeBody body;

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
        end = gc.checkForWin(playerID,me,enemy);

        
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
        if(playerID == 1){ //For editing: Adam coordinates are not centered
            me = new Adam(414,543,10,"adam.png");// MAZE_EDGE_X+(11*C),MAZE_EDGE_Y-C,C*3
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

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "stop");
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
                //body.setDirection(direction);
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
    public void connectToServer()
    {
        try{
            socket = new Socket("localhost",23000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();

            if (playerID == 1)
            {
                System.out.println("Picking the fruit...");
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

                    //Read Enemy coordinates from Server
                    if (end==true)
                    {
                        closeConnection();
                    }
                    
                    if (enemy != null)
                    {
                        //Apply enemy coordinates to enemy graphic

                        enemy.setX(dataIn.readDouble());
                        enemy.setY(dataIn.readDouble());
                    }
                }
            }catch(IOException iox){
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
                    //Send Player's coordinates to Server
                    if (me != null) {
                        end = gc.checkForWin(playerID,me,enemy);
                        dataOut.writeBoolean(end);
                        dataOut.flush();

                        //send then close
                        if (end == true)
                        {
                            
                            closeConnection();
                        }

                        dataOut.writeDouble(me.getX());
                        dataOut.writeDouble(me.getY());
                        //System.out.println("Sending MY coordinates: " + me.getX() + " and " + me.getY()); //FOR TESTING
                        dataOut.flush();

                        
                    }

                    try{
                        Thread.sleep(25);
                    }catch(InterruptedException ix){
                        System.out.println("InterruptedException from WTS run()");
                    }
                }


            }catch(IOException iox){
                System.out.println("IOException from WTS run()");
            }
        }
        
    }

    public void closeConnection()
        {
            
            try {
                socket.close(); //first to close connection is the winner
                //stopKeyBindings();
                //System.out.println(winner + " won! " + loser + " lost.");
                System.out.println("---CONNECTION CLOSED---");
            }catch (IOException iox)
            {
                System.out.println("IOException from method closeConnection() CSC");
            }
        }

    
}