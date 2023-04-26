//Abarico (22-04-2023)

package CSCIFinal;

import java.awt.*;

//interface for maze building objects, i think we can exploit for array use later in coll-detec
//we could also require collision detection here so it'll be implemented sa maze rectangles

public interface MazeBlock {

    void draw (Graphics2D g2d);
    boolean isColliding(Adam adam);
    double getSize();

}
