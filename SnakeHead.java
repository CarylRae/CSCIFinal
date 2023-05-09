


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class SnakeHead extends Player {
   /*  private double x;
    private double y;
    private double size;
    private boolean up, down, left, right; */

    private Rectangle2D.Double s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12,s13;

    private int snakeX[] = new int[15];
    private int snakeY[] = new int[15];
    

    //public SnakeHead(double x, double y, double size, String myImage){
    public SnakeHead(double x, double y, double size){
        //super(x, y, size, "snakehead.png");
        super(x, y, size);

        /* this.x = x;
        this.y = y;
        this.size = size;

        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false; */

    }

    public void drawSnakeHead(Graphics2D g2d) {

        s1 = new Rectangle2D.Double(x,y,size,size);
        s2 = new Rectangle2D.Double(x,y,size,size);
        s3 = new Rectangle2D.Double(x,y,size,size);
        s4 = new Rectangle2D.Double(x,y,size,size);
        s5 = new Rectangle2D.Double(x,y,size,size);
        s6 = new Rectangle2D.Double(x,y,size,size);
        s7 = new Rectangle2D.Double(x,y,size,size);
        s8 = new Rectangle2D.Double(x,y,size,size);
        s9 = new Rectangle2D.Double(x,y,size,size);
        s10 = new Rectangle2D.Double(x,y,size,size);
        s11 = new Rectangle2D.Double(x,y,size,size);
        s12 = new Rectangle2D.Double(x,y,size,size);
        s13 = new Rectangle2D.Double(x,y,size,size);

        for(int i = 0; i<13; i++){
            if(i==0){
                g2d.setColor(Color.GREEN);
                g2d.fill(s1);
            }
            else{
                g2d.setColor(Color.BLUE);
                g2d.fill(s2);
                g2d.fill(s3);
                g2d.fill(s4);
                g2d.fill(s5);
                g2d.fill(s6);
                g2d.fill(s7);
                g2d.fill(s8);
                g2d.fill(s9);
                g2d.fill(s10);
                g2d.fill(s11);
                g2d.fill(s12);
                g2d.fill(s13);
            }
        }

    }

    /* public void move(){
        int speed = 2;

        if(up) {
            y -= speed;
        } else if(down) {
            y += speed;
        } else if(left) {
            x -= speed;
        } else if(right) {
            x += speed;
        }
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getSize(){
        return size;
    }

    public boolean getUp(){
        return up;
    }
    public boolean getDown(){
        return down;
    }
    public boolean getRight(){
        return right;
    }
    public boolean getLeft(){
        return left;
    }
    public void setX(double n){
        x=n;
    }

    public void setY(double m){
        y=m;
    }

    */

    public void setDirection(String dir) {
        for(int i = 13; i>0; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        
        if(dir.equals("up")) {
            up = true;
            down = false;
            left = false;
            right = false;

            snakeY[0] -= 2;

        } else if (dir.equals("down")) {
            up = false;
            down = true;
            left = false;
            right = false;

            snakeY[0] += 2;

        } else if (dir.equals("left")) {
            up = false;
            down = false;
            left = true;
            right = false;

            snakeX[0] -= 2;

        } else if (dir.equals("right")) {
            up = false;
            down = false;
            left = false;
            right = true;

            snakeX[0] += 2;

        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }
 

 //I suspect this shall be overridden later with method with more conditions
 
    /* public boolean isColliding(MazeBlock other) {
        return!(this.x + this.size <= other.getX() ||
                this.x >= other.getX() + other.getWidth() ||
                this.y + this.size <= other.getY() ||
                this.y >= other.getY() + other.getHeight());
    } */

   


}
