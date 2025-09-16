package swings1;

import java.awt.*;

public class Shape {
    protected Color color;
    protected int x;
    protected int y;

    public Shape() {
        // Default color is black
        this.color = Color.BLACK;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Base method for drawing
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        // Default shape is a square
        g2d.fillRect(x, y, 50, 50);
    }

    // Base method for getting shape name
    public String getShapeName() {
        return "Shape";
    }
}