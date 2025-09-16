import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double[] score = new double[n];
        int[] credit = new int[n];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            switch (a / 10) {
                case 9:
                case 10:
                    score[i] = 4.0;
                    break;
                case 8:
                    score[i] = 3.0;
                    break;
                case 7:
                    score[i] = 2.0;
                    break;
                case 6:
                    score[i] = 1.0;
                    break;
                default:
                    score[i] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            credit[i] = in.nextInt();
        }
        double avg = in.nextDouble();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double total = score[i] * credit[i] + score[j] * credit[j];
                double gpa = total / (credit[i] + credit[j]);
                if (Math.abs(gpa - avg) < 0.01) {
                    System.out.printf("%d %d\n", i, j);
                }
            }
        }
    }
}
