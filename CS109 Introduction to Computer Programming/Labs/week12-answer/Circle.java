package swings1.answers;

import java.awt.*;

public class Circle extends Shape {
    public Circle() {
        this.color = Color.RED;
    }

    // Override the draw method
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x, y, 50, 50);
    }

    // Override the shape name
    @Override
    public String getShapeName() {
        return "Circle";
    }
}