import javax.swing.*;
import javax.xml.stream.events.EndElement;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GameCanvas extends JComponent{

    private int width;
    private int height;

    private MazeSkeleton MZ;
    private ArrayList<MazeBlock> canvasMaze;
    private Adam adam;
    private Eve eve;
    //private SnakeBody snakeBody;
    private GameFrame f;
    private boolean end;
    private int winner; //1 for Adam, 2 for Eve

    /* private ArrayList<Double> snakeXCoordinates;
    private ArrayList<Double> snakeYCoordinates; */

    public GameCanvas(int w, int h, GameFrame frame) {
        width = w;
        height = h;
        f = frame;
        setPreferredSize(new Dimension(width,height));
        MZ = new MazeSkeleton(width);
        canvasMaze = MZ.buildMaze(); // Lamberlain V. Muli helped here
        //adam = new Adam(419,550,10);
        //head = new SnakeHead(416.5,300.5,10);

        //snakeBody = new SnakeBody(head);

        /* snakeXCoordinates = new ArrayList<Double>();
        snakeYCoordinates = new ArrayList<Double>(); */

        winner = 0;
        end = false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        for(MazeBlock block : canvasMaze)
        {
            block.draw(g2d);
        }
        
        //draw squares where characters are "pinned" to
        f.getMe().draw(g2d);
        f.getEnemy().draw(g2d);

        // graphic of maze
        f.getMazeImage().paintIcon(this,g2d,0,0);

        // draw/paint the character graphics that uses the squares as reference
        // subject to change once i
        f.getMe().getCharacterImage().paintIcon(this,g2d,(int)f.getMe().getX(),(int)f.getMe().getY());
        f.getEnemy().getCharacterImage().paintIcon(this,g2d,(int)f.getEnemy().getX(),(int) f.getEnemy().getY());
    
    }

    public void collisionDetection (Player p){
        double adjust = 3.5;

        for(MazeBlock block : canvasMaze)//if(player touching wall)
        {
            if(p.isColliding(block)){
                if(p.getUp()){
                    p.setY(p.getY()+adjust);
                }
                else if(p.getDown()){
                    p.setY(p.getY() -adjust);
                }

                else if(p.getRight()){
                    p.setX(p.getX()-adjust);
                }

                else if(p.getLeft()){
                    p.setX(p.getX() + adjust);
                }

                p.setDirection("stop");
            }
        }
    }


    //collision detection for characters
    public boolean eveWin(Player me, Player enemy) //mutator method
    {
        if (me.isColliding(enemy)){
            end = true;
        }
        return end;
    }

    //THIS HAS LOGIC BUGS
    public boolean adamWin(int playerID,Player me,Player enemy)
    {
        if (playerID == 1){
            for(MazeBlock block : canvasMaze)
            {
                if(me.isColliding(block) && block instanceof Gate){
                    end = true;
                    return end;
                    
                }   
            }
        }
        
        if (playerID == 2){
            for(MazeBlock block : canvasMaze)
            {
                if(enemy.isColliding(block) && block instanceof Gate){
                    end = true;
                    return end;  
                }
            }
        }
 
        return end;
    } 

    // public boolean checkForWin(int playerID, Player me, Player enemy)
    // {
    //     if (playerID == 2 && me.isColliding(enemy)){
    //         winner = playerID;
    //         end = true;
    //         return end;
    //     }
        
    //     if (playerID == 1){
    //         for(MazeBlock block : canvasMaze)
    //         {
    //             if(me.isColliding(block) && block instanceof Gate){
    //                 end = true;
    //                 winner = playerID;
    //                 return end;
    //             }
                    
    //         }
    //     }

    //     return end;
        
    // } 

    public void startAnimation() {
        javax.swing.Timer animationTimer = new javax.swing.Timer(1,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                // collisionDetection(f.getEnemy());
                // f.getEnemy().move(1);
                
                //f.getBody().move(1);
                //f.getBody();

                //check for Eve win
                
                adamWin(f.getPlayerID(),f.getMe(),f.getEnemy());
                eveWin(f.getMe(),f.getEnemy());

                collisionDetection(f.getMe());
                f.getMe().move(1);

                //checkForWin(f.getPlayerID(), f.getMe(),f.getEnemy());

                if (end == false){
                    repaint();
                }
            }
            
        });

        animationTimer.start();

    }

    public Adam getAdam(){
        return adam;
    }

    public Eve getEve(){
        return eve;
    }

    /* public SnakeBody getSnakeBody(){
        return snakeBody;
    } */

}