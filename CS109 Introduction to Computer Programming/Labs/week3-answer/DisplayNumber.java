import java.util.Scanner;

public class DisplayNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Please input a number between [1,9]:");
            int number = in.nextInt();
            if (number >= 1 && number <= 9) {
                System.out.printf("number %d is in [1, 9]\n", number);
            } else if (number == 0) {
                break;
            }else{
                System.out.println("Your number is not in [1,9]");
            }
        } while (true);
    }
}
