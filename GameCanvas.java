package CSCIFinal;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GameCanvas extends JComponent{

    private int width;
    private int height;

    public GameCanvas(int w, int h) {
        width = w;
        height = h;
        setPreferredSize(new Dimension(width,height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

    }
}


