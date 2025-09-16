import java.util.Arrays;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        String[] parts = str.split("\\.");
        if (parts.length != 4) {
            System.out.println("No");
        } else {
            boolean result = true;
            outer:
            for (String s : parts) {
                if (s.isEmpty()) {
                    result = false;
                    break;
                }
                for (char c : s.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        result = false;
                        break outer;
                    }
                }
                int x = Integer.parseInt(s);
                if (x < 0 || x > 255) {
                    result = false;
                    break;
                }
                if (x >= 100 && s.length() != 3) {
                    result = false;
                    break;
                }
                if (x >= 10 && x < 100 && s.length() != 2) {
                    result = false;
                    break;
                }
                if (x == 0 && s.length() != 1) {
                    result = false;
                    break;
                }
            }
            System.out.print(result ? "Yes" : "No");
        }
    }
}
