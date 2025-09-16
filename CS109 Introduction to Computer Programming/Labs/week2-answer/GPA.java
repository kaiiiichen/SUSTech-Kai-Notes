import java.util.Scanner;

public class GPA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int grade;
        double gpa;
        System.out.println("Please input your grade:");
        grade = in.nextInt();
        if (grade >= 90) {
            gpa = 4.0;
        } else if (grade >= 80) {
            gpa = 3.0;
        } else if (grade >= 70) {
            gpa = 2.0;
        } else if (grade >= 60) {
            gpa = 1.0;
        } else {
            gpa = 0;
        }
        System.out.println("Your gpa is "+gpa);
    }
}
