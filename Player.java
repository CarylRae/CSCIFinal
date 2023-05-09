import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Player { //superclass of adam & snake
    protected double x;
    protected double y;
    protected int size;
    protected boolean up, down, left, right;

    private Rectangle2D.Double s1;

    public Player(double x, double y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
    }

    public void move(int speed){

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

    public void setY(double n){
        y=n;
    }

    public void setDirection(String dir){ 
        
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

    public void draw(Graphics2D g2d) 
    {
        s1 = new Rectangle2D.Double(x,y,size,size);
        g2d.setColor(Color.GREEN);
        g2d.fill(s1);

    }

}
