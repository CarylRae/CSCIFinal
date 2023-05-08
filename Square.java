import java.awt.*;
import java.awt.geom.*;

public class Square implements MazeBlock{
    private double x;
    private double y;
    private double size;

    public Square(double x, double y, double size){
        this.x = x;
        this.y = y;
        this.size = size;

    }

    public void draw(Graphics2D g2d) {

        Rectangle2D.Double square = new Rectangle2D.Double(x,y,size,size);

        g2d.setColor(Color.BLACK);
        g2d.fill(square);

    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getWidth(){
        return size;
    }

    public double getHeight(){
        return size;
    }

}