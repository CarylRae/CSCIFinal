


import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class SnakeBody {
    private double x;
    private double y;
    private double size;
    private boolean up, down, left, right;
    private ArrayList<Rectangle2D.Double> snakeArray;
    private Rectangle2D.Double s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12;


    public SnakeBody(SnakeHead h){
        x = h.getX();
        y = h.getY();
        size = h.getSize();

        snakeArray = new ArrayList<Rectangle2D.Double>();

        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;

    }

    public void draw(Graphics2D g2d) {

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

        g2d.setColor(Color.BLUE);
        g2d.fill(s1);
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


    }

    public void moveA(){
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

    public void setDirection(String dir) {
        if(dir.equals("up")) {
            up = true;
            down = false;
            left = false;
            right = false;
        } else if (dir.equals("down")) {
            up = false;
            down = true;
            left = false;
            right = false;
        } else if (dir.equals("left")) {
            up = false;
            down = false;
            left = true;
            right = false;
        } else if (dir.equals("right")) {
            up = false;
            down = false;
            left = false;
            right = true;
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }

    public boolean isColliding(MazeBlock other) {
        return!(this.x + this.size <= other.getX() ||
                this.x >= other.getX() + other.getWidth() ||
                this.y + this.size <= other.getY() ||
                this.y >= other.getY() + other.getHeight());
    }
}
