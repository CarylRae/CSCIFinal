import java.awt.*;
import java.awt.geom.*;

//Horizontal rectangles permanent height of 15 px (for maze building)

public class HRectangle implements MazeBlock{

    private double x;
    private double y;
    private double width;
    private double height;


    public HRectangle(double x, double y, double w){
        this.x = x;
        this.y = y;
        width = w;
        height = 15;
    }

    public void draw(Graphics2D g2d)
    {
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x,y,width,height);

        g2d.setColor(Color.BLACK);
        g2d.fill(rectangle);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

}
