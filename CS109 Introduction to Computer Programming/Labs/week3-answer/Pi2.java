import java.util.Scanner;

public class Pi2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double Pi = 0;
        System.out.println("Please input the precision:");
        double precision = in.nextDouble();
        double increment = Double.MAX_VALUE;
        int count = 0;
        while (increment > precision) {
            count++;
            increment = 4.0 / (2 * count - 1);
            if (count % 2 == 1)
                Pi += increment;
            else
                Pi -= increment;
        }
        System.out.printf("The estimational of Pi is %.6f\n", Pi);
        System.out.printf("It computed %d times\n",count);
    }
}
