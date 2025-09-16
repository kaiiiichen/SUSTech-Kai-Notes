public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d * %d = %2d\t", j, i, j * i);
            }
            System.out.println();
        }
    }

}
