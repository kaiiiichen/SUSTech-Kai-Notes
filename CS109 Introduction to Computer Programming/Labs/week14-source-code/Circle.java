

public class Circle extends Shape {
    private double radius;//圆形独有的属性
    static final int DEFAULT_RADIUS = 5;//圆形独有的属性


    public Circle(double radius, double x, double y) {
        super(x, y);
        this.radius = radius;
    }

    public Circle(double radius) {
        super(0, 0);
        this.radius = radius;
    }

    public Circle(double x, double y) {
        super(x, y);
        this.radius = DEFAULT_RADIUS;
    }


    @Override
    public String toString() {
        return "Circle{" +
                "radius=" +
                super.toString() +
                "}";
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    public void draw() {
        StdDraw.setPenColor(super.getColor().getColor());
        StdDraw.filledCircle(super.getX(), super.getY(), radius);
    }

    @Override
    public double area() {
        return radius * radius * Math.PI;
    }
}
