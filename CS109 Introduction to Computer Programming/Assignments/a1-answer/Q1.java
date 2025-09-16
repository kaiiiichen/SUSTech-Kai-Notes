import java.util.*;

class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        double min = 0, max = 0;
        if (a >= b && a >= c) max = a;
        if (b >= c && b >= a) max = b;
        if (c >= a && c >= b) max = c;
        if (a <= b && a <= c) min = a;
        if (c <= b && c <= a) min = c;
        if (b <= a && b <= c) min = b;
        System.out.printf("%.2f", (min + max) / 2);


    }
}