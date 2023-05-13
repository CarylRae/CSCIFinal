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

The code below is the player class that is extended by the adam and eve classes. 
It contains the methods such as move, draw, collision, and getters and setters that are used by the adam 
and eve classes to move around the maze.
*/

import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.Random;

public class Player implements MazeBlock { //superclass of adam & snake
    protected double x;
    protected double y;
    protected int size;
    protected boolean up, down, left, right;
    protected boolean speedBoolean, normalBoolean, randomBoolean;
    protected String i;

    public ImageIcon img;

    private Rectangle2D.Double s1;

    public Player(double x, double y, int size, String i){
        this.x = x;
        this.y = y;
        this.size = size;
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.i = i;

        img = new ImageIcon(this.getClass().getResource(this.i));
    }

    public void move(int speed){

        Random num = new Random();
        int random = num.nextInt( 4 );

        if(speedBoolean) {
            speed = speed*3;
        }
        if(normalBoolean){
            speed = 1;
        }
        if(randomBoolean){
            speed = speed*random;
            
        }
        
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

    public double getSize(){
        return size;
    }

    public double getHeight(){
        return size;
    }

    public double getWidth(){
        return size;
    }

    public boolean getUp(){
        return up;
    }
    public boolean getDown(){
        return down;
    }
    public boolean getRight(){
        return right;
    }
    public boolean getLeft(){
        return left;
    }
    public void setX(double n){
        x=n;
    }

    public void setY(double n){
        y=n;
    }

    public void setDirection(String dir){ 
        
        if(dir.equals("up")) {
            up = true;
            down = false;
            left = false;
            right = false;

        } else if (dir.equals("down")) {
            up = false;
            down = true;
            left = false;
            right = false;

        } else if (dir.equals("left")) {
            up = false;
            down = false;
            left = true;
            right = false;

        } else if (dir.equals("right")) {
            up = false;
            down = false;
            left = false;
            right = true;

        } else if (dir.equals("speedUp")) {
            speedBoolean = true;
            normalBoolean = false;
            randomBoolean = false;
            
        } else if (dir.equals("normalSpeed")) {
            speedBoolean = false;
            normalBoolean = true;
            randomBoolean = false;

        } else if (dir.equals("messySpeed")) {
            speedBoolean = false;
            normalBoolean = false;
            randomBoolean = true;
        }

        
        else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }

    public boolean isColliding(MazeBlock other) {
        return!(this.x + this.size <= other.getX() ||
                this.x >= other.getX() + other.getWidth() ||
                this.y + this.size <= other.getY() ||
                this.y >= other.getY() + other.getHeight());
    }

    public void draw(Graphics2D g2d) 
    {

        s1 = new Rectangle2D.Double(x,y,size,size);
        g2d.setColor(Color.GREEN);
        g2d.fill(s1);

    }

    public ImageIcon getCharacterImage(){
        return img;
    }

}
