

public class MyTriangle {
    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static boolean isValid(double side1, double side2, double side3) {
        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            return false;
        }
        return (side1 + side2 > side3) && (side2 + side3 > side1) && (side1 + side3 > side2);
    }

    public static double area(double a, double b, double c) {
        if (isValid(a, b, c)) {
            double p = 0.5 * (a + b + c);
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else {
            return -1;
        }
    }

    public static double area(double bottom, double height) {
        return 0.5 * bottom * height;
    }

    public static double area(double a, double b, int angleOfAandB) {
        return 0.5 * a * b * Math.sin(Math.PI / 180 * angleOfAandB);
    }

    public static void main(String[] args) {
        System.out.println(isValid(3.5, 4, -1));
        System.out.println(isValid(3, 4, 5));
        System.out.println(isValid(1, 2, 2));
        System.out.println(area(0, 4.5, 5.5));
        System.out.println(area(3.5, 4.5, 5.5));
        System.out.println(area(5, 6, 90));
        System.out.println(area(3,6));
    }

}