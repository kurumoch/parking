package threads;

import controllers.Controller;
import models.DetermineDistribution;
import models.PathGenerator;
import models.Vehicle;
import org.apache.commons.math3.distribution.*;
import org.apache.commons.math3.random.RandomGenerator;

public class CarsCreator extends Thread {
    private Controller controller;
    public boolean stop;

    private AbstractRealDistribution distributionCars;
    private AbstractRealDistribution distributionTime;


    public CarsCreator(Controller controller) {
        this.controller = controller;
        stop = false;
        update();
    }


    public void update(){
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
        PathGenerator gena = new PathGenerator(controller);
        while (true) {
            if(controller.vehicles.size() > 200)
            {
              Vehicle v=  controller.vehicles.remove(0);
              v = null;
            }
            int parkTime =  Math.toIntExact(Math.round(distributionTime.sample()));
            if(controller.getProbOfArrivalToParking() < Math.random() &&controller.getFreeParkingSpace().size()!=0) {
                controller.vehicles.add(new Vehicle(controller, parkTime, gena.generate()));
                int hours = parkTime / 360/6;
                int profit = 0;
                if(hours < 1) {
                    profit = controller.getCostOfOneHour();
                }
                else if (hours < 4) {
                    profit = controller.getCostToThreeHours();
                }
                else {
                    profit = controller.getCostMoreThreeHours();
                }
                controller.setMoney(controller.getMoney() + profit);
            }
            else controller.vehicles.add(new Vehicle(controller, parkTime, gena.generateNoPark()));
            try {
              int carTime =  Math.toIntExact(Math.round(distributionCars.sample()));
                Thread.sleep(carTime);
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
    }


}
