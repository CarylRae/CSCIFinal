package CSCIFinal;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square{
    private double x;
    private double y;
    private double size;
    private Color color;

    public Square(double x, double y, double size, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D g2d) {

        Rectangle2D.Double square = new Rectangle2D.Double(x,y,size,size);

        g2d.setColor(color);
        g2d.fill(square);

    }

}
