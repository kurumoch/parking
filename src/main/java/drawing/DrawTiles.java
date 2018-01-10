package drawing;

import controllers.Controller;
import models.TileType;

import javax.swing.*;
import java.awt.*;

public class DrawTiles {
    Graphics graphics;
    Controller controller;
    public DrawTiles(JPanel surface, Controller controller) {
        this.graphics = surface.getGraphics();
        this.controller = controller;
    }

    public void draw(TileType[][] tiles) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        for (int i = 0; i < controller.getxSize(); i++) {
            for (int j = 0; j < controller.getySize(); j++) {
                switch (tiles[i][j]) {
                    case LAWN:
                        graphics2D.setColor(Color.LIGHT_GRAY);
                        break;
                    case PARKING:
                        graphics2D.setColor(Color.ORANGE);
                        break;
                    case PARK_ROAD:
                        graphics2D.setColor(Color.gray);
                        break;
                    case ROAD:
                        graphics2D.setColor(Color.darkGray);
                        break;
                    case DOUBLE_PARKING:
                        graphics2D.setColor(Color.RED);
                        break;
                }
                Rectangle r = controller.getRectangles()[i][j];
                graphics2D.fillRect(r.x+1,r.y+1,r.width-1,r.height-1);
            }
        }
    }
}
