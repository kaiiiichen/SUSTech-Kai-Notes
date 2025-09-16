import java.util.Arrays;
import java.util.Scanner;

/*
100 5
5 50 40 20 10
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double budget = in.nextDouble();
        int n = in.nextInt();
        double[] price = new double[n];
        int[] id = new int[n];//record the index of price
        boolean[] buy = new boolean[n];//record whether buy it
        boolean sufficient = true;
        for (int i = 0; i < price.length; i++) {
            price[i] = in.nextDouble();
            id[i] = i;
        }
        for (int i = 0; i < price.length - 1; i++) {
            for (int j = 0; j < price.length - 1 - i; j++) {
                if (price[j] > price[j + 1]) {
                    double temp1 = price[j];
                    price[j] = price[j + 1];
                    price[j + 1] = temp1;
                    int temp2 = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = temp2;
                }
            }
        }
        for (int i = 0; i < price.length; i++) {
            if (budget > price[i]) {
                budget -= price[i];
                buy[id[i]] = true;
            } else {
                sufficient = false;
            }
        }
        if (sufficient) {
            System.out.printf("Budget sufficient, remaining %.2f", budget);
        } else {
            System.out.printf("Budget insufficient, remaining %.2f, ", budget);
            for (int i = 0; i < buy.length; i++) {
                if (!buy[i]) {
                    System.out.print(i + " ");
                }
            }
        }

    }
}
