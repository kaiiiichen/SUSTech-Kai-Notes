import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Circle> circleList = new ArrayList<>();
        Circle.setScreenSize(14);
        StdDraw.setScale(-Shape.getScreenSize(), Shape.getScreenSize());
        for (int i = 0; i < Shape.getScreenSize(); i += 2) {
            circleList.add(new Circle(i + 2, 0, -Shape.getScreenSize()));
        }
//        Collections.sort(circleList);
        for (int i = 0; i < circleList.size(); i++) {
            circleList.get(i).setColor(ShapeColor.values()[i % 3]);
            circleList.get(i).draw();
        }
    }
}
