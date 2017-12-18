package drawing;

import java.awt.*;

public class DrawRect {
    Graphics graphics;

    public DrawRect(Graphics graphics) {
        this.graphics = graphics;
    }

    public void drawRect(Rectangle rectangle) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.RED);
        graphics2D.fillRect(rectangle.x + 1, rectangle.y + 1, rectangle.width-1, rectangle.height-1);
    }
}
