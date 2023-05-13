/**
This is a template for a Java file.
@author Caryl Rae T. Chan (221503) & Michelle Kim Abarico (220017)
@version May 13, 2023
**/
/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.

The code below is the HRectangle class that implements the MazeBlock interface. It is used to create the horizontal
rectangles that make up the maze.
*/

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
