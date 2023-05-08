import java.awt.*;


public interface MazeBlock {

    void draw (Graphics2D g2d);

    double getX();

    double getY();

    double getWidth();

    double getHeight();

}
