
public class Rectangle extends Shape {

    private double width;//Rectangle 独有的属性
    private double height;//Rectangle 独有的属性

    /**
     * 子类继承父类，在构造方法里，首先要调用父类的构造方法
     * 父类Shape 只有一个构造方法 Shape(int x, int y),所以子类每个构造方法必须调用它
     * 父类用 super关键字表示
     * 子类继承父类时，创建子类的对象需要预留父类属性的空间，因此，子类对象包含 x,y,color以及width,height的存储空间
     *
     * @param x
     * @param y
     */
    public Rectangle(double x, double y) {
        super(x, y);// 第一步，调用父类的构造方法
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

    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                super.toString() +
                "}";
    }

    public void draw() {
        StdDraw.setPenColor(super.getColor().getColor());// color 用super.getColor() 替换
        StdDraw.filledRectangle(super.getX(), super.getY(), this.width / 2, this.height / 2);
    }

    @Override
    public double area() {
        return width * height;
    }

}
