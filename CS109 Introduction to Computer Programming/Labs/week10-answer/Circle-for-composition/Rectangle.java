public class Rectangle {
    private Position position;
    private double width;
    private double length;

    private static int count = 0;
    private int id;

    public int getId() {
        return id;
    }

    public Rectangle(Position position, double width, double length) {
        this.position = position;
        this.width = width;
        this.length = length;
        id = ++count;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Rectangle.count = count;
    }

    public String toString() {
        return String.format("Rectangle #%d: width = %.2f height = %.2f position=%s",
                id, width, length, position);
    }
}
