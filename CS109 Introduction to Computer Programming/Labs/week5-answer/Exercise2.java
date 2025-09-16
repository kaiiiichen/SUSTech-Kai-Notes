import java.util.Arrays;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of row and column of matrix A:");
        int m1 = in.nextInt();
        int n1 = in.nextInt();
        int[][] a = new int[m1][n1];
        System.out.println("Enter the elements of the matrix: ");
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n1; j++) {
                a[i][j] = in.nextInt();
            }
        }
        System.out.println("Enter the number of row and column of matrix B:");
        int m2 = in.nextInt();
        int n2 = in.nextInt();
        int[][] b = new int[m2][n2];
        System.out.println("Enter the elements of the matrix: ");
        for (int i = 0; i < m2; i++) {
            for (int j = 0; j < n2; j++) {
                b[i][j] = in.nextInt();
            }
        }
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        System.out.println("Product of Matrix is:");
        for (int[] line : c) {
            System.out.println(Arrays.toString(line));
        }
    }
}
