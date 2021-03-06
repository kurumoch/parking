package JPanels;


import controllers.Controller;
import models.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CopyOnWriteArrayList;

public class Surface extends JPanel implements ActionListener {
    private Controller controller;
    private CopyOnWriteArrayList<Vehicle> vehicles;
    public Surface(Controller controller) {
        super();
        this.controller = controller;
        this.setDoubleBuffered(true);
        setPreferredSize(new Dimension(400, 300));
        for (Vehicle vehicle : controller.vehicles) {
            vehicle.setBounds(500, 500);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        int x = 30;
        int y = 30;
        g.setColor(Color.BLACK);
        int dy = (getWidth() - 110) / controller.getTILES_X();
        int dx = (getHeight() - 50) / controller.getTILES_Y();
        for (int i = 0; i < controller.getTILES_Y(); i++) {
            for (int j = 0; j < controller.getTILES_X(); j++) {
                g.drawRect(y, x, dy, dx);
                x += dx;
            }
            x = 30;
            y += dy;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(controller.getTiles()!=null) {
            for (int i = 0; i < controller.getTILES_X(); i++) {
                for (int j = 0; j < controller.getTILES_Y(); j++) {
                    switch (controller.getTiles()[i][j]) {
                        case LAWN:
                            graphics2D.setColor(Color.GREEN);
                            break;
                        case PARKING:
                            graphics2D.setColor(Color.ORANGE);
                            break;
                        case PARK_ROAD:
                            graphics2D.setColor(Color.gray);
                            break;
                        case ROAD:
                            graphics2D.setColor(Color.darkGray);
                            break;
                        case DOUBLE_PARKING:
                            graphics2D.setColor(Color.RED);
                            break;
                    }
                    Rectangle r = controller.getRectangles()[i][j];
                    graphics2D.fillRect(r.x + 1, r.y + 1, r.width - 1, r.height - 1);
                }
            }
            for (Vehicle vehicle : controller.vehicles) {
                vehicle.draw(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Vehicle vehicle : controller.vehicles) {
            if(!vehicle.isParking)
                vehicle.move();
        }
        repaint();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
