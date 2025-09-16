

import java.awt.*;

public class Triangle extends Shape {

    private final int edge = 50;

    public Triangle() {
        super();
        this.color = Color.BLUE;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        int[] xPoints = {x, x + edge/2, x + edge};
        int[] yPoints = {y + edge, y, y + edge};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String getShapeName() {
        return "Triangle";
    }

    @Override
    public int getSize(){
        return edge;
    }

}