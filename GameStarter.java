/**
This is a template for a Java file.
@author Caryl Rae T. Chan (221503) & Michelle Kim Abarico (220017)
@version May 14, 2023
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

The code below is the GameStarter that allows the Players to connect to the server and start the game.
Included is also the music that plays in the background.
*/

public class GameStarter { //main for players
    public static void main(String[] args) {

        GameFrame gf = new GameFrame(843,675);
        gf.connectToServer();
        gf.setUpGUI();
        gf.addKeyBindings();
        gf.playMusic("zong_forest.wav");
    
    }

}