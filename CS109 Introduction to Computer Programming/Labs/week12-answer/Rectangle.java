package swings1.answers;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle() {
        super();
        this.color = Color.GREEN;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x, y, 80, 40);
    }

    @Override
    public String getShapeName() {
        return "Rectangle";
    }
}
