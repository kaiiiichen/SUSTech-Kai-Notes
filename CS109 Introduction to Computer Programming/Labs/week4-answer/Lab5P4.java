// test input:22 33 55 34 99 87 45 34 23 78 45 33 11 23 87 34 76 0
import java.util.Scanner;

public class Lab5P4 {
	/** Main Method */
	public static void main(String[] args) {
		int[] counts = new int[100]; // Counts the occurrences of numbers

		// Prompt the user to enter integers between 1 and 100
		System.out.print("Enter the integers between 1 and 100: ");

		Scanner input = new Scanner(System.in);
		int num; // holds user input
		do {
			num = input.nextInt();
			if (num >= 1 && num <= 100)	
				counts[num - 1]++;
		} while (num != 0);
		// Just store the input in the corresponding position in array
		// No need to think about an array with a variable length


		// Display results
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0)
				System.out.println((i + 1) + " occurs " + counts[i] +
					" time" + (counts[i] > 1 ? "s" : ""));
		}
	}



}
