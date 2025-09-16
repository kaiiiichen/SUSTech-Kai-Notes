import java.util.Scanner;

public class Grade {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;
        int grade = input.nextInt();
        while (grade != -1) {
            switch (grade/10){
                case 9: case 10:
                    countA++; break;
                case 8:
                    countB++; break;
                case 7:
                    countC++; break;
                case 6:
                    countD++; break;
                default:
                    countF++;
            }
            grade = input.nextInt();
        }
        System.out.println("A: " + countA);
        System.out.println("B: " + countB);
        System.out.println("C: " + countC);
        System.out.println("D: " + countD);
        System.out.println("F: " + countF);
    }
}
