import java.awt.*;

/**
 * 抽象类与非抽象类的区别：
 * 1. 抽象类里可以定义抽象方法，普通类不可以。
 * 2. 抽象类不能用本身实例化。 Shape s = new Shape(); //ERROR
 */
public abstract class Shape {
    int x;
    int y;
    Color color;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //抽象方法：有些方法不需要在当前类里实现，可以下推到子类去实现。
    public abstract int getSize();

    public abstract void draw(Graphics2D g2d);

    public abstract String getShapeName();

    public abstract void setCenter(int width, int height);

    public abstract  void shift();
    public abstract  void reset();


}