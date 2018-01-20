package drawing;

import JPanels.Surface;
import controllers.Controller;
import models.Car2D;

import javax.swing.*;
import java.awt.*;

public class DrawLines extends JComponent {
    private Graphics graphics;
    private JPanel surface;
    private Controller controller;
    private Rectangle[][] rectangles;
    private int width;
    private int height;
    private int xSize;
    private int ySize;

    public DrawLines(JPanel surface, Controller controller) {
        this.surface = surface;
        this.graphics = surface.getGraphics();
        this.controller = controller;
        this.width = surface.getWidth() - 110;
        this.height = surface.getHeight() - 50;
        this.xSize = controller.getxSize();
        this.ySize = controller.getySize();
        this.rectangles = new Rectangle[xSize][ySize];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void draw() {
        int x = 30;
        int y = 30;
        graphics.setColor(Color.BLACK);
        int dy = width / xSize;
        int dx = height / ySize;
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
             //   graphics.drawRect(y, x, dy, dx);
                rectangles[j][i] = new Rectangle(y, x, dy, dx);
                x += dx;
            }
            x = 30;
            y += dy;
        }
        controller.setRectangles(rectangles);
    }
}
