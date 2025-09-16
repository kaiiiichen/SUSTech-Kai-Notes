

import java.util.Scanner;
//test input: 88.3 99 45 78 67.5 98.4 23.5 65.5 82 85.4
public class Lab5P2 {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		/** New array and initialize */
		double[] scoreArray=new double[10];
		System.out.print(" Please input 10 scores of these students:");
		for(int i=0;i<10;i++){
			scoreArray[i]=input.nextDouble();
		}

		/** find out the min and max value */
		double max = scoreArray[0];
		double min = scoreArray[0];

		// this is also ok
		// double max = Integer.MIN_VALUE;
		// double min = Integer.MAX_VALUE;

		double sum = 0;
		for (double score : scoreArray) {
			sum += score;
			if (max < score) {
				max = score;
			}
			if (min > score) {
				min = score;
			}
		}
		System.out.printf(" Average score is %.2f", (sum - max - min) / 8);
	}

}
