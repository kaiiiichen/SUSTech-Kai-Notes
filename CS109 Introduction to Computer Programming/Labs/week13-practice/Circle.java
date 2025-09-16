

import java.awt.*;
public class Circle extends Shape {

    private int radius = 30;

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
    public int getSize(){
        return radius * 2;
    }


}
