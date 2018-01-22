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
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static models.Direction.RIGHT;

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

        isCreated = true;
    }

    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void move() {

//        int[] arr = controller.getTilesNumber(Math.round(x), Math.round(y));
        if( path.hasNext()) {
            point = path.next();
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
                            isParking = false;
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
        t.translate(x, y);
        ((Graphics2D) g).drawImage(car, t, panel);

    }

}
