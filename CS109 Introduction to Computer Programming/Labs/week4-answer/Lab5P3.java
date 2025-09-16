import java.util.Scanner;

public class Lab5P3 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        //Enter the length of array
        System.out.print("Enter the length of array:");
        int Size = input.nextInt();
        int[] Array1 = new int[Size];
        int[] Array2 = new int[Size];
        //Enter the 1st integer array
        System.out.printf("Enter the 1st integer array of size %d:", Size);
        for (int i = 0; i < Size; i++)
            Array1[i] = input.nextInt();

        //Enter the 2nd integer array
        System.out.printf("Enter the 2nd integer array of size %d:", Size);
        for (int i = 0; i < Size; i++)
            Array2[i] = input.nextInt();

        //Compare two integer arrays
        Boolean flag = true;
        for (int i = 0; i< Size; i++ )
            if(Array1[i] != Array2[i]) {
                flag = false;
                break;
            }

        //Output th result
        if(flag){
            System.out.println("The two arrays are equal.");
        }else{
            System.out.println("The two arrays are not equal.");
        }


    }
}
