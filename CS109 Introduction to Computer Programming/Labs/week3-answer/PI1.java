import java.util.Scanner;

public class PI1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double Pi = 0;
        System.out.println("Please input n:");
        int n = in.nextInt();
        double increment;
        for (int i = 1; i <= n; i++) {
            increment = 4.0 / (2 * i - 1);
            if (i % 2 == 1)
                Pi += increment;
            else
                Pi -= increment;
        }
        System.out.printf("The estimational of Pi is %f\n", Pi);
    }
}
