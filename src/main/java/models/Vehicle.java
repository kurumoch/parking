package models;

import controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Vehicle implements Serializable{

    private VehicleType type;
    private int parkingTime;
    public boolean isParking;
    public boolean isCreated;
    private float x, y;
    private BufferedImage car;
    static final int SIZE = 35;
    static final float SPEED = -28f;
    int maxX, maxY;
    private Controller controller;
    float speedX = SPEED, speedY = -25f;
    private TileType[][] tiles;
    private Rectangle[][] rectangles;
    JPanel panel;

    public Path getPath() {
        return path;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    Path path;
    Pair<Pair<Integer, Integer>, Direction> point;
    Pair<Pair<Integer, Integer>, Direction> oldPoint;

    public Vehicle(Controller controller, int parkingTime, Path path) {
        this.parkingTime = parkingTime;
        JPanel panel = controller.getSurface();
        Rectangle borderRect = controller.getRectangles()[controller.getTILES_X()-1][controller.getTILES_Y()-1];
        x=borderRect.x;
        y=borderRect.y;
        maxX=panel.getWidth();
        maxY=panel.getHeight();
        this.controller = controller;
        tiles = controller.getTiles();
        this.path = path;
        point = path.next();
        this.rectangles = controller.getRectangles();

        isCreated = true;
    }

    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void move() {

//        int[] arr = controller.getTilesNumber(Math.round(x), Math.round(y));
        if(oldPoint != point)
            switch (point.getSecond()) {
                case DOWN:
                    y -= speedY;
                    break;
                case LEFT:
                    x += speedX;
                    break;
                case RIGHT:
                    x -= speedX;
                    break;
                case UP:
                    y += speedY;
                    break;
                case PARK:
                    isParking = true;

        }
        if( path.hasNext()) {
                oldPoint = point;
            point = path.next();
        }
        else try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



    public void draw(Graphics g) {
        try {
            switch (point.getSecond()) {
                case DOWN:
                    car = ImageIO.read(new File("card1.png"));
                    break;
                case LEFT:
                    car = ImageIO.read(new File("carl1.png"));
                    break;
                case RIGHT:
                    car = ImageIO.read(new File("carr1.png"));
                    break;
                case UP:
                    car = ImageIO.read(new File("caru1.png"));
                    break;
                case PARK:
                    new Thread(() -> {
                        try {
                            Thread.sleep(parkingTime);
                            int hours = parkingTime / 360/6;
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
                            isParking = false;
                            controller.getFreeParkingSpace().add(point.getFirst());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        AffineTransform t = new AffineTransform();
        t.scale(1,1);
        t.translate(x-controller.getRectangles()[0][0].width*getCoeff(controller.widthh), y);
        ((Graphics2D) g).drawImage(car, t, panel);

    }

    private int getCoeff(int i){
        int a = 42;
        switch (i){
            case 10: a = 0; break;
            case 9: a = 0; break;
            case 8: a = 1; break;
            case 7: a = 1; break;
            case 6: a= 2; break;
            case 5: a=2;break;
            case 4: a=3;break;
            case 3: a=3;break;
            case 2: a=4;break;
            case 1: a=4; break;
        }
        return a;
    }


}
