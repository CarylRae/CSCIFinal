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

The code below is the maze block interface that is implemented by the maze block classes, HRectangle and VRectangle.
It contains the methods that are used by the maze block classes.
*/

import java.awt.*;


public interface MazeBlock {

    void draw (Graphics2D g2d);

    double getX();

    double getY();

    double getWidth();

    double getHeight();

}
