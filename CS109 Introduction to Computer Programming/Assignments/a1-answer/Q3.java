import java.util.*;

class Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int y1 = in.nextInt(), m1 = in.nextInt(), d1 = in.nextInt(), y2 = in.nextInt(), m2 = in.nextInt(), d2 = in.nextInt();
        int day1 = 51, day2 = 51, day3 = 52, day4 = 51;
        int total = 0;
        if ((y1 + 1) % 7 == 0) day1++;
        else if ((y1 + 2) % 7 == 0) day2++;
        else if ((y1 + 3) % 7 == 0) day3++;
        else if ((y1 + 4) % 7 == 0) day4++;
        if (m1 == 1) {
            if (m2 == 1) {
                total += d2 - d1;

            } else if (m2 == 2) {
                total += day1 - d1 + d2;
            } else if (m2 == 3) {
                total += day1 + day2 - d1 + d2;
            } else if (m2 == 4) {
                total += day1 + day2 + day3 - d1 + d2;
            } else {
                total += day1 + day2 + day3 + day4 - d1 + d2;
            }
        } else if (m1 == 2) {
            if (m2 == 2) {
                total += d2 - d1;
            } else if (m2 == 3) {
                total += day2 + d2 - d1;
            } else if (m2 == 4) {
                total += day2 + day3 + d2 - d1;
            } else {
                total += day2 + day3 + day4 + d2 - d1;
            }
        } else if (m1 == 3) {
            if (m2 == 3) {
                total += d2 - d1;
            } else if (m2 == 4) {
                total += day3 + d2 - d1;
            } else {
                total += day3 + day4 + d2 - d1;
            }
        } else if (m1 == 4) {
            if (m2 == 4) {
                total += d2 - d1;

            } else {
                total += day4 + d2 - d1;
            }
        } else if (m1 == 5) {
            total += d2 - d1;
        }
        System.out.println(total);

    }
}