



import java.util.Random;
import java.util.Scanner;

public class GuessingNumber {


	public static void main(String[] args) {
		
		Random random = new Random();
		int magicNum = random.nextInt(10);
	
		int inputNum;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please input an Integer in {0,1,2,...,9}:");
		inputNum = sc.nextInt();
		
		while(inputNum != magicNum){
			if (inputNum > magicNum)
				System.out.println("Too big!Please try again:");
			else
				System.out.println("Too small!Please try again:");
			inputNum = sc.nextInt();
		}
		
		System.out.println("Congratulations!");
		sc.close();
	}
}
