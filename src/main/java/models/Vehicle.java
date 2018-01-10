package models;

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

    static final int SPEED = 3;

    int maxX, maxY;

    int speedX = SPEED, speedY = SPEED;

    JPanel panel;

    public Vehicle(JPanel panel, int x, int y, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
        this.panel = panel;
        try {
            car = ImageIO.read(new File("car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setParkingTime(int secs) {
        this.parkingTime = secs;
    }

    private void setType(VehicleType type) {
        this.type = type;
    }

    private void setIsParking(boolean isParking) {
        this.isParking = isParking;
    }

    private int getParkingTime() {
        return parkingTime;
    }

    private VehicleType getType() {
        return type;
    }

    private boolean isParking() {
        return isParking;
    }

    public void setBounds(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void move() {
        x += speedX;
        y += speedY;
        if (x < 0) {
            speedX = -speedX;
            x = 0;
        }
        if (y < 0) {
            speedY = -speedY;
            y = 0;
        }
        if (x+SIZE>maxX) { speedX=-speedX; x=maxX-SIZE; }
        if (y+SIZE>maxY) { speedY=-speedY; y=maxY-SIZE; }
    }

    public void draw(Graphics g) {
        //image.paintIcon(panel, g, x, y); - commented out because I don't have an ImageIcon
        g.drawImage(car, x, y, panel);

//        g.drawOval(x, y, SIZE, SIZE);
//        g.drawRect(x+5,y+5,SIZE,SIZE);
//        g.fillRect(x+5,y+5,SIZE,SIZE);
    }

}
