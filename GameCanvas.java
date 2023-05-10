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
    private Eve eve;
    //private SnakeBody snakeBody;
    private GameFrame f;

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

        // graphic of maze
        f.getMazeImage().paintIcon(this,g2d,0,0);

        //draw characters
        f.getMe().draw(g2d);
        f.getEnemy().draw(g2d);
        //f.getBody().drawBody(g2d);
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

    public void startAnimation() {
        javax.swing.Timer animationTimer = new javax.swing.Timer(1,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                collisionDetection(f.getMe());
                f.getMe().move(1);

                collisionDetection(f.getEnemy());
                f.getEnemy().move(1);

                //f.getBody().move(1);
                //f.getBody();
                
                repaint();
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