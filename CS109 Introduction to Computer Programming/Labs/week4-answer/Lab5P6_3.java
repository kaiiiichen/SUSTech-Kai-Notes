// test input:2 3 5 6 9 10 12 13 15 16
import java.util.Scanner;

public class Lab5P6_3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // Create a Scanner

		// Prompt the user to enter how many integers
		System.out.print("Enter how many numbers: ");
		int n = input.nextInt();
		int[] numbers = new int[n]; // Create an array of length ten

		System.out.printf("Enter %d numbers: \n", n);
		int total = 0;
		for (int i = 0; i < n; i++) {
			numbers[i] = input.nextInt();
			total += numbers[i];
		}
		long current1 = System.currentTimeMillis();
		double avg = total / (double) n;
		System.out.println("average=" + avg);
		avg *= 2;
		int count = 0;

		// search from left and right side at the same time
		// approach the middle together
		// fastest in all solutions
		// time complexity O(n)
		int left=0, right=n-1,smallest=n;
		while(left<right){
			while((numbers[left]+numbers[right])>avg)	{
				smallest=right;
				right--;
				if(left>=right){
					break;
				}
			}
			count+=n-smallest;
			left++;		
		}
		// the remained right half must have the sum grater than the avarage value
		right=n-left;
		count+=right*(right-1)/2;
		
		System.out.println("The number of pairs of integer is " + count);
		long current2 = System.currentTimeMillis();
		System.out.printf("The running time is %.3f second",
				(current2 - current1) / 1000.0d);
	}

}
