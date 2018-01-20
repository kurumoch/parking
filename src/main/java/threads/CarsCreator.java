package threads;

import controllers.Controller;
import models.Vehicle;

public class CarsCreator extends Thread {
    Controller controller;
    public CarsCreator(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            int x = controller.getxSize()-1;
            int y = controller.getySize()-1;
            controller.vehicles.add(new Vehicle(controller.getSurface(),controller.getRectangles()[x][y].x,controller.getRectangles()[x][y].y,controller.getSurface().getWidth(),controller.getSurface().getHeight(), controller));
            try {
                //wait(10000);
                Thread.sleep(5000);
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
