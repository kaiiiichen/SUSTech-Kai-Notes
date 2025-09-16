import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeTest {
    public static void main(String[] args) {
        List<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            circles.add(new Circle(2 * i + 1, 0, 2 * i + 1));

        }
        StdDraw.setScale(-9, 9);
        Collections.sort(circles);
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).customizedColor(ColorScheme.SKY,i);
        }
    }
}
