package CSCIFinal;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

//test

public class GameCanvas extends JComponent{

    private int width;
    private int height;

    private MazeSkeleton MZ;
    private ArrayList<MazeBlock> canvasMaze;
    private Adam adam;

    public GameCanvas(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width,height));
        MZ = new MazeSkeleton(width);
        canvasMaze = MZ.buildMaze(); // Lamberlain V. Muli helped here
        adam = new Adam(419,550,10);

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
    }

    public void startAnimation() {
        javax.swing.Timer animationTimer = new javax.swing.Timer(45,new ActionListener() {

            int speed = 4;
            @Override
            public void actionPerformed(ActionEvent ae) {
                adam.moveA(speed);
                repaint();

                for(MazeBlock block : canvasMaze){
                    if(block.isColliding(adam)){
                        speed = 0;
                        break;
                    }
                }

                adam.moveA(speed);
                repaint();
            }


        });

        animationTimer.start();
    }


    public Adam getAdam(){
        return adam;
    }
}
