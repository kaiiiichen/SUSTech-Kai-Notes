import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] sudoku = new int[9][9];
        boolean result = true;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                sudoku[i][j] = in.nextInt();
            }
        }
        //check row
        for (int[] ints : sudoku) {
            if (!check9Numbers(ints)) {
                result = false;
                break;
            }
        }
        //check column
        if (result) {
            for (int j = 0; j < sudoku[0].length; j++) {
                int[] line = new int[9];
                for (int i = 0; i < sudoku.length; i++) {
                    line[i] = sudoku[i][j];
                }
                if (!check9Numbers(line)) {
                    result = false;
                    break;
                }
            }
        }
        //check 3*3
        outer:
        if (result) {
            for (int i = 0; i < 9; i += 3) {
                for (int j = 0; j < 9; j += 3) {
                    int count = 0;
                    int[] line = new int[9];
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            line[count] = sudoku[i + k][j + l];
                            count++;
                        }
                    }
                    if (!check9Numbers(line)) {
                        result = false;
                        break outer;
                    }
                }
            }
        }

        System.out.println(result?"Yes":"No");
    }

    /**
     * If the elements of array are 1-9 and each number only appears once,
     * then return true, otherwise return false
     *
     * @param array
     * @return
     */
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
