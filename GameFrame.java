

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame {

    private GameCanvas dc;

    public void setUpGUI(){
        int w = 843;
        int h = 675;

        JFrame f = new JFrame();

        dc = new GameCanvas(w,h);
        dc.startAnimation();
        f.setTitle("Final Project - Abarico, Michelle Kim - Chan, Caryl Rae 221503");

        Container cp = f.getContentPane();
        JPanel p = new JPanel();
        cp.add(dc, BorderLayout.CENTER); // game at center

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public void addKeyBindings() {

        dc.setFocusable(true);

        ActionMap am = dc.getActionMap();
        InputMap im = dc.getInputMap();

        am.put("up", new MoveAction("up"));
        am.put("down", new MoveAction("down"));
        am.put("left", new MoveAction("left"));
        am.put("right", new MoveAction("right"));
        am.put("stop", new MoveAction(""));

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "stop");
    }

    private class MoveAction extends AbstractAction {
        private String direction;

        public MoveAction(String dir) {
            direction = dir;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            dc.getAdam().setDirection(direction);
        }
    }
}