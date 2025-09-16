
public class Exercise4 {
    public static void main(String[] args) {
        System.out.println(check9Numbers(new int[]{1, 3, 2, 4, 9, 8, 7, 6, 5}));
        System.out.println(check9Numbers(new int[]{1, 1, 2, 4, 9, 8, 7, 6, 5}));
        System.out.println(check9Numbers(new int[]{2, 1, 2, 4, 9, 10, -1, 6, 5}));
        System.out.println(check9Numbers(new int[]{1, 2, 1, 2, 4, 9, 7, 4, 6, 5, 3}));
    }
    public static boolean check9Numbers(int[] array) {
        int[] check = new int[9];
        if (array.length != 9)
            return false;
        for (int i = 0; i < check.length; i++) {
            if (array[i] <= 0 || array[i] > 9)
                return false;
            check[array[i] - 1]++;
        }
        int product = 1;
        for (int e : check) {
            product *= e;
        }
        return product == 1;
    }
}
