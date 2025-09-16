
import java.util.ArrayList;
import java.util.Scanner;

public class StringExer2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please type a string: ");
        String input = sc.nextLine().trim();
        sc.close();

        sc.equals("sss");
        if (input.length() == 0) {
            System.out.println("Empty string, exit...");
            return;
        }

        StringBuilder buffer = new StringBuilder();

        ArrayList<Character> chars = new ArrayList<Character>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isWhitespace(c) && !chars.contains(c)) {
                chars.add(c);
            }
        }

        for (Character c : chars) {
            buffer.append(c);
        }

        System.out.printf("After removing repeating chars: %s\n", buffer.toString());


    }
}
