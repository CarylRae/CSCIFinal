


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class SnakeBody {
    private double x;
    private double y;
    private double size;
    private boolean up, down, left, right;
    public ArrayList<Rectangle2D.Double> snakeArray;

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

        //snakeArray.add(new Rectangle2D.Double(x+15,y+15,size,size));
        snakeArray.add(new Rectangle2D.Double(x,y,size,size));
        // snakeArray.add(new Rectangle2D.Double(x,y,size,size));
        // snakeArray.add(new Rectangle2D.Double(x,y,size,size));
        // snakeArray.add(new Rectangle2D.Double(x,y,size,size));
        // snakeArray.add(new Rectangle2D.Double(x,y,size,size));



        g2d.setColor(Color.red);
        g2d.fill(new Rectangle2D.Double(x+15,y+15,size,size));

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
