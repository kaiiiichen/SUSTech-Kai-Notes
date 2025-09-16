

import java.awt.*;

public abstract class Shape {
    int x;
    int y;
    Color color;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract int getSize();

    public abstract void draw(Graphics2D g2d);

    public abstract String getShapeName();


}