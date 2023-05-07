import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;


public class GameFrame extends JFrame{

    private GameCanvas gc;
    private Container cp; //contentpane
    private int width, height;
    private Socket socket;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private Player me, enemy;

    public GameFrame(int w, int h)
    {
        width = w;
        height = h;
        
        /* up = false; //will become ! when up key is clicked
        down = false;
        left = false;
        right = false; */
    }

    public void setUpGUI(){
        /* int w = 843;
        int h = 675; 

        JFrame f = new JFrame(); // this is now being created in the starter */

       /*  this.setTitle("Final Project - Abarico, Michelle Kim - Chan, Caryl Rae 221503");
        cp = this.getContentPane(); 
        cp.setFocusable(true); */ //MAY be commented out as irrelevant
        //JPanel p = new JPanel(); //commented out as unused
        //cp.add(dc, BorderLayout.CENTER); // game at center (for what?)

        /* cp.add(gc);

        gc = new GameCanvas(width,height);
        gc.startAnimation(); */
        
        

        /* pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); */

        cp = this.getContentPane();
        this.setTitle("Final Project - Abarico, Michelle Kim - Chan, Caryl Rae 221503");
        cp.setPreferredSize(new Dimension(width,height));

        //createSprites();

        gc = new GameCanvas(width,height);
        cp.add(gc);
        gc.startAnimation();
        //cp.setFocusable(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    //this might belong in player class
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

    //see line 70 comment
    private class MoveAction extends AbstractAction {
        private String direction;

        public MoveAction(String dir) {
            direction = dir;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            gc.getAdam().setDirection(direction);
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
                while(true)
                {
                    double enemyX = dataIn.readDouble();
                    double enemyY = dataIn.readDouble();

                    if (enemy != null)
                    {
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


                Thread readThread = new Thread((rfsRunnable));
                Thread writeThread = new Thread((wtsRunnable));

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
                while(true)
                {
                    if (me != null) {
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
                System.out.println("IOException from WTS run()");
            }

        }
    }



    public void connectToServer()
    {
        try{
            socket = new Socket("localhost",45372);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            
            if (playerID == 1)
            {
                System.out.println("You are Adam. Escape through the other end of the maze without being caught by the Snake.");
                System.out.println("Waking up the serpent...");
            }

            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);
            rfsRunnable.waitForStartMsg();

        }catch (IOException iox){
            System.out.println("IOException from connectToServer()");
        }

    }

    
}