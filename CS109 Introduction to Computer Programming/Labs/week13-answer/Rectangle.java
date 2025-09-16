import java.awt.*;

public class Rectangle extends Shape {

    private int width;
    private int height;

    private int offset;

    private final int WIDTH;
    private final int HEIGHT;

    private final boolean isSquare;

    public Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
        this.offset = 0;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.isSquare = height == width;
        if (width != height) {
            this.color = Color.GREEN;
        }

    }

    @Override
    public int getSize() {
        return width;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);
    }

    @Override
    public String getShapeName() {
        return "Rectangle";
    }

    @Override
    public void setCenter(int width, int height) {
        this.setPosition((width - getWidth() - offset) / 2,
                (height - getHeight()) / 2);
    }

    @Override
    public void shift() {
        if (isSquare) {
            this.width = (int) (this.width * 1.1);
            this.height = (int) (this.height * 1.1);
        } else {
            offset += 10;
        }
    }

    @Override
    public void reset() {
        if (isSquare) {
            this.width = WIDTH;
            this.height = HEIGHT;
        } else {
            offset = 0;
        }
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}