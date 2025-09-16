import java.util.ArrayList;

/**
 * 创建多个图形，每种图形也会创建多次。 对于这些图形，我们要获取它们的size与name?
 * <p>
 * 多态：
 * 可以由子类的对象去实例化父类的引用。 Shape s1 = new Circle();
 */
public class Exercise {
    public static void main(String[] args) {
        Shape c1 = new Circle();
        Shape c2 = new Circle();
        Shape t1 = new Triangle();
        Shape t2 = new Triangle();
        Shape r1 = new Rectangle(20, 40);
        //按照父类的引用去创建一个List，那么这个List中，可以存放所有其子类的对象
//        ArrayList<Circle> circles = new ArrayList<>();
//        ArrayList<Rectangle> rectangles = new ArrayList<>();
//        ArrayList<Triangle> triangles = new ArrayList<>();
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(c1);
        shapes.add(c2);
        shapes.add(r1);
        shapes.add(t1);
        shapes.add(t2);
// 如果用子类去实例化父类的引用，用父类引用去调用同名方法时，实际执行的是写在子类里的方法段
        //在父类必须保留方法签名
        for (Shape s : shapes) {
            System.out.println(s.getSize());
            System.out.println(s.getShapeName());
        }

//        for (Circle c: circles) {
//            System.out.println(c.getSize());
//            System.out.println(c.getShapeName());
//        }
//        for(Rectangle r : rectangles){
//            System.out.println(r.getSize());
//            System.out.println(r.getShapeName());
//        }
//        for(Triangle t:triangles){
//            System.out.println(t.getSize());
//            System.out.println(t.getShapeName());
//        }
    }
}
