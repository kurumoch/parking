package models;

import JPanels.Surface;
import controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vehicle {

    private VehicleType type;
    private int parkingTime;
    private boolean isParking;
    private int x, y;
    private BufferedImage car;
    static final int SIZE = 35;
    static final int SPEED = -3;
    private String direction = "right";
    int maxX, maxY;
    private Controller controller;
    int speedX = SPEED, speedY = SPEED;
    private TileType[][] tiles;
    private Rectangle[][] rectangles;
    JPanel panel;
    Path path;


    public Vehicle(Controller controller, int parkingTime, Path path) {
        JPanel panel = controller.getSurface();
        Rectangle borderRect = controller.getRectangles()[controller.getxSize()-1][controller.getySize()-1];
        x=borderRect.x;
        y=borderRect.y;
        maxX=panel.getWidth();
        maxY=panel.getHeight();
        this.controller = controller;
        tiles = controller.getTiles();
        this.rectangles = controller.getRectangles();
        try {
            car = ImageIO.read(new File("car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void move() {

        int[] arr = controller.getTilesNumber(x, y);
        if ((arr != null) && (tiles[arr[0]][arr[1]].equals(TileType.PARK_ROAD)) && (x == rectangles[arr[0]][arr[1]+1].x)) {
            y -= 10;
            x-=20;
        }
        x += speedX;
        //  y += speedY;
        if (x < 0) {
            speedX = -speedX;
            x = 0;
        }
//        if (y < 0) {
//            speedY = -speedY;
//            y = 0;
//        }
        if (x + SIZE > maxX) {
            speedX = -speedX;
            x = maxX - SIZE;
        }
//        if (y + SIZE > maxY) {
//            speedY = -speedY;
//            y = maxY - SIZE;
//        }


    }

    public void draw(Graphics g) {
        g.drawImage(car, x, y, panel);

    }

}
