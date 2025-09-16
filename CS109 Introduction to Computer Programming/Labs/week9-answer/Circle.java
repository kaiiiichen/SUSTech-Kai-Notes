import java.util.Random;

public class Circle {
    private double radius;
    private double x;
    private double y;

    public Circle() {
    }

    public Circle(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public double distanceToOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    public double area() {
        return radius * radius * Math.PI;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public void position() {
        System.out.printf("Position of the circle is (%.1f,%.1f)\n", x, y);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
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



    public String toString() {
        return String.format("radius = %.2f, x = %.2f, y = %.2f", radius, x, y);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int n = random.nextInt(5) + 5;
        Circle[] circles = new Circle[n];//创建数组
        int minRadiusIndex = 0;
        int maxPositionIndex = 0;
        for (int i = 0; i < circles.length; i++) {
            double radius = random.nextDouble() * 2 + 1;
            double x = random.nextDouble() * 3 + 2;
            double y = random.nextDouble() * 3 + 2;
            circles[i] = new Circle(radius, x, y);//构造方法
            if (circles[minRadiusIndex].getRadius() > radius)
                minRadiusIndex = i;
            if (circles[maxPositionIndex].distanceToOrigin() < circles[i].distanceToOrigin())
                maxPositionIndex = i;
        }
        for (int i = 0; i < circles.length; i++) {
            System.out.printf("Circle #%d: %s\n", i + 1, circles[i]);//我们直接print当前对象，返回的就是toString方法的结果
            //如果在当前class中没有设置toString方法，返回的是Object类中的toString方法的结果
        }
        System.out.printf("Circle #%d is the smallest circle, area = %.2f\n", minRadiusIndex + 1, circles[minRadiusIndex].area());
        System.out.printf("Circle #%d is the farthest circle, distance to origin = %.2f\n",maxPositionIndex+1,circles[maxPositionIndex].distanceToOrigin());

    }
}
