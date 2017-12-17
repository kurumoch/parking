package drawing;

import JPanels.Surface;
import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class DrawLines extends JComponent {
    private Graphics graphics;
    private Surface surface;
    private Controller controller;
    private Rectangle[][] rectangles;
    private int width;
    private int height;
    private int xSize;
    private int ySize;

    public DrawLines(Surface surface, Controller controller) {
        this.surface = surface;
        this.graphics = surface.getGraphics();
        this.controller = controller;
        this.width = surface.getWidth() - 60;
        this.height = surface.getHeight() - 50;
        this.xSize = controller.getxSize() + 2;
        this.ySize = controller.getySize() + 2;
        this.rectangles = new Rectangle[xSize][ySize];
    }

    public void draw() {
        int x = 30;
        int y = 30;
        graphics.setColor(Color.RED);
        int dx = width / xSize;
        int dy = height / ySize;
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                graphics.drawRect(x, y, dx, dy);
                rectangles[j][i] = new Rectangle(x, y, dx, dy);
                x += dx;
            }
            x = 30;
            y += dy;
        }
        controller.setRectangles(rectangles);
    }
}
