/**
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

The code below is the game canvas that draws the maze and the characters.
*/

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GameCanvas extends JComponent{

    private int width;
    private int height;
    private MazeSkeleton MZ;
    private ArrayList<MazeBlock> canvasMaze;
    private GameFrame f;
 
    public GameCanvas(int w, int h, GameFrame frame) {
        width = w;
        height = h;
        f = frame;
        setPreferredSize(new Dimension(width,height));
        MZ = new MazeSkeleton(width);
        canvasMaze = MZ.buildMaze();
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
        f.getMe().getCharacterImage().paintIcon(this,g2d,(int)f.getMe().getX(),(int)f.getMe().getY());
        f.getEnemy().getCharacterImage().paintIcon(this,g2d,(int)f.getEnemy().getX(),(int) f.getEnemy().getY());
    
    }

    //Lamberlain Muli helped with logic for adjusting here
    public void collisionDetection (Player p){
        double adjust = 3.5;

        for(MazeBlock block : canvasMaze)//if player is touching wall
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


    public void startAnimation() {
        javax.swing.Timer animationTimer = new javax.swing.Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                collisionDetection(f.getMe());
                f.getMe().move(1);

                repaint();
            }
            
        });

        animationTimer.start();

    }

}