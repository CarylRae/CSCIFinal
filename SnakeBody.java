
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class SnakeBody {
    private double x;
    private double y;
    private double size;
    private boolean up, down, left, right;

    private double snakeX[] = new double[30];
    private double snakeY[] = new double[30];

    public SnakeBody(Player h){
        x = h.getX();
        y = h.getY();
        size = h.getSize();

        snakeX[0] = x;
        snakeY[0] = y;

        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;

    }

    public void draw(Graphics2D g2d) {

        for (int i = 0; i<29; i++){
            if(i == 0){
                Rectangle2D.Double r1 = new Rectangle2D.Double(snakeX[i], snakeY[i], size, size);
                g2d.setColor(Color.GREEN);
                g2d.fill(r1);
            }
            else{
                Rectangle2D.Double r1 = new Rectangle2D.Double(snakeX[i], snakeY[i], size, size);
                g2d.setColor(new Color(45,180,0));
                g2d.fill(r1);
            }
        }
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

    public void setY(double m){
        y=m;
    }


    public void setDirection(String dir) {
        
        for(int i = 29; i>0; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        
        if(dir.equals("up")) {
            up = true;
            down = false;
            left = false;
            right = false;

            snakeY[0] -= 10;

        } else if (dir.equals("down")) {
            up = false;
            down = true;
            left = false;
            right = false;

            snakeY[0] += 10;

        } else if (dir.equals("left")) {
            up = false;
            down = false;
            left = true;
            right = false;

            snakeX[0] -= 10;

        } else if (dir.equals("right")) {
            up = false;
            down = false;
            left = false;
            right = true;

            snakeX[0] += 10;

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
