import java.awt.*;
import java.awt.geom.*;

//Vertical rectangles permanent width of 15 px (for maze building)
public class VRectangle implements MazeBlock{

    private double x;
    private double y;
    private double width;
    private double height;


    public VRectangle(double x, double y, double h){
        this.x = x;
        this.y = y;
        width = 15;
        height = h;

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
