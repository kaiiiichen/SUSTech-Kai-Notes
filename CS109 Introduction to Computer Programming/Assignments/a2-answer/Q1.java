import java.util.Arrays;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int length = n;
        int count = 0;
        while (length > 0) {
            count ++;
            boolean isSmallest = true;
            for (int i = 1; i < length; i++) {
                if (arr[0] > arr[i]) {
                    isSmallest = false;
                    break;
                }
            }
            int temp = arr[0];
            for (int i = 0; i < length - 1; i++) {
                arr[i] = arr[i + 1];
            }
            if (isSmallest) {
                length--;
            } else {
                arr[length - 1] = temp;
            }
        }
        System.out.println(count);
    }
}
