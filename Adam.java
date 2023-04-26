package CSCIFinal;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Adam {

    private double x;
    private double y;
    private double size;
    private boolean up, down, left, right;

    public Adam(double x, double y, double size){
            this.x = x;
            this.y = y;
            this.size = size;
            this.up = false;
            this.down = false;
            this.left = false;
            this.right = false;

        }

        public void draw(Graphics2D g2d) {

            Rectangle2D.Double square = new Rectangle2D.Double(x,y,size,size);

            g2d.setColor(Color.green);
            g2d.fill(square);

        }

        public void moveA(int speed){
            speed = 4;

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
        public double getWidth(){
            return size;
        }

        public double getHeight(){
            return size;
        }


        public void setDirection(String dir) {
            String direc = dir;
            boolean exit = false;
            switch (dir){
                case "up":
                    
                        up = true;
                        down = false;
                        left = false;
                        right = false;
                       
                        for(MazeBlock block : canvasMaze){
                            if(block.isColliding(adam)){
                                exit = true;
                                break;
                            }
                        }

                        if (exit == true)
                            break;
                    
                   
                case "down":
                    up = false;
                    down = true;
                    left = false;
                    right = false;

                    for(MazeBlock block : canvasMaze){
                        if(block.isColliding(adam)){
                            exit = true;
                            break;
                        }
                    }

                    if (exit == true)
                        break;

                case "left":
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                    for(MazeBlock block : canvasMaze){
                        if(block.isColliding(adam)){
                            exit = true;
                            break;
                        }
                    }

                    if (exit == true)
                        break;
                case "right":
                    up = false;
                    down = false;
                    left = false;
                    right = true;

                    for(MazeBlock block : canvasMaze){
                        if(block.isColliding(adam)){
                            exit = true;
                            break;
                        }
                    }

                    if (exit == true)
                        break;
                default:
                    up = false;
                    down = false;
                    left = false;
                    right = false;
            }
        }

    }




