import java.awt.*;
import java.util.Random;

public class Circle extends Shape {

    private int radius = 30;
    private final Color COLOR = color.RED;

    public Circle() {
        super();
        this.color = Color.RED;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x, y, radius * 2, radius * 2);
    }

    @Override
    public String getShapeName() {
        return "Circle";
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
        this.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    @Override
    public void reset() {
        this.color = COLOR;
    }

    @Override
    public int getSize() {
        return radius * 2;
    }


}
