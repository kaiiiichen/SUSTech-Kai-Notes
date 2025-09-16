import java.util.Arrays;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the number of matrix:");
        int n = in.nextInt();
        int[][] a = inputMatrix(in);
        while (n-- > 1) {
            int[][] b = inputMatrix(in);
            a = productMatrixs(a, b);
            System.out.println("Product of Matrix is:");
            for (int[] line : a) {
                System.out.println(Arrays.toString(line));
            }
        }
        in.close();
    }
    private static int[][] inputMatrix(Scanner in) {
        System.out.println("Enter the number of row and column of matrix:");
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] a = new int[m][n];
        System.out.println("Enter the elements of the matrix: ");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        return a;
    }
    private static int[][] productMatrixs(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

}
