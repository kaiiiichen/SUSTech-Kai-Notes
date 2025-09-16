import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int hour, minute, second, seconds;
        System.out.print("Enter the number of seconds: ");
        seconds = in.nextInt();
        hour = seconds / 3600;
        minute = seconds / 60 % 60;
        second = seconds % 60;
        System.out.printf("The equivalent time is %d hours %d minutes and %d seconds.", hour, minute, second);

    }
}
