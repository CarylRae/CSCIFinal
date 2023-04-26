package CSCIFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame {

    private GameCanvas dc;

    public void setUpGUI(){
        int w = 843;
        int h = 675;

        JFrame f = new JFrame();

        dc = new GameCanvas(w,h);
        f.setTitle("Final Project - Abarico, Michelle Kim - Chan, Caryl Rae 221503");

        Container cp = f.getContentPane();
        cp.add(dc, BorderLayout.CENTER); // game at center

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}