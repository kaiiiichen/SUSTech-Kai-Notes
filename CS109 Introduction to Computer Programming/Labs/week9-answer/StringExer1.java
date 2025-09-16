
import java.util.Scanner;

public class StringExer1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = null;
        System.out.print("Type a string (\"quit\" to finish): ");
        while ((s = sc.next()) != null) {
            if (s.startsWith("quit")) break;
            if (checkPalindrome(s)) {
                System.out.printf("%s is a palindrome\n", s);
            } else {
                System.out.printf("%s is not a palindrome\n", s);
            }
            System.out.print("Type a string (\"quit\" to finish): ");
        }
        sc.close();
    }

    public static boolean checkPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}
