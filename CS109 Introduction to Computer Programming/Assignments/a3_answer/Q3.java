import java.util.*;

public class Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = in.nextInt();
            }
        }


        boolean flag = false;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (check(arr, i, j)) {
                    System.out.println(i + " " + j + " " + arr[i][j]);
                    flag = true;
                }
            }
        }
        if (!flag) System.out.println("No Sad Point found");
    }

    static boolean check(int[][] arr, int i, int j) {
        for (int k = 0; k < arr.length; k++) {
            if (arr[k][j] > arr[i][j]) {
                return false;
            }
        }
        for (int k = 0; k < arr[0].length; k++) {
            if (arr[i][k] < arr[i][j]) {
                return false;
            }
        }
        return true;
    }
}