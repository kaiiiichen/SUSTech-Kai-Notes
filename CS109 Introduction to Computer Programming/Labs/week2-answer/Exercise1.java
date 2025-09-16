import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double width, height;
        System.out.print("Enter the width of a rectangle: ");
        width = in.nextDouble();
        System.out.print("Enter the height of a rectangle: ");
        height = in.nextDouble();
        System.out.printf("The area is %.2f\n" +
                "The perimeter is %.2f", width * height, (width + height) * 2);
    }
}
