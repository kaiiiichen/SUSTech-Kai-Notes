import java.util.*;

class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        System.out.printf("%.2f", ((a.charAt(0) - '0') + a.charAt(1) + a.charAt(2) + a.charAt(3) + a.charAt(4) + a.charAt(5) - 5 * '0') / 11.0);


    }
}