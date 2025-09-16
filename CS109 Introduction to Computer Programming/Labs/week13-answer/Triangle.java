import java.awt.*;

public class Triangle extends Shape {

    private int edge = 50;
    private final int EDGE = 50;
    public Triangle() {
        super();
        this.color = Color.BLUE;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        int[] xPoints = {x, x + edge / 2, x + edge};
        int[] yPoints = {y + edge, y, y + edge};
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public String getShapeName() {
        return "Triangle";
    }

    @Override
    public void setCenter(int width, int height) {
        this.setPosition(
                (width - getSize()) / 2,
                (height - getSize()) / 2
        );
    }

    @Override
    public void shift() {
        this.edge = (int)(this.edge * 0.9);
    }

    @Override
    public void reset() {
        this.edge = EDGE;
    }

    @Override
    public int getSize() {
        return edge;
    }

}