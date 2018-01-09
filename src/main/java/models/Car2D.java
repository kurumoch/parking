package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

/**
 * Created by denis on 09.01.2018.
 */
public class Car2D extends JComponent implements ActionListener {
    Timer t = new Timer(1, this);
    double x=100, y=100, vx=0.1, vy=0.1;

    public void paintComponent(Graphics g){
//        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        Rectangle2D rectangle2D = new Rectangle2D.Double(x,y,100,100);
        graphics2D.fill(rectangle2D);

        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x+=vx;
        y+=vy;
        repaint();
    }
}
