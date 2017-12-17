package drawing;

import controllers.Controller;

import java.awt.*;

public class DrawDefaultTiles {
    Graphics graphics;
    Controller controller;
    public DrawDefaultTiles(Graphics graphics, Controller controller) {
        this.graphics = graphics;
        this.controller = controller;
    }
    public void drawDefaultRoad() {
        Graphics2D graphics2D = (Graphics2D) graphics;

        for(int i = 0;i<controller.getxSize()+2;i++) {
            graphics2D.setColor(Color.GRAY);
            Rectangle r = controller.getRectangles()[i][controller.getySize()+1];
            graphics2D.fillRect(r.x+1,r.y+1,r.width-1,r.height-1);
            graphics2D.setColor(Color.GREEN);
            r = controller.getRectangles()[i][0];
            graphics2D.fillRect(r.x+1,r.y+1,r.width-1,r.height-1);
        }
    }
    public void drawDefaultLawn() {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN);
        for(int i =0;i<controller.getySize()+1;i++) {
            Rectangle r = controller.getRectangles()[controller.getxSize()+1][i];
            graphics2D.fillRect(r.x+1,r.y+1,r.width-1,r.height-1);
            r = controller.getRectangles()[0][i];
            graphics2D.fillRect(r.x+1,r.y+1,r.width-1,r.height-1);
        }
    }
}
