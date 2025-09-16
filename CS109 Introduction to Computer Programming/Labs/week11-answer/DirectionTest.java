import java.util.EnumSet;

public class DirectionTest {
    public static void main(String[] args) {
        for (Direction d:Direction.values()) {
            System.out.println(d);
        }

        for (Direction d: EnumSet.range(Direction.EAST,Direction.NORTH)
             ) {
            System.out.println(d);
        }
    }
}
