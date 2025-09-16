import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        String finalString = a.replaceAll(b, "");
        System.out.println(finalString);
        StringBuilder sb = new StringBuilder();
        for (char c : finalString.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        System.out.println(sb.toString());
        String str1 = sb.toString();
        String str2 = sb.reverse().toString();
        System.out.println(str1.equals(str2) ? "Yes" : "No");
    }
}
