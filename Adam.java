


import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Adam extends Player{
   /*  private double x;
    private double y;
    private double size;
    private boolean up, down, left, right;

    public Adam(double x, double y, double size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;

    } */

    public Adam(int x, int y, int size){
        super(x, y, size);
        //super(x, y, size, "adam.png");
    }
 
    /* public void drawAdam(Graphics2D g2d) 
    {

        Rectangle2D.Double square = new Rectangle2D.Double(x,y,size,size);

        g2d.setColor(Color.green);
        g2d.fill(square);

    } */

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
    } */

    /* public double getX(){
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
    } */

    /* public void setDirection(String dir) {
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
    } */

}
