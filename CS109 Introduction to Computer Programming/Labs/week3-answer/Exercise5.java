import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Please input a number between [1,9]:");
            int number = in.nextInt();
            if (number >= 1 && number <= 9) {
                for (int i = 1; i <= number; i++) {
                    for (int j = 1; j <= i; j++) {
                        System.out.printf("%d * %d = %2d\t", j, i, j * i);
                    }
                    System.out.println();
                }
            } else if (number == 0) {
                break;
            } else {
                System.out.println("Your number is not in [1,9]");
            }
        } while (true);

    }
}
