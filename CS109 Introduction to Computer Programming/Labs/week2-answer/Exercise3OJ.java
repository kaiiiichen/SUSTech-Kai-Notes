import java.util.Scanner;

public class Exercise3OJ {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int m = in.nextInt();
        while (t-- > 0) {
            int N = n;
            int sum = 0;
            while (N-- > 0) {
                int c = in.nextInt();
                sum += c;
            }
            System.out.println(sum - m);
        }
    }
}
