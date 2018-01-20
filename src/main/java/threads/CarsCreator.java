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
            controller.vehicles.add(new Vehicle(controller.getSurface(),30,30,400,400));
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
