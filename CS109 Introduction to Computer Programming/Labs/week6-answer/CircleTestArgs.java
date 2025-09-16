public class CircleTestArgs {
    public static void main(String[] args) {
        double side1 = Double.parseDouble(args[0]);
        double side2 = Double.parseDouble(args[1]);
        double side3 = Double.parseDouble(args[2]);
        System.out.println("Area = " + MyTriangle.area(side1, side2, side3));
        System.out.println("Perimeter = " + MyTriangle.perimeter(side1, side2, side3));
    }
}
