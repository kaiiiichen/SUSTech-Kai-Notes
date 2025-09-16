import java.util.Random;

public class Circle {
    private double radius;
    private Position position;
    private int id;

    private static int count = 0;

    public Circle() {
        this.id = ++count;
        this.position = new Position(0, 0);
    }

    public Circle(double radius, double x, double y) {
        this.id = ++count;
        this.radius = radius;
        this.position = new Position(x, y);
    }

    public Circle(double radius, Position position) {
        this.id = ++count;
        this.radius = radius;
        this.position = position;
    }

    public double distanceToOrigin() {
        return Math.sqrt(Math.pow(position.getX(), 2) + Math.pow(position.getY(), 2));
    }

    public double area() {
        return radius * radius * Math.PI;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String toString() {
        return String.format("Circle #%d: radius = %.2f, position = %s", id, radius, position);
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Circle.count = count;
    }
}


