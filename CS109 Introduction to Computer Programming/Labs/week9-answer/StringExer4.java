import java.util.Scanner;

public class StringExer4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("please input number n:");
        int n = in.nextInt();
        long t1 = System.currentTimeMillis();
        printWithString(n);
        long t2 = System.currentTimeMillis();
        printWithStringBuilder(n);
        long t3 = System.currentTimeMillis();
        System.out.printf("Print by String: %.5f\n", (t2 - t1) / 1000.0);
        System.out.printf("Print by StringBuilder: %.5f\n", (t3 - t2) / 1000.0);
    }

    public static void printWithString(int n) {
        String str = "";
        for (int i = 0; i < n; i++) {
            str = str + (char) (97 + i % 24);// str = new String("abc"+"d"); 非常耗时
            System.out.println(str);
        }
    }
    public static void printWithStringBuilder(int n) {
        StringBuilder sb = new StringBuilder();//创建的时候，在StringBuilder内层创建了一个char[]
//        //每次执行append的时候，把append里的内容放进char数组的后面，如果char数组越界，再创建一个更长的char数组
//        // 做频繁字符串拼接时，StringBuilder效率高很多

        for (int i = 0; i < n; i++) {
            sb.append((char) (97 + i % 24));
            System.out.println(sb);
        }
    }
}
