
//test input: 2.5 5.5 3.4 6.4 7.7 2.2 8.9 0.2
public class Lab5P1 {

	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		/** New array */
		double[] myList1;
		System.out.print(" Enter the length of myList1:");
		int n = input.nextInt();
		myList1 = new double[n];

		/** Initialize */

		System.out.print(" Enter " + myList1.length + " values:   ");
		for (int i = 0; i < myList1.length; i++) {
			myList1[i] = input.nextDouble();
		}

		/*make a copy*/
		double[] myList2 = new double[myList1.length];
		for(int i = 0; i < myList1.length; i++)
			myList2[i] = myList1[i];

		/** Shifting elements */
		double temp = myList1[0];
		for (int j = 1; j < myList1.length; j++) {
			myList1[j - 1] = myList1[j];
		}
		myList1[myList1.length - 1] = temp;

		System.out.print(" myList1:");
		for (int i = 0; i < myList1.length; i++) {
			System.out.print(myList1[i] + " ");
		}
		System.out.println();


		/** Printing array */
		System.out.print(" myList2:");
		for (double item : myList2) {
			System.out.print(item + " ");
		}
	}

}
