

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GameCanvas extends JComponent{

    private int width;
    private int height;

    private MazeSkeleton MZ;
    private ArrayList<MazeBlock> canvasMaze;
    private Adam adam;
    private SnakeHead head;
    private SnakeBody snakeBody;

    public GameCanvas(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width,height));
        MZ = new MazeSkeleton(width);
        canvasMaze = MZ.buildMaze(); // Lamberlain V. Muli helped here
        adam = new Adam(419,550,10);
        head = new SnakeHead(200,300,10);
        snakeBody = new SnakeBody(head);

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

        adam.draw(g2d);
        //drawing snake
        head.draw(g2d);
        snakeBody.draw(g2d);
    }

    public void collisionDetection (){
        double adj = 3.5;

        for(MazeBlock block : canvasMaze)//if(player touching wall)
        {
            if(adam.isColliding(block)){
                if(adam.getUp()){
                    adam.setY(adam.getY()+adj);
                }
                else if(adam.getDown()){
                    adam.setY(adam.getY() -adj);
                }

                else if(adam.getRight()){
                    adam.setX(adam.getX()-adj);
                }

                else if(adam.getLeft()){
                    adam.setX(adam.getX() + adj);
                }

                adam.setDirection("stop");
            }
        }
    }

    public void startAnimation() {
        javax.swing.Timer animationTimer = new javax.swing.Timer(1,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                adam.moveA();
                collisionDetection();
                repaint();
            }

        });

        animationTimer.start();
    }

    public Adam getAdam(){
        return adam;
    }
}