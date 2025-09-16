import java.sql.SQLOutput;
import java.util.Random;

public class CircleTest {
    public static void main(String[] args) {
        Random random = new Random();
        Circle[] circles = new Circle[random.nextInt(3) + 3];
        for (int i = 0; i < circles.length; i++) {
            double radius = random.nextDouble() * 2 + 1;
            double x = random.nextDouble() * 10 - 5;
            double y = random.nextDouble() * 10 - 5;
            circles[i] = new Circle(radius, x, y);
        }
        System.out.printf("There are %d Circles:\n",Circle.getCount());
        for (Circle c: circles) {
            System.out.println(c);
        }
    }
}
