

import java.awt.*;

public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double x, double y) {
        super(x, y);

    }

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                super.toString() +
                "}\n";
    }

    public void draw() {
        StdDraw.setPenColor(color.getColor());
        StdDraw.filledRectangle(x, y, this.width / 2, this.height / 2);
    }

    @Override
    public int compareTo(Shape o) {
        if (this.area() < o.area()) {
            return 1;
        } else if (this.area() > o.area()) {
            return -1;
        } else{
            if (this.x < o.x){
                return -1;
            }else if (this.x > o.x){
                return 1;
            }
        }
        return 0;
    }

    public double area() {
        return this.width * this.height;
    }

    @Override
    public void customizedColor(ColorScheme colorScheme, int index) {
        Color[] colorList = colorScheme.getColorScheme();
        if (index < 0) {
            index = 0;
        }
        if (index >= colorList.length) {
            index = index % colorList.length;
        }
        StdDraw.setPenColor(colorList[index]);
        StdDraw.filledRectangle(x, y, this.width / 2, this.height / 2);
    }
}
