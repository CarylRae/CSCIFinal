//Abarico (22-04-2023)

package CSCIFinal;

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

        g2d.setColor(Color.blue);
        g2d.fill(rectangle);
    }

    public boolean isColliding(Adam adam)
    {
        System.out.println("HRect");
        return !( //negated (!) for readability/semantics because method name isColliding
                this.x + this.width <= adam.getX() || //r1 left of r2
                        this.x >= adam.getX() + adam.getWidth() || //r1 right of r2
                        this.y + this.height <= adam.getY() || //r1 above r2
                        this.y >= adam.getY() + adam.getHeight() //r1 below r2
        );
        //if at least 1 is true, then NOT colliding
        //if all false, then colliding
    }

    public double getSize(){
        return width;
    }

}
