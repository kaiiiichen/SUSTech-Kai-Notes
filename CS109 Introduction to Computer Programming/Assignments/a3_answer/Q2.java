import java.util.*;

public class Q2 {
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
        int up = 0;
        int down = M - 1;
        int left = 0;
        int right = N - 1;

        while (true) {
            for (int i = up; i <= down; i++) {
                System.out.print(arr[i][left] + " ");
            }
            left++;
            if (left > right) break;
            for (int i = left; i <= right; i++) {
                System.out.print(arr[down][i] + " ");
            }
            down--;
            if (down < up) break;
            for (int i = down; i >= up; i--) {
                System.out.print(arr[i][right] + " ");
            }
            right--;
            if (right < left) break;
            for (int i = right; i >= left; i--) {
                System.out.print(arr[up][i] + " ");

            }
            up++;
            if (up > down) break;
        }

    }
}