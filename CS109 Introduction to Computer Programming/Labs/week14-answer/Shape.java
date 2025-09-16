


public abstract class Shape implements Comparable<Shape>, ColorDraw {
    protected double x;
    protected double y;
    protected ShapeColor color = ShapeColor.GRAY;
    private static int screenSize = 10;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {

        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ShapeColor getColor() {
        return color;
    }

    public void setColor(ShapeColor color) {
        this.color = color;
    }

    public static int getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(int screenSize) {
        Shape.screenSize = screenSize;
    }


    @Override
    public String toString() {
        return " x=" + x +
                ", y=" + y +
                ", color=" + color;
    }


    abstract public void draw();
    abstract public double area();
}
