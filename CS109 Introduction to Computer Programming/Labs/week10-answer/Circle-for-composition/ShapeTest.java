import java.util.Random;

public class ShapeTest {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 3;

        double distanceMax = Double.MIN_VALUE;

        Circle furthestCircle = new Circle(0, new Position(0,0));
        Rectangle furthestRectangle = new Rectangle(new Position(0,0), 1,2);
        Circle.setCount(0);
        Rectangle.setCount(0);
        boolean isCircle = false;

        for (int i = 0; i < n; i++) {
            double r = random.nextDouble() * 2 + 1.0;
            double x = random.nextDouble() * 3 + 2.0;
            double y = random.nextDouble() * 3 + 2.0;

            Circle c = new Circle(r, new Position(x, y));
            System.out.println(c);
            double distance = c.getPosition().distanceToOrigin();
            if (distance > distanceMax) {
                distanceMax = distance;
                furthestCircle = c;
                isCircle = true;
            }
        }

        for (int i = 0; i < n; i++) {
            double width = random.nextDouble() * 2 + 1.0;
            double length = random.nextDouble() * 2 + 1.0;
            double x1 = random.nextDouble() * 3 + 2.0;
            double y1 = random.nextDouble() * 3 + 2.0;

            Rectangle rect = new Rectangle(new Position(x1,y1), width, length);
            System.out.println(rect);
            double distance = rect.getPosition().distanceToOrigin();
            if (distance > distanceMax) {
                distanceMax = distance;
                furthestRectangle = rect;
                isCircle = false;
            }

        }

        if(isCircle){
            System.out.println("\nFurthest " + furthestCircle);
        }else{
            System.out.println("\nFurthest " + furthestRectangle);
        }
    }
}
