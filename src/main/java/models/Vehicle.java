package models;

import JPanels.Surface;
import controllers.Controller;
import org.apache.commons.math3.util.Pair;

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
    static final float SPEED = -10f;
    int maxX, maxY;
    private Controller controller;
    float speedX = SPEED, speedY = SPEED;
    private TileType[][] tiles;
    private Rectangle[][] rectangles;
    JPanel panel;
    Path path;
    Pair<Pair<Integer, Integer>, Direction> point;


    public Vehicle(Controller controller, int parkingTime, Path path) {
        this.parkingTime = parkingTime;
        JPanel panel = controller.getSurface();
        Rectangle borderRect = controller.getRectangles()[controller.getxSize()-1][controller.getySize()-1];
        x=borderRect.x;
        y=borderRect.y;
        maxX=panel.getWidth();
        maxY=panel.getHeight();
        this.controller = controller;
        tiles = controller.getTiles();
        this.path = path;
        point = path.next();
        this.rectangles = controller.getRectangles();
        try {
            car = ImageIO.read(new File("l_car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        isCreated = true;
    }

    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void move() {
        int[] arr = controller.getTilesNumber(Math.round(x), Math.round(y));
        if(arr[0] == point.getFirst().getFirst() && arr[1] == point.getFirst().getSecond()) {
            point = path.next();
        }
        switch (point.getSecond()){
            case DOWN: y-=speedY; break;
            case LEFT: x+=speedX; break;
            case RIGHT: x-=speedX; break;
            case UP:y+=speedY; break;
            case PARK:
                try {
                    isParking = true;
                    Thread.currentThread().sleep(parkingTime);
                    isParking = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

//
//        if ((arr != null) && (tiles[arr[0]][arr[1]].equals(TileType.PARK_ROAD)) && (x == rectangles[arr[0]][arr[1]+1].x)) {
//            y -= 10;
//            x-=20;
//        }
//        x += speedX;
//        //  y += speedY;
//        if (x < 0) {
//            speedX = -speedX;
//            x = 0;
//        }
////        if (y < 0) {
////            speedY = -speedY;
////            y = 0;
////        }
//        if (x + SIZE > maxX) {
//            speedX = -speedX;
//            x = maxX - SIZE;
//        }
////        if (y + SIZE > maxY) {
////            speedY = -speedY;
////            y = maxY - SIZE;
////        }


    }

    public void draw(Graphics g) {
        AffineTransform t = new AffineTransform();
        t.translate(x, y);
        t.scale(1, 1);
        ((Graphics2D) g).drawImage(car, t, panel);

    }

}
