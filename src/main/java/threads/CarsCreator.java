package threads;

import controllers.Controller;
import models.DetermineDistribution;
import models.PathGenerator;
import models.Vehicle;
import org.apache.commons.math3.distribution.*;
import org.apache.commons.math3.random.RandomGenerator;

public class CarsCreator extends Thread {
    Controller controller;

    AbstractRealDistribution distributionCars;
    AbstractRealDistribution distributionTime;


    public CarsCreator(Controller controller) {
        this.controller = controller;
        switch (controller.getTypeOfThreadTimeOnParking()) {
            case "Детерминированный":
                distributionTime = new DetermineDistribution(controller.getIntervalTime());
                break;
            case "Нормальный":
                distributionTime = new NormalDistribution(controller.getMxTime(),controller.getDxTime());
                break;
            case "Показательный":
                distributionTime = new ExponentialDistribution(controller.getLambdaTime());
                break;
            case "Равномерный":
                distributionTime = new UniformRealDistribution(controller.getT1Time(), controller.getT2Time());
                break;
        }
        switch (controller.getTypeOfThreadOfCars()) {
            case "Детерминированный":
                distributionCars = new DetermineDistribution(controller.getIntervalCars());
                break;
            case "Нормальный":
                distributionCars = new NormalDistribution(controller.getMxCars(),controller.getDxCars());
                break;
            case "Показательный":
                distributionCars = new ExponentialDistribution(controller.getLambdaCars());
                break;
            case "Равномерный":
                distributionCars = new UniformRealDistribution(controller.getT1Cars(), controller.getT2Cars());
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            int parkTime =  Math.toIntExact(Math.round(distributionTime.sample()));
            System.out.println(Math.abs(parkTime));
            controller.vehicles.add(new Vehicle(controller,parkTime, PathGenerator.generate()));
            try {
              int carTime =  Math.toIntExact(Math.round(distributionCars.sample()));
                System.out.println(Math.abs(carTime));
                Thread.sleep(carTime);
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
    }


}
