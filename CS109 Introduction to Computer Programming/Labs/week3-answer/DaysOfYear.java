import java.util.Scanner;

public class DaysOfYear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int year = in.nextInt();
        int month = in.nextInt();
        int day = in.nextInt();
        int daysOfYear = 0;
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    daysOfYear += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    daysOfYear += 30;
                    break;
                case 2:
                    daysOfYear += (isLeapYear) ? 29 : 28;

            }
        }
        daysOfYear += day;
        System.out.println(daysOfYear);
    }
}
