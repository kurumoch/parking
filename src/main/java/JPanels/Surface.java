package JPanels;


import controllers.Controller;
import models.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Surface extends JPanel implements ActionListener {
    private Controller controller;
    private ArrayList<Vehicle> vehicles;

    public Surface(Controller controller) {
        super();
        this.controller = controller;
        this.vehicles = controller.vehicles;
        this.setDoubleBuffered(true);
        setPreferredSize(new Dimension(400, 300));
        vehicles.add(new Vehicle(this,10,10,500,500));
        vehicles.add(new Vehicle(this,30,30,500,500));
        for (Vehicle vehicle : vehicles) {
            vehicle.setBounds(500, 500);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(50, 50, 50, 50);
        for (Vehicle vehicle : vehicles) {
            vehicle.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
        repaint();
    }
}
